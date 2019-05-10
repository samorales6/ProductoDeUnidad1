

package pue.controlador;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import pue.vista.InterfazReceptor;



public class PuertoSerie extends Thread{
    
    private final CommPortIdentifier idPuerto;
    private SerialPort puertoSerie;
    private InputStream flujoEntrada;
    private OutputStream flujoSalida;
    
    private int lon;
    //defino un contador para la serie 
    private long [] i={0,0,0,0};
    private byte [] buffer = new byte[1024]; // tamaño maximo de caracteres que se puede recibir
    private String aux;
    
    //creo las series que serian equivalentes a los pares ordenados
    private XYSeries distancia= new XYSeries("Distancia");
    private XYSeries temperatura= new XYSeries("Temperatura");
    private XYSeries co2= new XYSeries("CO2");
    private XYSeries humedad= new XYSeries("Humedad");
    
    //creo las colecciones que son similares a las funciones
    private XYSeriesCollection dis=new XYSeriesCollection(distancia);
    private XYSeriesCollection tem=new XYSeriesCollection(temperatura);
    private XYSeriesCollection Co2=new XYSeriesCollection(co2);
    private XYSeriesCollection hum=new XYSeriesCollection(humedad);

    public XYSeriesCollection getDis() {
        return dis;
    }

    public XYSeriesCollection getTem() {
        return tem;
    }

    public XYSeriesCollection getCo2() {
        return Co2;
    }

    public XYSeriesCollection getHum() {
        return hum;
    }
    
    

    public String getAux() {
        return aux;
    }
public PuertoSerie (String puerto) throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException, IOException{
      
        idPuerto = CommPortIdentifier.getPortIdentifier(puerto);
        System.out.println("Puerto "+puerto+" encontrado");
     if(idPuerto.isCurrentlyOwned()){
         System.out.println("Puerto Ocupado");
     }
     else{
         puertoSerie= (SerialPort) idPuerto.open("tx/rx",1000);
         System.out.println("Puerto abierto");
         puertoSerie.setSerialPortParams(9600, SerialPort.DATABITS_8,
                 SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
         System.out.println("Puerto Configurado");
         flujoSalida = puertoSerie.getOutputStream();
         System.out.println("Flujo de salida configurado");
         flujoEntrada = puertoSerie.getInputStream();
         System.out.println("Flujo de entrada configurado");
         this.start();
     }
    }
    
   public void tx (String mensaje) {
        try {
            flujoSalida.write(mensaje.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(PuertoSerie.class.getName()).log(Level.SEVERE, null, ex);
        }
                    System.out.println("Mensaje enviado. "+mensaje);
   }
   
    
   public void run(){
       while(true){
           try {
               if ((lon=flujoEntrada.read(buffer)) >- 1){
                   aux = new String (buffer,0,lon);
                   //guardo el primer caracter de la cadena
                   char inicial=aux.charAt(0);
                   //elimino el primer caracter y un posible enter
                   aux=aux.replace(String.valueOf(inicial),"").replace(String.valueOf((char)10),"");
                   //compara cual fue el primer caracter 
                   if(inicial=='d'){
                   //añado a la serie un par formado por el contador y el dato recibido
                       distancia.add(i[0],Float.parseFloat(aux));
                       i[0]++;    //incremeto el contador para el siguente dato
                       InterfazReceptor.jcbDis.setText(aux); //guardo el dato en la interfaz(checkbox)
                       //cambio el estado el checkbox
                       InterfazReceptor.jcbDis.setSelected(!InterfazReceptor.jcbDis.isSelected());
                    }
                   if(inicial=='t'){
                       temperatura.add(i[1],Float.parseFloat(aux));
                       i[1]++;
                       InterfazReceptor.jcbTem.setText(aux);
                       InterfazReceptor.jcbTem.setSelected(!InterfazReceptor.jcbTem.isSelected());
                    }
                   if(inicial=='c'){
                       co2.add(i[2],Float.parseFloat(aux.replace("-","")));
                       i[2]++;
                       InterfazReceptor.jcbCo2.setText(aux.replace("-",""));
                       InterfazReceptor.jcbCo2.setSelected(!InterfazReceptor.jcbCo2.isSelected());
                    }
                   if(inicial=='h'){
                       humedad.add(i[3],Float.parseFloat(aux));
                       i[3]++;
                       InterfazReceptor.jcbHum.setText(aux);
                       InterfazReceptor.jcbHum.setSelected(!InterfazReceptor.jcbHum.isSelected());
                    }
               } 
           } catch (IOException ex) {
               Logger.getLogger(PuertoSerie.class.getName()).log(Level.SEVERE, null, ex);
           }catch(java.lang.NumberFormatException e){
               //exepcion que nos permite continuar si un valor no se pudo convertir
           }
       }
   }
   
   public void cerrar() throws IOException{
       flujoSalida.close();
       flujoEntrada.close();
       puertoSerie.close();
       
   }

}
    
    


