/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Koneksi.KoneksiDb;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Barang;

/**
 *
 * @author SALTI DILFANI
 */
public class DaoBarang implements ImplementBarang{

    Connection connection;
    final String insert = "Insert into barang (nama, harga, stok) values (?,?,?)";
    final String delete = "Delete from barang where no=?";
    final String update = "Update barang set nama=?, harga=?, stok=? where no=?";
    final String select = "Select * from barang";
    final String carinama = "Select * from barang where nama like ?";

    public DaoBarang() {
        connection = KoneksiDb.connection();
    }
   
    @Override
    public void insert(Barang b) {
        PreparedStatement statement = null;
         
        try {
            statement = (PreparedStatement) connection.prepareStatement(insert);
            statement.setString(1, b.getNama());
            statement.setDouble(2, b.getHarga());
            statement.setInt(3, b.getStok());
            statement.executeUpdate();
            ResultSet rs = (ResultSet) statement.getGeneratedKeys();
            while (rs.next()){
                b.setNo(rs.getInt(5));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
 
    @Override
    public void delete(int no) {
        PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(delete);
            statement.setInt(1, no);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try{
                statement.close();
            } catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        
    }
    @Override
    public List<Barang> getAll() {
        List<Barang> lmb = null;

        try {
            lmb = new ArrayList<Barang>();
            Statement st =  (Statement) connection.createStatement();
            
            ResultSet rs = (ResultSet) st.executeQuery(select);
            while(rs.next()){
                Barang b = new Barang();
                b.setNo(rs.getInt("no"));
                b.setNama(rs.getString("nama"));
                b.setHarga(rs.getDouble("harga"));
                b.setStok(rs.getInt("stok"));
                lmb.add(b);
            }
        } catch (SQLException ex) {
                ex.printStackTrace();
        }
        return lmb;
    }

    @Override
    public List<Barang> getCariNama(String nama) {
        List<Barang> lmb = null;
        try {
            lmb = new ArrayList<Barang>();
            PreparedStatement st = (PreparedStatement) connection.prepareStatement(carinama);
            st.setString(1, "%" + nama + "%");
            ResultSet rs = (ResultSet) st.executeQuery();
            while(rs.next()){
                Barang b = new Barang();
                b.setNo(rs.getInt("no"));
                b.setNama(rs.getString("nama"));
                b.setHarga(rs.getDouble("harga"));
                b.setStok(rs.getInt("stok"));
                lmb.add(b);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lmb;
    }
       

    @Override
    public void update(Barang b) {
    PreparedStatement statement = null;
         
        try {
            statement = (PreparedStatement) connection.prepareStatement(update);
            statement.setString(1, b.getNama());
            statement.setDouble(2, b.getHarga());
            statement.setInt(3, b.getStok());
            statement.setInt(4, b.getNo());
            statement.executeUpdate(); 
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    }
    
 
