/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author SALTI DILFANI
 */
public class TabelBarang extends AbstractTableModel{

    List<Barang> lmb;

    public TabelBarang(List<Barang> lmb) {
        this.lmb = lmb;
    }
   
    @Override
    public int getRowCount() {
        return lmb.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        switch(column){
            case 0:
                return lmb.get(row).getNo();
            case 1:
                return lmb.get(row).getNama();
            case 2:
                return lmb.get(row).getHarga();
            case 3:
                return lmb.get(row).getStok();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "no";
            case 1:
                return "nama";
            case 2:
                return "harga";
            case 3:
                return "stok";
            default:
                return null;
        }
    }
    
}
