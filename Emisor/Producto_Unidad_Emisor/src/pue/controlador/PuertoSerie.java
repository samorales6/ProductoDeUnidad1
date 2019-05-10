

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
import pue.vista.InterfazEmisor;



public class PuertoSerie extends Thread{
    
    private CommPortIdentifier idPuerto;
    private SerialPort puertoSerie;
    private InputStream flujoEntrada;
    private OutputStream flujoSalida;
    
    private int lon;
    private byte [] buffer = new byte[1024]; // tamaño maximo de caracteres que se puede recibir
    private String aux;


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
                   //Sacamos el primer caracter de la cadena
                   char inicial=aux.charAt(0);
                   //y eliminamos el primer caracter
                   aux=aux.replace(String.valueOf(inicial),"");
                   //identificamos el primer caracter y clasificamos los datos
                   if(inicial=='d'){
                       //limpiamos el label
                       InterfazEmisor.jLdis.setText("");
                       //ponemos el valor del sensor con sus unidades
                       InterfazEmisor.jLdis.setText(aux+" cm");
                       //cambiamos el estado del checkbox
                       InterfazEmisor.jcbDis.setSelected(!InterfazEmisor.jcbDis.isSelected());
                    }
                   if(inicial=='t'){
                       InterfazEmisor.jLtem.setText("");
                       InterfazEmisor.jLtem.setText(aux+" °C");
                       InterfazEmisor.jcbTem.setSelected(!InterfazEmisor.jcbTem.isSelected());
                    }
                   if(inicial=='c'){
                       InterfazEmisor.jLCo2.setText("");
                       InterfazEmisor.jLCo2.setText(aux+" %");
                       InterfazEmisor.jcbCo2.setSelected(!InterfazEmisor.jcbCo2.isSelected());
                    }
                   if(inicial=='h'){
                       InterfazEmisor.jLhum.setText("");
                       InterfazEmisor.jLhum.setText(aux+" %");
                       InterfazEmisor.jcbHum.setSelected(!InterfazEmisor.jcbHum.isSelected());
                    }
               }

           } catch (IOException ex) {
               Logger.getLogger(PuertoSerie.class.getName()).log(Level.SEVERE, null, ex);
           } 
       }
   }
   
   public void cerrar() throws IOException{
       flujoSalida.close();
       flujoEntrada.close();
       puertoSerie.close();
       
   }

}
    
    


