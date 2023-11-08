/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pointsale;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/**
 *
 * @author nestor meneses
 */
public class Grafica_time_series {
    
     String dia, mes, ano, precio, num;
    int d, m, y, s;
    public double sum, pre;
    private Calendar cal;
    int yea,me;

    //int di = cal.get(Calendar.DAY_OF_MONTH);
    //java.util.Date fecha = new java.util.Date();
    conexion con;
  public  Grafica_time_series(){
       
       cal = Calendar.getInstance();
    yea = cal.get(Calendar.YEAR);
     me = cal.get(Calendar.MONTH) + 1;
    int semana = cal.get(Calendar.WEEK_OF_YEAR);
    String sem = Integer.toString(semana);
    }

    public void grafica(String mes_ob) {
con = new conexion();
        TimeSeries pop = new TimeSeries("Ganancias", Day.class);
      //  TimeSeries pop2 = new TimeSeries("Ganancias", Week.class);
        try {
             con.Consulta("select * from ventas where year='" + yea + "' AND mes='" + mes_ob + "'");
       //  ResultSet res = estado.executeQuery("SELECT * FROM ventas WHERE year='" + yea + "' AND mes='" + me + "'  AND semana='" + sem + "'");
            while (con.rst.next()) {
                dia = con.rst.getString("dia");
                mes = con.rst.getString("mes");
                ano = con.rst.getString("year");

            
                d = Integer.parseInt(dia);
                m = Integer.parseInt(mes);
                y = Integer.parseInt(ano);
                //Statement esta2 = con.createStatement();
                 con.Consulta("SELECT SUM(precio*cantidad) FROM ventas WHERE dia='" + dia + "' AND mes='" + mes + "' AND year='" + ano + "'");
                while (con.rst.next()) {
                    //precio = res.getString("precio");
                    //pre = Double.parseDouble(precio);
                  //  num = res2.getString(1);
                    //sum=Double.parseDouble(num);
                    sum = con.rst.getDouble(1);
                    System.out.println("totles: "+sum);
//System.out.println("suma"+sum+"pre"+pre+"  "+num);

//sum= sum+num;
                }

                //pop.add(new Day(d, m, y), pre);
                pop.addOrUpdate(new Day(d, m, y), sum);
            }
        } catch (Exception e) {
            System.out.println(e);
        } 

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(pop);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "MES: " + mes_ob + "/" + yea,
                "FECHA",
                "CANTIDAD",
                dataset,
                true,
                true,
                false);
//Con las siguientes 3 líneas podemos cambiar el formato de la fecha
        XYPlot plot = chart.getXYPlot();
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("dd-MM-yy"));
        axis.setTickLabelsVisible(true);

//
        ChartFrame frame = new ChartFrame("Gráfico Time Series", chart);
        frame.pack();
        frame.setVisible(true);
        frame.setTitle("Ventas");
        frame.setLocationRelativeTo(null);
    }
    
}
