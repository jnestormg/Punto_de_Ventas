/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pointsale;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileFilter;
import java.util.Calendar;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

public class Principal extends javax.swing.JFrame implements ActionListener {

    crud_productos save = new crud_productos();
    conexion conector;
    Imprimir_Ticket imp = new Imprimir_Ticket();
    public Acciones metodos = new Acciones(this);
    JMenuItem item1, item2, item_eliminar, item_modificar, item_eliminar_folio_produc, item_eliminar_folio;
    JPopupMenu popup, popup2, popup3;
    int indice, cont_col_modelo = 0, indice2, indice3;
    String precio, codigo;
    String parse_camb,serial_num;
    float parse_total, recibe, camb;
    DefaultTableModel modelobuscar2;
    int second = 1000, cont_serial;
    public Timer tiempo;
    // public Connection conx = conector.conexion;
    public static Calendar cal = Calendar.getInstance();
    int dia, mes, year, estado_p;
    String esta_year, esta_mes, esta_edo;

    public Principal() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.metodos = new Acciones(this);
        buscar.requestFocus();
        conector = new conexion();
       
        mostrardatos();
        tiempo = new Timer(second, new TimerListener());

        activar();
        instalacion();
         poner_mes();
        // encender_on_top();

    }
    public void instalacion() {
        try {
            year = cal.get(Calendar.YEAR);
            mes = cal.get(Calendar.MONTH) + 1;
            dia = cal.get(Calendar.DAY_OF_MONTH);
            String fecha_ini = null;

            if (mes >= 10) {
                fecha_ini = year + "-" + mes + "-" + dia;
            } else {
                fecha_ini = year + "-0" + mes + "-" + dia;

            }
            System.out.println("fechando " + fecha_ini);
            String fecha_fin;
            if ((mes + 1) == 13) {
                fecha_fin = (year + 1) + "-" + (1) + "-" + dia;
            } else {
                fecha_fin = year + "-" + (mes + 1) + "-" + dia;
            }
            ResultSet res = conector.Consulta("select count(*) from instalacion");
            while (res.next()) {
                estado_p = res.getInt(1);
            }

            if (estado_p == 0) {
                conector.ejecutar("insert into instalacion(fecha,fecha_limite,year,mes,estado,serial)"
                        + " values('" + fecha_ini + "','" + fecha_fin + "','" + year + "','" + mes + "','activo','')");
            } else {
                conector.Consulta("select * from instalacion");
                while (conector.rst.next()) {
                    esta_year = conector.rst.getString("fecha");
                    esta_mes = conector.rst.getString("fecha_limite");
                    esta_edo = conector.rst.getString("estado");
                    serial_num=conector.rst.getString("serial");
                   System.out.println(esta_year + esta_mes + esta_edo+"serial:"+serial_num);
                }
                
                
                if (serial_num.trim().isEmpty()) {
                    if (fecha_ini.equals(esta_year) && esta_edo.equals("activo")) {
                        //JOptionPane.showMessageDialog(null, "Version de Prueba");
                    } else if (fecha_ini.equals(esta_mes)) {
                        JOptionPane.showMessageDialog(null, "Version de Prueba ha Vencido");
                        conector.ejecutar("update instalacion set estado='inactivo' ");
                        System.exit(0);

                    } else if (esta_edo.equals("inactivo")) {
                        JOptionPane.showMessageDialog(null, "Version de Prueba ha Vencido 1");
                        System.exit(0);
                    }
                     jMenuItem3.setVisible(false);
                    jMenuItem2.setVisible(false);
                     jMenuItem7.setVisible(false);
                     jButton1.setVisible(false);

                } else if (serial_num.trim().equals("n1989-r1966-a1990")) {
                    jProgressBar1.setValue(0);
                    jProgressBar1.setVisible(false);
                    jMenuItem8.setVisible(false);
                    jMenuItem3.setVisible(true);
                    jMenuItem2.setVisible(true);
                      jMenuItem7.setVisible(true);
                    jButton1.setVisible(true);
                    desactivar();
                }
              
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    class TimerListener implements ActionListener {

        int cont, cont_borrar;

        public void actionPerformed(ActionEvent ev) {
            cont++;
            jProgressBar1.setValue(cont);
            if (cont == 1000) {
                System.exit(0);
                try {

                } catch (Exception e) {
                    System.out.print("error" + e);
                }

            }

        }

    }

    public void activar() {
        tiempo.start();
    }
    public void desactivar(){
    tiempo.stop();}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        buscar = new org.jdesktop.swingx.JXSearchField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_venta = new org.jdesktop.swingx.JXTable();
        imprimir = new javax.swing.JButton();
        total = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        contador = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        mensaje = new org.jdesktop.swingx.JXLabel();
        folio_p = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        volver = new javax.swing.JLabel();
        recibido = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        eticambio = new javax.swing.JLabel();
        cambio = new org.jdesktop.swingx.JXLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jXTitledPanel1 = new org.jdesktop.swingx.JXTitledPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        code_p = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        name_p = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        price_p = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        stock_p = new javax.swing.JTextField();
        unit_p = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        measures_p = new javax.swing.JComboBox<>();
        guardar_producto = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabla_productos = new org.jdesktop.swingx.JXTable();
        busca_producto = new org.jdesktop.swingx.JXSearchField();
        jLabel10 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        busca_folio = new org.jdesktop.swingx.JXSearchField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabla_mostrar_ventas = new org.jdesktop.swingx.JXTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        total_vendido = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cant_vendidos = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        select_mes = new com.toedter.calendar.JMonthChooser();
        jLabel16 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        cancela = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Punto de Venta");
        setUndecorated(true);

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/barcodes.png"))); // NOI18N
        jLabel1.setText("CÓDIGO:");

        buscar.setToolTipText("Buscar Producto");
        buscar.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        buscar.setPrompt("Ingresar Código ");
        buscar.setSearchMode(null);
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });
        buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscarKeyPressed(evt);
            }
        });

        tabla_venta.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        tabla_venta.getTableHeader().setReorderingAllowed(false);
        tabla_venta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_ventaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabla_ventaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabla_ventaMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_venta);

        imprimir.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimirletra.png"))); // NOI18N
        imprimir.setText("IMPRIMIR");
        imprimir.setEnabled(false);
        imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirActionPerformed(evt);
            }
        });
        imprimir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                imprimirKeyPressed(evt);
            }
        });

        total.setEditable(false);
        total.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel8.setText("Total: $");

        contador.setEditable(false);
        contador.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setText("Cantidad ");

        mensaje.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        mensaje.setLineWrap(true);

        folio_p.setVisible(false);

        jProgressBar1.setMaximum(1000);

        volver.setText("Volver");
        volver.setVisible(false);
        volver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                volverMouseClicked(evt);
            }
        });

        recibido.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        recibido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 51), 2));
        recibido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recibidoActionPerformed(evt);
            }
        });
        recibido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                recibidoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                recibidoKeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel14.setText("Recibido: $");

        eticambio.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        eticambio.setForeground(new java.awt.Color(0, 153, 0));
        eticambio.setText("Cambio: $");
        eticambio.setVisible(false);

        cambio.setForeground(new java.awt.Color(0, 153, 0));
        cambio.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(volver, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(folio_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(contador, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(recibido, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(eticambio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cambio, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(folio_p, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(volver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(jLabel9)
                        .addComponent(jLabel14))
                    .addComponent(contador, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(recibido, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(imprimir)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(eticambio))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cambio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(45, 45, 45))
        );

        jScrollPane1.setViewportView(jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Venta", jPanel1);

        jXTitledPanel1.setTitle("Agregar Producto");

        code_p.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        code_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                code_pActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText(" Código:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("* Nombre:");

        name_p.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setText("* Precio:");

        price_p.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setText("* Existencias:");

        stock_p.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        unit_p.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        unit_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unit_pActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setText("* Cantidad Unitaria:");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setText("Unidad de Medida:");

        measures_p.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        measures_p.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pz", "Kg", "Lt" }));

        guardar_producto.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        guardar_producto.setText("Guardar");
        guardar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardar_productoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(112, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(name_p, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(code_p, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(stock_p, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(price_p, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(unit_p, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(guardar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(measures_p, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(153, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(code_p, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name_p, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(price_p, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stock_p, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(measures_p, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(unit_p, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(guardar_producto)
                .addContainerGap())
        );

        jScrollPane4.setViewportView(jPanel5);

        javax.swing.GroupLayout jXTitledPanel1Layout = new javax.swing.GroupLayout(jXTitledPanel1.getContentContainer());
        jXTitledPanel1.getContentContainer().setLayout(jXTitledPanel1Layout);
        jXTitledPanel1Layout.setHorizontalGroup(
            jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1046, Short.MAX_VALUE)
        );
        jXTitledPanel1Layout.setVerticalGroup(
            jXTitledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXTitledPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabla_productos.setGridColor(new java.awt.Color(51, 102, 255));
        tabla_productos.getTableHeader().setReorderingAllowed(false);
        tabla_productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabla_productosMouseReleased(evt);
            }
        });
        jScrollPane5.setViewportView(tabla_productos);

        busca_producto.setToolTipText("Buscar");
        busca_producto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        busca_producto.setPrompt("Ingresar Código o Nombre del Producto");
        busca_producto.setSearchMode(null);
        busca_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                busca_productoKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Buscar:");

        jButton4.setText("Mostrar Todo");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXTitledPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(busca_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jButton4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXTitledPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(busca_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        jScrollPane3.setViewportView(jPanel4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Productos", jPanel2);

        busca_folio.setToolTipText("Buscar Folio");
        busca_folio.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        busca_folio.setPrompt("Buscar Folio ");
        busca_folio.setSearchMode(null);
        busca_folio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busca_folioActionPerformed(evt);
            }
        });

        tabla_mostrar_ventas.getTableHeader().setReorderingAllowed(false);
        tabla_mostrar_ventas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabla_mostrar_ventasMouseReleased(evt);
            }
        });
        tabla_mostrar_ventas.setEditable(false);
        jScrollPane6.setViewportView(tabla_mostrar_ventas);

        jLabel11.setText("Folio:");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel13.setText("Total Vendido: $");

        total_vendido.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel15.setText("Cantidad de Productos Vendidos:");

        cant_vendidos.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        select_mes.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                select_mesPropertyChange(evt);
            }
        });

        jLabel16.setText("Mostrar Ventas del Mes:");

        jButton1.setText("Gráfica de Mes ");
        jButton1.setVisible(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(total_vendido, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(cant_vendidos, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(25, 25, 25)
                        .addComponent(busca_folio, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(select_mes, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)))
                .addGap(25, 25, 25))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(busca_folio, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(jButton2)
                        .addComponent(jLabel16))
                    .addComponent(select_mes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(total_vendido, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cant_vendidos, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(90, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consultar Ventas", jPanel6);

        menu.setText("Menú");

        jMenuItem3.setText("Exportar Productos");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menu.add(jMenuItem3);

        jMenuItem2.setText("Importar Productos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menu.add(jMenuItem2);

        jMenuItem7.setText("Datos del Negocio");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        menu.add(jMenuItem7);

        jMenuItem8.setText("Agregar Serial");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        menu.add(jMenuItem8);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setText("Eliminar Producto");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        menu.add(jMenuItem9);

        cancela.setText("Cancelar Venta");
        cancela.setEnabled(false);
        cancela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelaActionPerformed(evt);
            }
        });
        menu.add(cancela);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menu.add(jMenuItem1);

        jMenuBar1.add(menu);

        jMenu2.setText("Ventana");

        jMenuItem4.setText("Pantalla Completa");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Reducir Pantalla");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Ayuda");

        jMenuItem6.setText("Documentación");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        try {
            if (tabla_venta.getRowCount() > 0) {
                JOptionPane.showMessageDialog(null, "Cancelar Venta o Finalizar Venta");
            } else {
                System.exit(0);
            }

        } catch (Exception ex) {
        }

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void guardar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardar_productoActionPerformed
        // TODO add your handling code here:
        try {
            if (name_p.getText().trim().length() > 0 && price_p.getText().trim().length() > 0 && stock_p.getText().trim().length() > 0 && unit_p.getText().trim().length() > 0) {
                save.guardando(code_p.getText().trim(), name_p.getText().trim(), price_p.getText().trim(), stock_p.getText().trim(), unit_p.getText().trim(), measures_p.getSelectedItem().toString());
            } else {
                JOptionPane.showMessageDialog(null, "Todos los Campos Marcados son Requeridos");
            }
            code_p.setText(null);
            name_p.setText(null);
            price_p.setText(null);
            stock_p.setText(null);
            unit_p.setText(null);
            measures_p.setSelectedItem("Unidad");
            mostrardatos();
            code_p.requestFocus();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Guar_ent " + ex);
        }
    }//GEN-LAST:event_guardar_productoActionPerformed


    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        // TODO add your handling code here
        try {
            //   this.setAlwaysOnTop(true);

            cont_col_modelo = 0;
            String entra_cantidad = JOptionPane.showInputDialog(null, "Cantidad", "Ingresar Cantidad", 1);
            float cantid_verificar = Float.parseFloat(entra_cantidad.trim());
            metodos.verificar_codigo(buscar.getText().trim(), "".trim(), cantid_verificar);
            buscar.setText(null);
            System.out.println("col_cont " + cont_col_modelo);
             poner_mes();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Entrada de datos 1 " + ex);
            buscar.setText(null);
            buscar.requestFocus();
        }
    }//GEN-LAST:event_buscarActionPerformed

    private void buscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyPressed

        try {

            if ((buscar.getText().contains("a") || buscar.getText().contains("e") || buscar.getText().contains("i") || buscar.getText().contains("o") || buscar.getText().contains("u"))) {
                // Statement sta_busca_palabra = prin.conx.createStatement();
                ResultSet buscar_por_nombre = conector.Consulta("SELECT (codigo),(nombre),(precio),(existencia),(unid_med) FROM productos WHERE nombre LIKE '%" + buscar.getText().trim() + "%'");
                modelobuscar2 = new DefaultTableModel(new String[]{"Código", "Descripcion", "Precio", "Existencias", "Unidad Medida"}, 0);
                // this.tabla_venta.setModel(modelobuscar);
                ResultSetMetaData rsMd = buscar_por_nombre.getMetaData();
                //La cantidad de columnas que tiene la consulta
                int cantidadColumnas = rsMd.getColumnCount();

                while (buscar_por_nombre.next()) {
                    Object[] fila = new Object[cantidadColumnas];
                    for (int i = 0; i < cantidadColumnas; i++) {
                        fila[i] = buscar_por_nombre.getObject(i + 1);
                    }
                    modelobuscar2.addRow(fila);
                }
                tabla_venta.setModel(modelobuscar2);
                tabla_venta.getColumnModel().getColumn(1).setPreferredWidth(300);

                cont_col_modelo = modelobuscar2.getRowCount();
               

                if (modelobuscar2.getRowCount() == 0) {//regresar a la tabla sino encuentra coincencias
                    //  tabla_venta.setModel(metodos.modelo);
                    // buscar.setText(null);
                    tabla_venta.setModel(metodos.modelo);
                    tabla_venta.getColumnModel().getColumn(1).setPreferredWidth(300);

                } else {

                }
                volver.setVisible(true);
            } else {
                tabla_venta.setModel(metodos.modelo);
                tabla_venta.getColumnModel().getColumn(1).setPreferredWidth(300);
                volver.setVisible(false);
                cont_col_modelo = 0;
            }
            System.out.println("col_cont " + cont_col_modelo);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Entrada de datos 3" + ex);
            tabla_venta.setModel(metodos.modelo);
            tabla_venta.getColumnModel().getColumn(1).setPreferredWidth(300);
        }


    }//GEN-LAST:event_buscarKeyPressed

    private void tabla_ventaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ventaMouseReleased
        // TODO add your handling code here:
        System.out.println("cantidad de filas" + tabla_venta.getRowCount());
        if (tabla_venta.getRowCount() > 0) {
            //  popup.add(item2);
            item1 = new JMenuItem("Eliminar");
            item1.addActionListener(this);
            // item2 = new JMenuItem("Cambiar Precio");
            //item2.setToolTipText("Cambiar el precio por unidad");
            //item2.addActionListener(this);
            popup = new JPopupMenu();
            popup.add(item1);
            System.out.println("col  " + cont_col_modelo);

            tabla_venta.setComponentPopupMenu(popup);

        } else {
            tabla_venta.setComponentPopupMenu(null);
        }


    }//GEN-LAST:event_tabla_ventaMouseReleased

    private void unit_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unit_pActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unit_pActionPerformed

    private void code_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_code_pActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_code_pActionPerformed

    private void imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimirActionPerformed
        // TODO add your handling code here:
        try {
            System.out.print("cantidad de filas" + tabla_venta.getRowCount());
            if (tabla_venta.getRowCount() > 0 ) {
                if(recibido.getText().trim().isEmpty()){
                mensaje.setText("No se ha recibido Ninguna Cantidad");
                mensaje.setForeground(Color.red);
                recibido.requestFocus();
                }
                else{
                // System.out.println(folio_f + "  " + total_f + " " + "   " + recibido.getText().toString().trim());
                //imp.Imprimir(folio_f, total_f, recibido.getText().toString().trim());
                imp.Imprimir(folio_p.getText(), total.getText().trim(), recibido.getText().toString().trim());
                metodos.nueva();
                buscar.setText(null);
                buscar.requestFocus();
                imprimir.setEnabled(false);
                mensaje.setText(null);
                recibido.setText(null);
                recibido.setEditable(false);
                recibido.setBorder(null);
                eticambio.setVisible(false);
                cambio.setVisible(false);
                cancela.setEnabled(false);
                }
                //this.dispose();

            } else {
                //JOptionPane.showMessageDialog(null, "No hay nada que Imprimir");
                mensaje.setText("No hay nada que Imprimir");
                
            }
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_imprimirActionPerformed

    private void busca_productoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busca_productoKeyPressed
        // TODO add your handling code here:
        buscar_producto(busca_producto.getText().trim());
    }//GEN-LAST:event_busca_productoKeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        mostrardatos();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tabla_ventaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ventaMouseClicked
        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(null, "clic ");


    }//GEN-LAST:event_tabla_ventaMouseClicked

    private void tabla_ventaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ventaMousePressed
        // TODO add your handling code here:
        try {
            if (cont_col_modelo > 0) {
                cont_col_modelo = 0;

                String entra_cantidad = null;

                indice = tabla_venta.getSelectedRow();
                System.out.println(indice + " num_indice");
                String product = tabla_venta.getValueAt(indice, 1).toString();
                String codigo_prin = tabla_venta.getValueAt(indice, 0).toString();
                String uni_med = tabla_venta.getValueAt(indice, 4).toString();

                if (uni_med.trim().equals("Pz")) {
                    entra_cantidad = JOptionPane.showInputDialog(null, "Ingresar Cantidad de Piezas", "Agregar " + product, 1);
                } else if (uni_med.trim().equals("Kg")) {
                    entra_cantidad = JOptionPane.showInputDialog(null, "Ingresar Cantidad en Gramos", "Agregar " + product, 1);
                } else if (uni_med.trim().equals("Lt")) {
                    entra_cantidad = JOptionPane.showInputDialog(null, "Ingresar Cantidad en Mililitros", "Agregar " + product, 1);
                }
                float cantid_verificar = Float.parseFloat(entra_cantidad.trim());
                if (codigo_prin.trim().length() == 0 && product.trim().length() > 0) {
                    metodos.verificar_codigo("".trim(), product.trim(), cantid_verificar);
                    System.out.println("pasando producto" + product);

                } else if (codigo_prin.trim().length() > 0 && product.trim().length() > 0) {
                    metodos.verificar_codigo(codigo_prin.trim(), codigo_prin, cantid_verificar);
                    System.out.println("pasando codigo" + codigo_prin);
                }
                  poner_mes();
                buscar.setText(null);
                buscar.requestFocus();
                volver.setVisible(false);
                imprimir.setEnabled(true);
                //recibido.setEditable(true);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en tabla al buscar " + ex.getMessage());
            buscar.setText(null);
            buscar.requestFocus();
            tabla_venta.setModel(metodos.modelo);
            tabla_venta.getColumnModel().getColumn(1).setPreferredWidth(300);
        }
    }//GEN-LAST:event_tabla_ventaMousePressed

    /*public void mostrar_por_fecha() {
        try {
            long t2 = fecha_p.getDate().getTime();
            // String dat = fecha_p.getDate().toString();
            java.util.Date sqlDates = new java.sql.Date(t2);
            String sqlDats = sqlDates.toString();
            System.out.print("Fecha selecionada"+sqlDats);
            mostrardatos_venta_fecha(sqlDats,"".trim(),"".trim());
            float canti_vent = 0;
            conector.Consulta("select sum(cantidad) from ventas where fecha='" + sqlDats + "'");
            while (conector.rst.next()) {
                canti_vent = conector.rst.getFloat(1);
            }
            String parse_cant_vent = Float.toString(canti_vent);
            cant_vendidos.setText(parse_cant_vent);
            float total_vent = 0;
            conector.Consulta("select sum(precio*cantidad) from ventas where fecha='" + sqlDats + "'");
            while (conector.rst.next()) {
                total_vent = conector.rst.getFloat(1);
            }
            String parse_total_vent = Float.toString(total_vent);
            total_vendido.setText(parse_total_vent);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }*/

     public void mostrar_por_mes(String mes) {
        try {
            
            mostrardatos_venta_fecha("".trim(), "".trim(),mes);
            float canti_vent = 0;
            conector.Consulta("select sum(cantidad) from ventas where mes='" + mes + "'");
            while (conector.rst.next()) {
                canti_vent = conector.rst.getFloat(1);
            }
            String parse_cant_vent = Float.toString(canti_vent);
            cant_vendidos.setText(parse_cant_vent);
            float total_vent = 0;
            conector.Consulta("select sum(precio*cantidad) from ventas where mes='" + mes + "'");
            while (conector.rst.next()) {
                total_vent = conector.rst.getFloat(1);
            }
            String parse_total_vent = Float.toString(total_vent);
            total_vendido.setText(parse_total_vent);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    private void tabla_productosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_productosMouseReleased
        // TODO add your handling code here:
        item_eliminar = new JMenuItem("Eliminar");
        item_eliminar.addActionListener(this);
        item_modificar = new JMenuItem("Modificar");
        //item2.setToolTipText("Cambiar el precio por unidad");
        item_modificar.addActionListener(this);
        popup2 = new JPopupMenu();
        popup2.add(item_eliminar);
        popup2.add(item_modificar);

        tabla_productos.setComponentPopupMenu(popup2);
    }//GEN-LAST:event_tabla_productosMouseReleased

    private void cancelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelaActionPerformed
        // TODO add your handling code here:

        if (JOptionPane.showOptionDialog(this, "¿Desea Cancelar La Venta Actual\nPara Crear Nueva?", "Borrar Venta ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{" SI ", " NO "}, "NO") == 0) {
            metodos.cancelar(folio_p.getText().toString());
            metodos.nueva();
            buscar.setText(null);
            buscar.requestFocus();
            imprimir.setEnabled(false);
            eticambio.setVisible(false);
            cambio.setVisible(false);
            cambio.setText(null);
            recibido.setText(null);
            recibido.setEditable(false);
            recibido.setBorder(null);
            poner_mes();

            if (tabla_venta.getRowCount() > 0) {
                cancela.setEnabled(true);
            } else {
                cancela.setEnabled(false);
            }
        }

    }//GEN-LAST:event_cancelaActionPerformed

    private void busca_folioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busca_folioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_busca_folioActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {

            mostrar_por_folio();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    public void mostrar_por_folio() {
        try {

            mostrardatos_venta_fecha("".trim(), busca_folio.getText().trim(),"".trim());
            float canti_vent = 0;
            conector.Consulta("select sum(cantidad) from ventas where folio='" + busca_folio.getText().trim() + "'");
            while (conector.rst.next()) {
                canti_vent = conector.rst.getFloat(1);
            }
            String parse_cant_vent = Float.toString(canti_vent);
            cant_vendidos.setText(parse_cant_vent);

            float total_vent = 0;
            conector.Consulta("select sum(precio*cantidad) from ventas where folio='" + busca_folio.getText().trim() + "'");
            while (conector.rst.next()) {
                total_vent = conector.rst.getFloat(1);
            }
            String parse_total_vent = Float.toString(total_vent);
            total_vendido.setText(parse_total_vent);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void tabla_mostrar_ventasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_mostrar_ventasMouseReleased
        // TODO add your handling code here:
        try {
            if (tabla_mostrar_ventas.getRowCount() > 0) {
                item_eliminar_folio_produc = new JMenuItem("Eliminar");
                item_eliminar_folio_produc.addActionListener(this);
                // item2 = new JMenuItem("Cambiar Precio");
                //item2.setToolTipText("Cambiar el precio por unidad");
                //item2.addActionListener(this);
                popup3 = new JPopupMenu();
                popup3.add(item_eliminar_folio_produc);
                tabla_mostrar_ventas.setComponentPopupMenu(popup3);
            } else {
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_tabla_mostrar_ventasMouseReleased

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

        try {
            JFileChooser fileChooser = new JFileChooser(".");
            fileChooser.setDialogTitle("Guardar Productos");
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("csv", "csv");
            fileChooser.setFileFilter(filtro);

            fileChooser.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);
            int status = fileChooser.showSaveDialog(null);
            File selectedFile = null, r = null;
            if (status == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                r = selectedFile;
                System.out.println(r.getAbsolutePath().replace("\\", "//"));
                
          //    conector.Consulta("-header -csv C:\\Users\\nestor meneses\\Ventas\\sales.db \"select * from productos;\" > f.csv");
             //conector.Consulta("-header on ");
             // conector.ejecutar(".mode csv ");
              //conector.Consulta("once C:\\Users\\nestor meneses\\Ventas\\db.csv");
             //conector.ejecutar(" .header on .mode csv .once  /Users/nestor meneses/Ventas/db.csv ");
                    conector.Consulta( "csv C:\\Users\\nestor meneses\\Ventas\\sales.db"
                            + "select * from productos; ss.csv");

            } else if (status == JFileChooser.CANCEL_OPTION) {
                System.out.println("Cancelar");
            }
           // conector.Consulta("select 'CODIGO','DESCRIPCION','PRECIO','EXISTENCIAS','CANTIDAD_UNITARIA', 'UNIDAD_DE_MEDIDA'"
             //       + " union all select "
              //      + " codigo,nombre, precio, existencia, cant_unit, unid_med from productos into outfile '" + r.getAbsolutePath().replace("\\", "//") + "." + fileChooser.getFileFilter().getDescription() + "' fields terminated by ',' lines terminated by '\r\n';");
            // JOptionPane.showMessageDialog(null, "DATOS GUARDADOS", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);

        } 
        catch(SQLException er){System.out.println(er.getMessage());}
        catch (Exception e) {
             System.out.println("Error al cargar "+e.getMessage());
        }

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:

        try {
            JFileChooser fileChooser = new JFileChooser(".");
            fileChooser.setDialogTitle("Abrir Archivo");
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("csv", "csv");
            fileChooser.setFileFilter(filtro);
            fileChooser.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);
            int status = fileChooser.showOpenDialog(null);
            File selectedFile = null, r = null;
            if (status == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                r = selectedFile;
                System.out.println("" + r.getAbsolutePath().replace("\\", "//"));

            } else if (status == JFileChooser.CANCEL_OPTION) {
                System.out.println("Cancelar");
            }

            conector.Consulta("LOAD DATA INFILE '" + r.getAbsolutePath().replace("\\", "//")
                    + "' INTO TABLE productos  FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n' IGNORE 1 LINES "
                    + "(codigo,nombre,precio,existencia,cant_unit,unid_med);");
            //JOptionPane.showMessageDialog(null, "DATOS CARGADOS", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
            mostrardatos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error cargando archivo" + e.getMessage());
            System.out.println(e.getMessage());

        }

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void volverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volverMouseClicked
        // TODO add your handling code here:
        tabla_venta.setModel(metodos.modelo);
        tabla_venta.getColumnModel().getColumn(1).setPreferredWidth(300);
        volver.setVisible(false);
        buscar.setText("");
        buscar.requestFocus();
        //cont_col_modelo = 0;

    }//GEN-LAST:event_volverMouseClicked

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, d.width, d.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        this.setSize(1129, 711);
        this.setLocationRelativeTo(null);

    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        Negocio dn = new Negocio();
        dn.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void recibidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recibidoKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_recibidoKeyPressed

    private void recibidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recibidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_recibidoActionPerformed

    private void recibidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recibidoKeyReleased
        // TODO add your handling code here:
        try {
            cambio.setVisible(true);
            eticambio.setVisible(true);
            parse_total = Float.parseFloat(total.getText().trim());
            //if(recibido.getText().length()>=){
            recibe = Float.parseFloat(recibido.getText());
            camb = recibe - parse_total;
            parse_camb = Float.toString(camb);

            if (camb >= 0) {
                cambio.setText(parse_camb);
            } else {
                cambio.setText("Cantidad Insuficiente. Faltante de: $" + (parse_total - recibe));
                //recibido.setText("");
                //recibido.requestFocus();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_recibidoKeyReleased

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        try{
            String fecha_ini;
           String ser= JOptionPane.showInputDialog("Número de Serial:");
           conector.Consulta("select count(serial) from instalacion");
           while(conector.rst.next()){
           cont_serial=conector.rst.getInt(1);
           }
           if(cont_serial>=1){
               if(ser.trim().equalsIgnoreCase("n1989-r1966-a1990")){
               //conector.ejecutar("delete from instalacion");
               
               conector.ejecutar("update instalacion set serial='"+ser+"'");
                jProgressBar1.setValue(0);
                    desactivar();
                    jProgressBar1.setVisible(false);
                    jMenuItem8.setVisible(false);
                     jMenuItem3.setVisible(true);
                    jMenuItem2.setVisible(true);
                    jMenuItem7.setVisible(true);
                    jButton1.setVisible(true);
                     JOptionPane.showMessageDialog(null, "Registro Exitoso");

           // conector.ejecutar("insert into instalar(serial) values('"+ser+"')");
               }else{
                          JOptionPane.showMessageDialog(null, "Serial Incoreccto");
               }
           }else{
          JOptionPane.showMessageDialog(null, "Ya fue Registrado");
           }
        }catch(Exception e){
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void select_mesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_select_mesPropertyChange
        // TODO add your handling code here:
      poner_mes();
    }//GEN-LAST:event_select_mesPropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         int sel_mes=select_mes.getMonth()+1;
        String par_mes=Integer.toString(sel_mes);
        Grafica_time_series gf=new Grafica_time_series();
        gf.grafica(par_mes);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void imprimirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_imprimirKeyPressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_imprimirKeyPressed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        String obj_product=JOptionPane.showInputDialog("Código o Nombre del Producto:");
        metodos.eliminar_x_busqueda(obj_product);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    
    
    public void poner_mes(){
  int sel_mes=select_mes.getMonth()+1;
        String par_mes=Integer.toString(sel_mes);
       mostrar_por_mes(par_mes);
}
    /*public void encender_on_top(){
     System.out.println("top:"+ontop.getState());
        if(ontop.getState()==true){
        this.setAlwaysOnTop(true);
        ontop.setText("Encendio");
        }
        else if(ontop.getState()==false){
        this.setAlwaysOnTop(false);
        ontop.setText("Apagado");
        }
    }*/
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == item1) {
            try {
                //conector.Consulta("");
                String cantArticulos = JOptionPane.showInputDialog("Cantidad a Eliminar");
                //  JSpinner spin=new JSpinner();
                //JOptionPane.showInputDialog(null, spin, "cantidad a eliminar", 1);
                // JOptionPane.showInputDialog("cantidad", spin);
                indice = tabla_venta.getSelectedRow();
                System.out.println(indice + " num_indice");
                precio = tabla_venta.getValueAt(indice, 2).toString();
                String cantillevada = tabla_venta.getValueAt(indice, 3).toString();
                codigo = tabla_venta.getValueAt(indice, 0).toString();
                String nom_produc = tabla_venta.getValueAt(indice, 1).toString();
                
                metodos.eliminar_producto(codigo.trim(), nom_produc.trim(), indice, precio.trim(), cantArticulos.trim(), cantillevada.trim());
                poner_mes();
                //int cantmax = (int) tabla_venta.getValueAt(indice, 2);
                // metod.eliminar(indice, preci, cantArticulos, cantillevada, descripcion2);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "error eliminando producto " + ex);
            }

        }
        if (evt.getSource() == item_eliminar) {
            indice2 = tabla_productos.getSelectedRow();
            String codigo = tabla_productos.getValueAt(indice2, 0).toString();
            String nom_produc = tabla_productos.getValueAt(indice2, 1).toString();
            if (codigo.trim().length() > 0 && nom_produc.trim().length() > 0) {
                System.out.println("eliminando 0: " + codigo + " " + nom_produc);
                save.Eliminando(codigo.trim());
                mostrardatos();

            } else if (codigo.trim().length() == 0 && nom_produc.trim().length() > 0) {
                System.out.println("eliminando: 1" + codigo + " " + nom_produc);
                save.Eliminando(nom_produc.trim());
                mostrardatos();
            }

        }
        if (evt.getSource() == item_modificar) {
            indice2 = tabla_productos.getSelectedRow();
            String codigo = tabla_productos.getValueAt(indice2, 0).toString();
            String nom_produc = tabla_productos.getValueAt(indice2, 1).toString();
            String prec_crud = tabla_productos.getValueAt(indice2, 2).toString();
            String exis_crud = tabla_productos.getValueAt(indice2, 3).toString();
            String cant_crud = tabla_productos.getValueAt(indice2, 4).toString();
            String unid_med_crud = tabla_productos.getValueAt(indice2, 5).toString();
             modificar_productos mp=new modificar_productos();
               mp.setVisible(true);
             

            if (codigo.length() > 0 && nom_produc.length() > 0) {
                System.out.println("modificando 0: " + codigo + " " + nom_produc);
               // save.modificar(codigo.trim(),nom_produc.trim(), prec_crud.trim(), exis_crud.trim(), cant_crud.trim(), unid_med_crud.trim());
              
               mp.modificar(codigo.trim(),nom_produc.trim(), prec_crud.trim(), exis_crud.trim(), cant_crud.trim(), unid_med_crud.trim());
                mostrardatos();

            } else if (codigo.length() == 0 && nom_produc.length() > 0) {
                System.out.println("modificando: 1" + codigo + " " + nom_produc + " " + prec_crud);
                //save.modificar("".trim(),nom_produc.trim(), prec_crud.trim(), exis_crud.trim(), cant_crud.trim(), unid_med_crud.trim());
                mp.modificar("".trim(),nom_produc.trim(), prec_crud.trim(), exis_crud.trim(), cant_crud.trim(), unid_med_crud.trim());
                
                mostrardatos();
            }
            mostrardatos();
        }
        if (evt.getSource() == item_eliminar_folio_produc) {

            indice3 = tabla_mostrar_ventas.getSelectedRow();
            String folio = tabla_mostrar_ventas.getValueAt(indice3, 0).toString();
            String codigo = tabla_mostrar_ventas.getValueAt(indice3, 1).toString();
            String nom_produc = tabla_mostrar_ventas.getValueAt(indice3, 2).toString();
            String cant_exist = tabla_mostrar_ventas.getValueAt(indice3, 4).toString();
            String unid_med = tabla_mostrar_ventas.getValueAt(indice3, 5).toString();
            String cant_elim = JOptionPane.showInputDialog(null, "Cantidad a Eliminar de " + nom_produc, "Folio:" + folio, 1);

            metodos.eliminar_de_folio(folio.trim(), codigo.trim(), nom_produc.trim(), cant_exist.trim(), cant_elim.trim(), unid_med.trim());
            if (busca_folio.getText().trim().length() > 0) {
                mostrar_por_folio();
            } else if (busca_folio.getText().trim().length() == 0) {
                poner_mes();
            }

        }
    }

    public JTable obtiene_tabla() {
        if (tabla_venta.getRowCount() > 0) {
            cancela.setEnabled(true);
        } else {
            cancela.setEnabled(false);
        }
        return tabla_venta;
    }

    public JTextField obtiene_total() {
        return total;
    }

    public JTextField obtiene_contador() {
        return contador;
    }

    public JTextField obtiene_recibe() {
        return recibido;
    }

    public JLabel obtiene_mensaje() {
        return mensaje;
    }

    public JLabel obtiene_folio() {
        return folio_p;
    }

    public JButton obtiene_imp() {
        return imprimir;
    }

    public JTextField obtienebuscar() {
        return buscar;
    }

    public void mostrardatos() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Producto");
        modelo.addColumn("Precio");
        modelo.addColumn("Existencias");
        modelo.addColumn("Cantidad Unitaria");
        modelo.addColumn("Unidad de Medida");
        tabla_productos.setModel(modelo);
        String sql = "";

        sql = "SELECT * FROM productos ORDER BY id_producto DESC";

        String[] datos = new String[6];
        try {

            conector.Consulta(sql);

            while (conector.rst.next()) {
                datos[0] = conector.rst.getString(2);
                datos[1] = conector.rst.getString(3).toUpperCase();
                datos[2] = conector.rst.getString(4);
                datos[3] = conector.rst.getString(5);
                datos[4] = conector.rst.getString(6);
                datos[5] = conector.rst.getString(7);

                modelo.addRow(datos);
            }
            String medidas_combo[] = {"Pz", "Kg", "Lt"};
            JComboBox combo = new JComboBox(medidas_combo);
            TableColumn tc = tabla_productos.getColumnModel().getColumn(5);
            TableCellEditor tce = new DefaultCellEditor(combo);
            tc.setCellEditor(tce);
            tabla_productos.setModel(modelo);
            tabla_productos.getColumnModel().getColumn(1).setPreferredWidth(300);

        } catch (Exception er) {
            System.out.println(er.getMessage());
        }

    }

    public void mostrardatos_venta_fecha(String fecha, String folio,String mes) {

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Folio");
        modelo.addColumn("Código");
        modelo.addColumn("Producto");
        modelo.addColumn("Precio");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Unidad Medida");
        modelo.addColumn("Fecha");
        tabla_mostrar_ventas.setModel(modelo);
        String sql = "";
        if(fecha.isEmpty() && folio.isEmpty() ){

        sql = "SELECT * FROM ventas WHERE  mes='"+mes+"' ORDER BY id_venta DESC";
        }
        else if(folio.isEmpty() && mes.isEmpty()){
          sql = "SELECT * FROM ventas WHERE  fecha='"+fecha+"' ORDER BY id_venta DESC";

        }
        else if(mes.isEmpty() && fecha.isEmpty()){
                  sql = "SELECT * FROM ventas WHERE  folio='"+folio+"' ORDER BY id_venta DESC";

        }
        
        String[] datos = new String[7];
        try {

            conector.Consulta(sql);

            while (conector.rst.next()) {
                datos[0] = conector.rst.getString(10);
                datos[1] = conector.rst.getString(2);
                datos[2] = conector.rst.getString(3).toUpperCase();
                datos[3] = conector.rst.getString(4);
                datos[4] = conector.rst.getString(5);
                datos[5] = conector.rst.getString(6);
                datos[6] = conector.rst.getString(11);

                modelo.addRow(datos);
            }
            tabla_mostrar_ventas.setModel(modelo);
            tabla_mostrar_ventas.getColumnModel().getColumn(2).setPreferredWidth(300);

        } catch (Exception er) {
            System.out.println(er.getMessage());
        }

    }

    public void buscar_producto(String producto) {
        try {

            if (producto.trim().length() > 2) {
                // Statement sta_busca_palabra = prin.conx.createStatement();
                //ResultSet buscar_por_nombre = sta_busca_palabra.executeQuery("SELECT (nombre),(precio) FROM productos WHERE nombre LIKE '%" + nombre + "%'");
                ResultSet buscar_por_nombre = conector.Consulta("select (codigo),(nombre),(precio),(existencia),(cant_unit),(unid_med) from productos where codigo like '%" + producto + "%' or nombre like '%" + producto + "%' ");

                DefaultTableModel modelobuscar = new DefaultTableModel(new String[]{"Código", "Producto", "Precio", "Existencia", "Cantidad Unitaria", "Unidad Medida"}, 0);
                // this.tabla_venta.setModel(modelobuscar);
                ResultSetMetaData rsMd = buscar_por_nombre.getMetaData();
                //La cantidad de columnas que tiene la consulta
                int cantidadColumnas = rsMd.getColumnCount();

                while (buscar_por_nombre.next()) {
                    Object[] fila = new Object[cantidadColumnas];
                    for (int i = 0; i < cantidadColumnas; i++) {
                        fila[i] = buscar_por_nombre.getObject(i + 1);
                    }

                    modelobuscar.addRow(fila);

                }

                tabla_productos.setModel(modelobuscar);
                tabla_productos.getColumnModel().getColumn(1).setPreferredWidth(300);
                String medidas_combo[] = {"Pz", "Kg", "Lt"};
                JComboBox combo = new JComboBox(medidas_combo);
                TableColumn tc = tabla_productos.getColumnModel().getColumn(5);
                TableCellEditor tce = new DefaultCellEditor(combo);
                tc.setCellEditor(tce);
                //  if (modelobuscar.getRowCount() == 0) {//regresar a la tabla sino encuentra coincencias
                //  tabla_venta.setModel(metodos.modelo);
                // buscar.setText(null);

                //}
            } //else if (buscar.getText().trim().length() < 2) {
            // tabla_venta.setModel(metodos.modelo);
            // }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "u 2" + ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXSearchField busca_folio;
    private org.jdesktop.swingx.JXSearchField busca_producto;
    public org.jdesktop.swingx.JXSearchField buscar;
    private org.jdesktop.swingx.JXLabel cambio;
    private javax.swing.JMenuItem cancela;
    private javax.swing.JLabel cant_vendidos;
    private javax.swing.JTextField code_p;
    private javax.swing.JTextField contador;
    private javax.swing.JLabel eticambio;
    private javax.swing.JLabel folio_p;
    private javax.swing.JButton guardar_producto;
    public javax.swing.JButton imprimir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.jdesktop.swingx.JXTitledPanel jXTitledPanel1;
    private javax.swing.JComboBox<String> measures_p;
    private org.jdesktop.swingx.JXLabel mensaje;
    private javax.swing.JMenu menu;
    private javax.swing.JTextField name_p;
    private javax.swing.JTextField price_p;
    private javax.swing.JTextField recibido;
    private com.toedter.calendar.JMonthChooser select_mes;
    private javax.swing.JTextField stock_p;
    private org.jdesktop.swingx.JXTable tabla_mostrar_ventas;
    public org.jdesktop.swingx.JXTable tabla_productos;
    public org.jdesktop.swingx.JXTable tabla_venta;
    private javax.swing.JTextField total;
    private javax.swing.JLabel total_vendido;
    private javax.swing.JTextField unit_p;
    private javax.swing.JLabel volver;
    // End of variables declaration//GEN-END:variables
}
