package pointsale;

import java.awt.Color;
import javax.swing.*;
import java.sql.*;
import java.util.Calendar;
import java.util.Random;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class Acciones {

    public Principal prin;
    public conexion conector;
    public String name_producto, unid_med, cantlleva = null, folio, fecha_timestamp, codigo_cancel, producto_cancel;
    public float precio, total, contador, conv_cantidad, diferencia_cantidades = 0, importe = 0, convertcantlleva, existencia_acc, cuenta_stock, cantidad_cancel, existencia_cancel, suma_existencias;
    ResultSet res;
    public DefaultTableModel modelo = new DefaultTableModel(new String[]{"Código", "Descripción", "Precio", "Cantidad", "Importe"}, 0);
    DefaultTableModel modelobuscar = new DefaultTableModel(new String[]{"Descripcion".toUpperCase(), "Precio"}, 0);
    ResultSet buscar_por_nombre;

    public Random rand = new Random();
    public static Calendar cal = Calendar.getInstance();
    int NumFactura = rand.nextInt(100000);
    int dia, mes, year;
    int hora, minuntos, segundos;
    int insert_ind = 0, agrega_id, contar_p,ver_id;

    public Acciones(Principal pri) {
        this.prin = pri;

        conector = new conexion();
        year = cal.get(Calendar.YEAR);
        mes = cal.get(Calendar.MONTH) + 1;
        dia = cal.get(Calendar.DAY_OF_MONTH);
        hora = cal.get(Calendar.HOUR_OF_DAY);
        minuntos = cal.get(Calendar.MINUTE);
        segundos = cal.get(Calendar.SECOND);
        folio = "" + year + "" + mes + "" + dia + "" + NumFactura;

        fecha_timestamp = "" + year + "-" + mes + "-" + dia;
        System.out.println("recolectando la fecha " + fecha_timestamp);
    }

    public void verificar_codigo(String code, String pro_nom, float cantidad) {
        try {
            precio = 0;
            if (code.isEmpty()) {
                conector.Consulta("SELECT * FROM productos WHERE  nombre='" + pro_nom + "'");
            } else {
                conector.Consulta("SELECT * FROM productos WHERE codigo='" + code + "'");
            }
            /*Statement sta_verificar = prin.conx.createStatement();
            ResultSet res_verificar = sta_verificar.executeQuery("SELECT * FROM productos WHERE codigo='" + code + "'");*/
            while (conector.rst.next()) {
                name_producto = conector.rst.getString("nombre");
                precio = conector.rst.getFloat("precio");
                unid_med = conector.rst.getString("unid_med");

            }
            if (code.isEmpty()) {
                conector.Consulta("select count(*) from ventas where nombre='" + pro_nom + "'");
            } else {
                conector.Consulta("select count(*) from ventas where codigo='" + code + "'");
            }
            while (conector.rst.next()) {
                ver_id = conector.rst.getInt(1);
            }
                    
             
            if (precio != 0 && cantidad > 0) {
                if (code.isEmpty()) {
                    AgregarTabla("".trim(), name_producto, precio, cantidad, unid_med,ver_id);// agregar a tabla
                } else {
                    AgregarTabla(code, name_producto, precio, cantidad, unid_med,ver_id);// agregar a tabla
                }
                prin.obtiene_mensaje().setText("");

            } else {
                prin.obtiene_mensaje().setText("Inexistencia de Producto");
                prin.obtiene_mensaje().setForeground(Color.red);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Verificando " + ex);
        }
    }

    public void AgregarTabla(String codigo, String producto, float precio, float cantidad, String uni_med,int id) {
        try {
            Vector fila = new Vector();

            fila.add(0, codigo);
            fila.add(1, producto.toUpperCase());
            fila.add(2, precio);
            
            
            if(id>0){
            int idf=fila.indexOf(codigo);
            System.out.print("sacar id numero"+idf);
            }

            //stock
            if (codigo.isEmpty()) {
                conector.Consulta("select * from productos where  nombre ='" + producto + "'");
            } else {
                conector.Consulta("select * from productos where codigo='" + codigo + "' ");
            }
            while (conector.rst.next()) {
                existencia_acc = conector.rst.getFloat("existencia");
            }
            //

            if (uni_med.equals("Pz")) {
                total = total + (precio * cantidad);// total
                importe = precio * cantidad;
                contador = contador + cantidad;// contador de productos
                fila.add(3, cantidad + " " + uni_med);
                //agregar a tabla ventas
                conector.ejecutar("INSERT INTO ventas(codigo,descripcion,precio,cantidad,unidad_medida,indice,dia,mes,year,folio,fecha) "
                        + "VALUES('" + codigo + "','" + producto + "','" + precio + "','" + cantidad + "','" + uni_med + "','" + insert_ind + "','" + dia + "','" + mes + "','" + year + "','" + folio + "','" + fecha_timestamp + "')");
                cuenta_stock = existencia_acc - cantidad;

                if (codigo.isEmpty()) {
                    conector.ejecutar("update productos set existencia='" + cuenta_stock + "' where  nombre='" + producto + "'");

                } else {
                    conector.ejecutar("update productos set existencia='" + cuenta_stock + "' where codigo='" + codigo + "' ");

                }
            } else if (uni_med.equals("Kg") || uni_med.equals("Lt")) {
                total = total + ((cantidad / 1000) * precio);
                importe = precio * (cantidad / 1000);
                contador = contador + (cantidad / 1000);// contador de productos
                fila.add(3, (cantidad / 1000) + " " + uni_med);
                //agregar a tabla ventas
                conector.ejecutar("INSERT INTO ventas(codigo,descripcion,precio,cantidad,unidad_medida,dia,mes,year,folio,fecha) "
                        + "VALUES('" + codigo + "','" + producto + "','" + precio + "','" + (cantidad / 1000) + "','" + uni_med + "','" + dia + "','" + mes + "','" + year + "','" + folio + "','" + fecha_timestamp + "')");
                cuenta_stock = existencia_acc - (cantidad / 1000);
                if (codigo.isEmpty()) {
                    conector.ejecutar("update productos set existencia='" + cuenta_stock + "' where  nombre='" + producto + "'");

                } else {
                    conector.ejecutar("update productos set existencia='" + cuenta_stock + "' where codigo='" + codigo + "' ");

                }
            }

            System.out.println("he agregado indice " + insert_ind);

            String ConvierteTotal = Float.toString(total);

            String Conviertecontador = Float.toString(contador);

            fila.add(4, importe);
            // modelo.addRow(fila);
            modelo.insertRow(0, fila);
            prin.obtiene_tabla().setModel(modelo);
            prin.obtiene_tabla().getColumnModel().getColumn(1).setPreferredWidth(300);

            prin.obtiene_total().setText(ConvierteTotal);
            prin.obtiene_contador().setText(Conviertecontador);
            prin.obtiene_folio().setText(folio);
            prin.obtiene_imp().setEnabled(true);
            prin.obtiene_recibe().setEditable(true);
            prin.obtiene_recibe().setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 51), 3));
            insert_ind++;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Agregar a Tabla " + ex);
        }
    }

    public void eliminar_producto(String codigo, String producto, int indice, String precio, String cantidad, String cant_llevada) {
        try {
            float conv_precio = Float.parseFloat(precio);
            conv_cantidad = Float.parseFloat(cantidad);
            String obten_medida = null;
            ResultSet re;
            int id_p = 0;
            System.out.println(codigo + " ind " + indice + "pre " + precio + " cant " + cantidad + " llevado " + cant_llevada);
            //quitar la etiqueta Kg,Lt yPz para convertir a flotante
            if (cant_llevada.endsWith("Pz")) {///por pieza
                cantlleva = cant_llevada.replace("Pz", " ");
                convertcantlleva = Float.parseFloat(cantlleva);//conversion 
                obten_medida = "Pz";
                total = total - (conv_precio * conv_cantidad);
                contador = contador - conv_cantidad;// contador de productos
                importe = conv_precio * (convertcantlleva - conv_cantidad);
                modelo.setValueAt(importe, indice, 4);
                System.out.println("es una pz");
            } else if (cant_llevada.endsWith("Lt")) {//por litros
                cantlleva = cant_llevada.replace("Lt", " ");
                convertcantlleva = Float.parseFloat(cantlleva) * 1000;//conversion 
                obten_medida = "Lt";
                total = total - ((conv_cantidad / 1000) * conv_precio);
                contador = contador - (conv_cantidad / 1000);// contador de productos
                importe = conv_precio * ((convertcantlleva - conv_cantidad) / 1000);
                modelo.setValueAt(importe, indice, 4);
                System.out.println(" es medida lt");
            } else if (cant_llevada.endsWith("Kg")) {
                cantlleva = cant_llevada.replace("Kg", " ");
                convertcantlleva = Float.parseFloat(cantlleva) * 1000;//conversion 
                total = total - ((conv_cantidad / 1000) * conv_precio);
                contador = contador - (conv_cantidad / 1000);// contador de productos
                importe = conv_precio * ((convertcantlleva - conv_cantidad) / 1000);
                modelo.setValueAt(importe, indice, 4);
                obten_medida = "Kg";
            }

            //stock
            if (codigo.isEmpty()) {
                conector.Consulta("select * from productos where  nombre ='" + producto + "'");

            } else {
                conector.Consulta("select * from productos where codigo='" + codigo + "' ");

            }
            while (conector.rst.next()) {
                existencia_acc = conector.rst.getFloat("existencia");

            }
            //
            ////id de ventas

            diferencia_cantidades = (convertcantlleva - conv_cantidad);
            System.out.println("deifenrecia:" + diferencia_cantidades);
            if (diferencia_cantidades > 0) {
                if (obten_medida.equals("Pz")) {
                    modelo.setValueAt(diferencia_cantidades + " " + obten_medida, indice, 3);//cambiar cantidad llevada
                    cuenta_stock = existencia_acc + (conv_cantidad);
                    if (codigo.isEmpty()) {
                        conector.ejecutar("UPDATE ventas SET cantidad='" + diferencia_cantidades + "' WHERE folio='" + folio + "' and  descripcion='" + producto + "' and id_venta='" + id_p + "'");
                        conector.ejecutar("update productos set existencia='" + cuenta_stock + "' where  nombre='" + producto + "' ");
                    } else {
                        conector.ejecutar("UPDATE ventas SET cantidad='" + diferencia_cantidades + "' WHERE folio='" + folio + "' AND codigo='" + codigo + "' AND cantidad='" + cant_llevada + "'  ");
                        conector.ejecutar("update productos set existencia='" + cuenta_stock + "' where codigo='" + codigo + "' ");
                    }

                } else if (obten_medida.equals("Lt") || obten_medida.equals("Kg")) {
                    modelo.setValueAt((diferencia_cantidades / 1000) + " " + obten_medida, indice, 3);//cambiar cantidad llevada
                    cuenta_stock = existencia_acc + (conv_cantidad / 1000);
                    if (codigo.isEmpty()) {
                        conector.ejecutar("update ventas set cantidad='" + (diferencia_cantidades / 1000) + "' where folio='" + folio + "'  AND descripcion='" + producto + "'");
                        conector.ejecutar("update productos set existencia='" + cuenta_stock + "' where  nombre='" + producto + "'");
                    } else {
                        conector.ejecutar("update ventas set cantidad='" + (diferencia_cantidades / 1000) + "' where folio='" + folio + "' AND codigo='" + codigo + "' ");
                        conector.ejecutar("update productos set existencia='" + cuenta_stock + "' where codigo='" + codigo + "' ");
                    }

                }
                prin.obtiene_mensaje().setText("");
            } else if (diferencia_cantidades < 0) {
                prin.obtiene_mensaje().setText("Cantidad Mayor a la Existente");
                prin.obtiene_mensaje().setForeground(Color.red);

            } else if (diferencia_cantidades == 0) {////borra de ventas y tabla y modifica existencias
                modelo.removeRow(indice);
                insert_ind--;
                prin.obtiene_mensaje().setText("");
                prin.obtiene_imp().setEnabled(false);
                if (codigo.isEmpty()) {

                    conector.ejecutar("delete from ventas where  folio='" + folio + "' and descripcion='" + producto + "'");
                    if (obten_medida.equals("Pz")) {
                        cuenta_stock = existencia_acc + (conv_cantidad);
                        conector.ejecutar("update productos set existencia='" + cuenta_stock + "' where  nombre='" + producto + "'");
                    } else {
                        cuenta_stock = existencia_acc + (conv_cantidad / 1000);
                        conector.ejecutar("update productos set existencia='" + cuenta_stock + "' where  nombre='" + producto + "'");
                    }
                } else {
                    conector.ejecutar("delete from ventas where folio='" + folio + "' and codigo='" + codigo + "' ");
                    if (obten_medida.equals("Pz")) {
                        cuenta_stock = existencia_acc + (conv_cantidad);
                        conector.ejecutar("update productos set existencia='" + cuenta_stock + "' where  nombre='" + producto + "'");
                    } else {
                        cuenta_stock = existencia_acc + (conv_cantidad / 1000);
                        conector.ejecutar("update productos set existencia='" + cuenta_stock + "' where  nombre='" + producto + "'");
                    }
                }
            }
            prin.obtiene_tabla().setModel(modelo);
            String convierteTotal = Float.toString(total);
            prin.obtiene_total().setText(convierteTotal);
            String conv_contador = Float.toString(contador);
            prin.obtiene_contador().setText(conv_contador);
            prin.buscar.requestFocus();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al Eliminar " + ex);
        }

    }

    public void eliminar_x_busqueda(String producto) {
        Vector vect = modelo.getDataVector();
        //int fil_sel=;
        // System.out.println("odtuve indice "+fil_sel);
    }

    public void nueva() {
        try {
            modelo = (DefaultTableModel) prin.obtiene_tabla().getModel();
            int filas = prin.obtiene_tabla().getRowCount();
            for (int i = 0; filas > i; i++) {
                modelo.removeRow(0);
                contador = 0;
                insert_ind = 0;
                total = 0;
                NumFactura = rand.nextInt(100000);

                folio = "" + year + "" + mes + "" + dia + "" + NumFactura;
                prin.obtiene_contador().setText(null);
                prin.obtiene_total().setText(null);
                prin.obtiene_folio().setText(folio);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al Eliminar filas " + ex);
        }
    }

    public void cancelar(String folio) {
        try {

            Statement esta1 = conector.conexion.createStatement();
            ResultSet res0 = esta1.executeQuery("select * from ventas where folio='" + folio + "'");
            // conector.Consulta();
            //conector.Consulta("select * from ventas where folio='" + folio + "'");
            while (res0.next()) {
                codigo_cancel = res0.getString("codigo");
                producto_cancel = res0.getString("descripcion");
                cantidad_cancel = res0.getFloat("cantidad");
                /*Statement sta_verificar = prin.conx.createStatement();
            ResultSet res_verificar = sta_verificar.executeQuery("SELECT * FROM productos WHERE codigo='" + code + "'");*/
                Statement esta = conector.conexion.createStatement();
                if (codigo_cancel.isEmpty()) {
                    res = esta.executeQuery("select * from productos where  nombre='" + producto_cancel + "'");
                    // conector.Consulta();
                } else {
                    res = esta.executeQuery("select * from productos where codigo='" + codigo_cancel + "' ");

                }

                while (res.next()) {
                    existencia_cancel = res.getFloat("existencia");
                }
                suma_existencias = existencia_cancel + cantidad_cancel;
                if (codigo_cancel.isEmpty()) {
                    conector.ejecutar("update productos set existencia='" + suma_existencias + "' where  nombre='" + producto_cancel + "'");
                } else {
                    conector.ejecutar("update productos set existencia='" + suma_existencias + "' where codigo='" + codigo_cancel + "' ");

                }
            }
            conector.ejecutar("delete from ventas where folio='" + folio + "'");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al Eliminar venta " + ex);

        }
    }

    public void eliminar_de_folio(String folio, String codigo, String producto, String cantidad, String cant_elim, String unidad) {
        try {

            float parse_cant = Float.parseFloat(cantidad);
            float parse_cant_elim = Float.parseFloat(cant_elim);
            float sum_cant, exist_p = 0, sum_exits = 0;

            if (codigo.isEmpty()) {
                conector.Consulta("select * from productos where  nombre='" + producto + "'");
            } else {
                conector.Consulta("select * from productos where codigo='" + codigo + "' ");
            }
            while (conector.rst.next()) {
                exist_p = conector.rst.getFloat("existencia");
            }
            if (unidad.equals("Pz")) {
                sum_cant = parse_cant - parse_cant_elim;
                System.out.println("comprobar existencia " + exist_p + "    " + parse_cant_elim + "   cod " + codigo.length());
                sum_exits = (exist_p + parse_cant_elim);
                if (codigo.isEmpty()) {
                    conector.ejecutar("update productos set existencia='" + sum_exits + "' where  nombre='" + producto + "'");
                } else {
                    conector.ejecutar("update productos set existencia='" + sum_exits + "' where codigo='" + codigo + "'");
                }
            } else {
                sum_cant = ((parse_cant * 1000) - (parse_cant_elim)) / 1000;
                System.out.println("comprobar existencia " + exist_p + "    " + parse_cant_elim / 1000 + "  cod " + codigo.length());
                sum_exits = (exist_p + (parse_cant_elim / 1000));
                if (codigo.isEmpty()) {
                    conector.ejecutar("update productos set existencia='" + sum_exits + "' where nombre='" + producto + "'");
                } else {
                    conector.ejecutar("update productos set existencia='" + sum_exits + "' where  codigo='" + codigo + "'");
                }
            }

            if (sum_cant > 0) {
                if (codigo.isEmpty()) {
                    conector.ejecutar("update ventas set cantidad='" + sum_cant + "' where folio='" + folio + "' and descripcion='" + producto + "' ");
                }//conector.ejecutar("update productos set existencia='"++"'");
                else {
                    conector.ejecutar("update ventas set cantidad='" + sum_cant + "' where  folio='" + folio + "' and codigo='" + codigo + "' ");
                }
            } else {
                if (codigo.isEmpty()) {
                    conector.ejecutar("delete from ventas where folio='" + folio + "'  and descripcion='" + producto + "' ");
                } else {
                    conector.ejecutar("delete from ventas where  folio='" + folio + "' and codigo='" + codigo + "' ");
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al Eliminar venta " + ex);

        }

    }

}
