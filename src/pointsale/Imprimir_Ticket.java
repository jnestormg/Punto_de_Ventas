/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pointsale;

import java.util.Calendar;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

/**
 *
 * @author nestor meneses
 */
public class Imprimir_Ticket {
                String cadena, nom,dir,tel;
                int year,mes,dia,hora,minunto,segundos;
                Calendar cal;
                conexion conect=new conexion();

    public void Imprimir(String folio,String total,String recibido){
        ///fecha
        cal=Calendar.getInstance();
        year=cal.get(Calendar.YEAR);
        mes=cal.get(Calendar.MONTH)+1;
        dia=cal.get(Calendar.DAY_OF_MONTH);
        hora=cal.get(Calendar.HOUR_OF_DAY);
        minunto=cal.get(Calendar.MINUTE);
        segundos=cal.get(Calendar.SECOND);
        
        //
        System.out.println("desde ticket "+folio+ " "+total+""+recibido);
        float conv_recibido=Float.parseFloat(recibido);
        float conv_total=Float.parseFloat(total);
        try{
            
            conect.Consulta("select * from datos_negocio");
            while(conect.rst.next()){
            nom=conect.rst.getString("nombre");
            dir=conect.rst.getString("direccion");
            tel=conect.rst.getString("telefono");
            }
            
            cadena =""+(char)27+(char)112+(char)0+(char)10+(char)100;//codigo para sacar caja registradora
            cadena +="\t"+nom+"\n\t"+dir+"\n\t\t"+tel+"\n";
            cadena +="Folio: "+folio;
            cadena +="\nFecha: "+year+"/"+mes+"/"+dia+"  "+hora+":"+minunto+":"+segundos;
            cadena += "\n---------------------------------------------------";
            cadena +="\nDESCRIPCION \tPRECIO\tCANTIDAD";
            conect.Consulta("select * from ventas where folio='"+folio+"' order by id_venta desc");
            while(conect.rst.next()){
            cadena +="\n*"+conect.rst.getString("descripcion").toUpperCase()+"\n\t      $ " 
                   +conect.rst.getFloat("precio") + "\t   " + conect.rst.getFloat("cantidad")+" "+conect.rst.getString("unidad_medida");
            }
             cadena += "\n---------------------------------------------------"
                    + "\nTOTAL: $" + total
                    + "\nRECIBIDO: $"+recibido;

             
             cadena +="\nCAMBIO: $"+(conv_recibido-conv_total);
            
              DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            //Aqui selecciona tu impresora, el ejemplo tomará la impresora predeterminada.
            PrintService service = PrintServiceLookup.lookupDefaultPrintService();
            DocPrintJob pj = service.createPrintJob();
            byte[] bytes = cadena.getBytes();
            Doc doc = new SimpleDoc(bytes, flavor, null);

            try {
                pj.print(doc, null);

            } catch (PrintException e) {
            }
        
        }
        catch(Exception ex){
        
        }
    
    }
    
}
