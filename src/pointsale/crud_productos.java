
package pointsale;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class crud_productos {
    public conexion conector;

    public crud_productos(){
    conector=new conexion();
    }

    //guardar productos
    public void guardando(String code, String name, String price, String stock, String unit, String measures) {
        try {
           conector.ejecutar("INSERT INTO productos(codigo, nombre, precio, existencia, cant_unit, unid_med) "
                    + "VALUES('" + code + "','" + name + "','" + price + "','" + stock + "','" + unit + "','" + measures + "')");
           // Statement estado_ingreso = prin.conx.createStatement();
            //estado_ingreso.executeUpdate("INSERT INTO productos(codigo, nombre, precio, existencia, cant_unit, unid_med) "
              //      + "VALUES('" + code + "','" + name + "','" + price + "','" + stock + "','" + unit + "','" + measures + "')");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "crud " + ex);
        }
    }
    
    //Eliminar productos
    public void Eliminando(String code) {
        try {
           conector.ejecutar("delete from productos where codigo='"+code+"' or nombre='"+code+"'");
           // Statement estado_ingreso = prin.conx.createStatement();
            //estado_ingreso.executeUpdate("INSERT INTO productos(codigo, nombre, precio, existencia, cant_unit, unid_med) "
              //      + "VALUES('" + code + "','" + name + "','" + price + "','" + stock + "','" + unit + "','" + measures + "')");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "crud eliminar " + ex);
        }
    } 
    
    public void modificar(int id,String code,String producto,String precio,String existencia,String cant, String unid_med){
        try{
            //conector.ejecutar("update productos set codigo='"+code+"' where codigo='"+code+"' or nombre='"+code+"'  ");
            //conector.ejecutar("update productos set nombre='"+code+"' where codigo='"+code+"' or nombre='"+code+"'  ");
          /*  System.out.println("ver productos des " + code + "   " + producto);
            if (code.trim().length() == 0) {
                System.out.println("true");
                res = conector.Consulta("select * from productos where  nombre='" + producto + "'");
            } else {
                System.out.println("false");

                res = conector.Consulta("select * from productos where  codigo='" + code + "'");
            }
            while (res.next()) {
                id = res.getInt("id_producto");
            }
            System.out.println("id :  " + id);*/
            conector.ejecutar("update productos set codigo='" + code + "' where id_producto='" + id + "' ");
            conector.ejecutar("update productos set nombre='" + producto + "' where id_producto='" + id + "' ");
            conector.ejecutar("update productos set precio='" + precio + "' where id_producto='" + id + "' ");
            conector.ejecutar("update productos set existencia='" + existencia + "' where id_producto='" + id + "'   ");
            conector.ejecutar("update productos set cant_unit='" + cant + "' where id_producto='" + id + "'   ");
            conector.ejecutar("update productos set unid_med='" + unid_med + "' where id_producto='" + id + "'   ");

        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "crud modificar" + ex);
        }
        
    }
    
    /*
*/
}
