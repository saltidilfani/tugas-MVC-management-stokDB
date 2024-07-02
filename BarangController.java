/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DaoBarang;
import dao.ImplementBarang;
import java.util.List;
import javax.swing.JOptionPane;
import model.Barang;
import model.TabelBarang;
import view.FormBarang;

/**
 *
 * @author SALTI DILFANI
 */
public class BarangController {
    FormBarang frame;
    ImplementBarang implbarang;
    List<Barang> lmb;

    public BarangController(FormBarang frame) {
        this.frame = frame;
        implbarang = new DaoBarang();
        lmb = implbarang.getAll();
    }
    
    public void reset(){
         frame.getTxtNo().setText("");
         frame.getTxtNama().setText("");
         frame.getTxtHarga().setText("");
         frame.getTxtStok().setText("");
    }
    
    public void isiTable(){
        lmb = implbarang.getAll();
        TabelBarang tb = new TabelBarang(lmb);
        frame.getTabelBarang().setModel(tb);
    }
    
    public void isiField(int row){
        frame.getTxtNo().setText(String.valueOf(lmb.get(row).getNo()));
        frame.getTxtNama().setText(lmb.get(row).getNama());
        frame.getTxtHarga().setText(String.valueOf(lmb.get(row).getHarga()));
        frame.getTxtStok().setText(String.valueOf(lmb.get(row).getStok()));
    }
    
    public void insert(){
        if(!frame.getTxtNama().getText().trim().isEmpty() & !frame.getTxtNama().getText().trim().isEmpty()){
            Barang b = new Barang();
            b.setNo(Integer.valueOf(frame.getTxtNo().getText()));
            b.setNama(frame.getTxtNama().getText());
            b.setHarga(Double.valueOf(frame.getTxtHarga().getText()));
            b.setStok(Integer.valueOf(frame.getTxtStok().getText()));
            
            implbarang.insert(b);
            JOptionPane.showMessageDialog(null, "Data Disimpan");
    }else{
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan");
        }
    }
    
    public void delete(){
        if(!frame.getTxtNo().getText().trim().isEmpty()){
            int no = Integer.parseInt(frame.getTxtNo().getText());
            implbarang.delete(no);
            JOptionPane.showMessageDialog(null, "Data Dihapus");
    }else{
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus");
        }
    }
    
    public void update(){
        if(!frame.getTxtNo().getText().trim().isEmpty()){
            Barang b = new Barang();
            
            b.setNama(frame.getTxtNama().getText());
            b.setHarga(Double.valueOf(frame.getTxtHarga().getText()));
            b.setStok(Integer.valueOf(frame.getTxtStok().getText()));
            b.setNo(Integer.valueOf(frame.getTxtNo().getText()));
            
            implbarang.update(b);
            JOptionPane.showMessageDialog(null, "Data Diperbarui");
    }else{
            JOptionPane.showMessageDialog(null, "Data Gagal Diperbarui");
        }
    }
    
    public void isiTableCariNama(){
        lmb = implbarang.getCariNama(frame.getTxtCari().getText());
        TabelBarang tb = new TabelBarang(lmb);
        frame.getTabelBarang().setModel(tb);
    }
    
    public void cariNama(){
        if(!frame.getTxtCari().getText().trim().isEmpty()){
           implbarang.getCariNama(frame.getTxtCari().getText());
           isiTableCariNama();
        }else{
            JOptionPane.showMessageDialog(null, "Silahkan masukkan nama");
        }
    }
}
