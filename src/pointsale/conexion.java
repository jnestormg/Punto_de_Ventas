
package pointsale;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class conexion {
    public static Connection conexion;

    public static Statement  sentSQL;
    public static ResultSet rst;
    public static String url_path;
/*public void conexion(){
    try {
 Class.forName("org.sqlite.JDBC");
        //Class.forName("com.mysql.jdbc.Driver");
        
        conectar(crear_archivos()); //quitar parametros para mysql
        
    } catch (ClassNotFoundException ex) {
        JOptionPane.showMessageDialog(null, ex);
    } 
    catch (Exception er) {
        JOptionPane.showMessageDialog(null, er);
    }
*/

    public void conectar(String urls) {
        try {
           
           Class.forName("org.sqlite.JDBC");
            /* String host = "jdbc:mysql://localhost:3306/sales";
Syste
            String usuario = "root";
            String password = "";
           conexion = DriverManager.getConnection(host, usuario, password);*/
            //String url = "C:\\Users\\nestor meneses\\Documents\\NetBeansProjects\\PointSale\\sales.db";
            conexion = DriverManager.getConnection("jdbc:sqlite:" +urls);
            //sentSQL=conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            sentSQL = conexion.createStatement();
            
            
            Crear_tablas();// creando tablas
            Principal prin=new Principal();
                    prin.setVisible(true);
            System.out.println("conexion exitosa"+urls);
        }
         catch (ClassNotFoundException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
        catch (Exception er) {
            JOptionPane.showMessageDialog(null, er);
        }
    }
   public String crear_archivos() throws IOException{
        
       String fol = System.getProperty("user.home") + "\\Ventas";
       File folder = new File(fol);
       folder.mkdir();
       // Dir += params.get("separador") + "sales.sqlite";
       //System.out.println(fol);

       File archi = new File(fol + "\\sales.db");
       if (archi.exists()) {
           System.out.print("ya existe");
       } else {
           FileWriter escri = new FileWriter(fol + "\\sales.db");
           PrintWriter pw = new PrintWriter(escri);
           System.out.print("archivo creado");
       }
       return fol + "\\sales.db";
   }
public void Crear_tablas() throws SQLException{
sentSQL.executeUpdate("CREATE TABLE IF NOT EXISTS productos ("
                    + "  id_producto  integer NOT NULL PRIMARY KEY AUTOINCREMENT,"
                    + "  codigo       varchar(130),"
                    + "  nombre       varchar(250),"
                    + "  precio       float(50),"
                    + "  existencia   float(50),"
                    + "  cant_unit    float(50),"
                    + "  unid_med     varchar(10)"
                    + ");");
            sentSQL.executeUpdate("CREATE TABLE  IF NOT EXISTS ventas ("
                    + "  id_venta       integer NOT NULL PRIMARY KEY AUTOINCREMENT,"
                    + "  codigo         varchar(250),"
                    + "  descripcion    varchar(250),"
                    + "  precio         float(50),"
                    + "  cantidad       float(50),"
                    + "  unidad_medida  varchar(50),"
                    + "  indice         int,"
                    + "  dia            int,"
                    + "  mes            int,"
                    + "  year           int,"
                    + "  folio          varchar(50),"
                    + "  fecha          date"
                    + ");");
            sentSQL.executeUpdate("CREATE TABLE IF NOT EXISTS datos_negocio ("
                    + "  id         integer NOT NULL PRIMARY KEY AUTOINCREMENT,"
                    + "  nombre     varchar(250) NOT NULL,"
                    + "  direccion  varchar(150),"
                    + "  telefono   varchar(50)"
                    + ");");
            sentSQL.executeUpdate("CREATE TABLE IF NOT EXISTS instalacion ("
                    + "  fecha         date,"
                    + "  fecha_limite  date,"
                    + "  year          int,"
                    + "  mes           int,"
                    + "  estado        varchar(50),"
                    + "  serial        varchar(50)"
                    + ");");
}
    public void  ejecutar(String sql) throws SQLException
		  {
                      //executeUpdate nos sirve para hacer UPDATE, INSERT y ese tipo de sentencias que modifican la base de datos
                      // no se necesita devolver algo
                       sentSQL.executeUpdate(sql);
                       
		  }
     
     //metodo para ejecutar consultas de sentencias sql en la base de datos
     public ResultSet Consulta(String sql) throws SQLException{
         
         //executeQuery nos sirve solamente para hacer consultas de la base de datos, solo acepta sentencias como SELECT
         // se necesita forzosamente devolver algo, guardar en una variable el resultado de la consulta
        rst=sentSQL.executeQuery(sql);
        
       return rst;
    }

}
