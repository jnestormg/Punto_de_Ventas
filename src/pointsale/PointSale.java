
package pointsale;

//import com.birosoft.liquid.LiquidLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
//import com.birosoft.liquid.LiquidLookAndFeel;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;


public class PointSale {
    public static String url_path;

    public static void main(String[] args) {
           
        
        try {
            
             String fol = System.getProperty("user.home") + "\\Ventas";
        File folder = new File(fol);
        folder.mkdir();
       // Dir += params.get("separador") + "sales.sqlite";
        //System.out.println(fol);

        File archi = new File(fol+"\\sales.db");
        if (archi.exists()) {
            System.out.print("ya existe");
        } else {
            FileWriter escri = new FileWriter(fol+"\\sales.db");
            PrintWriter pw = new PrintWriter(escri);
            System.out.print("archivo creado");
        }
            url_path=fol+"\\sales.db";

        
            //javax.swing.UIManager.put("Synthetica.window.decoration", true);
           // javax.swing.UIManager.put("Synthetica.extendedFileChooser.enabled", false);
            //javax.swing.UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaBlueSteelLookAndFeel");
            JFrame.setDefaultLookAndFeelDecorated(true);
           // SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.CremeSkin");
  //javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");

    //LiquidLookAndFeel.setLiquidDecorations(true, "mac");
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            //Principal prin=new Principal();
              //      prin.setVisible(true);
               conexion con=new conexion();
             con.conectar(url_path);
                    
            
        }catch(Exception ex){}
          
    }
    
}
