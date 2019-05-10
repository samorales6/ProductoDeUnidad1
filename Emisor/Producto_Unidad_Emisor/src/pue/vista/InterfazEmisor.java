
package pue.vista;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.UnsupportedCommOperationException;
import pue.controlador.PuertoSerie;


public class InterfazEmisor extends javax.swing.JFrame {

    //creamos 2 puertos uno para el cpu 2(receptor) y para el arduino con 
    //los sensores
    private PuertoSerie arduino,cpu;

    public InterfazEmisor() {
        initComponents();
        //ocultamos todos los checkboxs
        jcbCo2.setVisible(false);
        jcbDis.setVisible(false);
        jcbHum.setVisible(false);
        jcbTem.setVisible(false);
        
        //inicializamos los puertos con sus respectivos COM
        try {
            arduino=new PuertoSerie("COM9");
            cpu=new PuertoSerie("COM14");
        } catch (NoSuchPortException ex) {
            Logger.getLogger(InterfazEmisor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PortInUseException ex) {
            Logger.getLogger(InterfazEmisor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedCommOperationException ex) {
            Logger.getLogger(InterfazEmisor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InterfazEmisor.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLdistancia = new javax.swing.JLabel();
        jLtemperatura = new javax.swing.JLabel();
        jLco2 = new javax.swing.JLabel();
        jLhumedad = new javax.swing.JLabel();
        jLdis = new javax.swing.JLabel();
        jLtem = new javax.swing.JLabel();
        jLCo2 = new javax.swing.JLabel();
        jLhum = new javax.swing.JLabel();
        jcbDis = new javax.swing.JCheckBox();
        jcbTem = new javax.swing.JCheckBox();
        jcbHum = new javax.swing.JCheckBox();
        jcbCo2 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLdistancia.setText("DISTANCIA");

        jLtemperatura.setText("TEMPERATURA");

        jLco2.setText("CO2");

        jLhumedad.setText("HUMEDAD");

        jcbDis.setEnabled(false);
        jcbDis.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jcbDisStateChanged(evt);
            }
        });

        jcbTem.setEnabled(false);
        jcbTem.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jcbTemStateChanged(evt);
            }
        });

        jcbHum.setEnabled(false);
        jcbHum.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jcbHumStateChanged(evt);
            }
        });

        jcbCo2.setEnabled(false);
        jcbCo2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jcbCo2StateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLdistancia)
                    .addComponent(jLco2)
                    .addComponent(jLdis)
                    .addComponent(jLCo2))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLhum)
                    .addComponent(jLtem)
                    .addComponent(jLhumedad)
                    .addComponent(jLtemperatura))
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jcbDis)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbTem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbCo2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbHum)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLdistancia)
                    .addComponent(jLtemperatura))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLdis)
                    .addComponent(jLtem))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLco2)
                    .addComponent(jLhumedad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLCo2)
                    .addComponent(jLhum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbDis)
                    .addComponent(jcbTem)
                    .addComponent(jcbCo2)
                    .addComponent(jcbHum)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    ///cada vez que el estado de un checkbox cambie se enviara el dato correspondiente al cpu2
    private void jcbDisStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jcbDisStateChanged
        //elimino las unidades y aumento una etiqueta o identifiacion al principio
        cpu.tx('d'+jLdis.getText().replace(" cm",""));
    }//GEN-LAST:event_jcbDisStateChanged

    private void jcbTemStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jcbTemStateChanged
        cpu.tx('t'+jLtem.getText().replace(" °C",""));
    }//GEN-LAST:event_jcbTemStateChanged

    private void jcbCo2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jcbCo2StateChanged
        cpu.tx('c'+jLCo2.getText().replace(" %",""));
    }//GEN-LAST:event_jcbCo2StateChanged

    private void jcbHumStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jcbHumStateChanged
        cpu.tx('h'+jLhum.getText().replace(" %",""));
    }//GEN-LAST:event_jcbHumStateChanged

    
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
            java.util.logging.Logger.getLogger(InterfazEmisor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazEmisor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazEmisor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazEmisor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazEmisor().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel jLCo2;
    private javax.swing.JLabel jLco2;
    public static javax.swing.JLabel jLdis;
    private javax.swing.JLabel jLdistancia;
    public static javax.swing.JLabel jLhum;
    private javax.swing.JLabel jLhumedad;
    public static javax.swing.JLabel jLtem;
    private javax.swing.JLabel jLtemperatura;
    public static javax.swing.JCheckBox jcbCo2;
    public static javax.swing.JCheckBox jcbDis;
    public static javax.swing.JCheckBox jcbHum;
    public static javax.swing.JCheckBox jcbTem;
    // End of variables declaration//GEN-END:variables


}
