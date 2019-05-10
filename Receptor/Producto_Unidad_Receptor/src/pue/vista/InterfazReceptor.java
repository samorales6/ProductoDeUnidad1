
package pue.vista;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.UnsupportedCommOperationException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import pue.controlador.PuertoSerie;


public class InterfazReceptor extends javax.swing.JFrame {

    //declaro 2 puertos uno para el cpu (emisor) arduino con los actuadores
    private PuertoSerie cpu,arduino;
    //creo un arreglo con el nombre de las graficas
    private String[] nombre={"DISTANCIA","TEMPERATURA","CO2","HUMEDAD"};

    public InterfazReceptor() {
        initComponents();
        //oculto todos los checkbox
        jcbCo2.setVisible(false);
        jcbDis.setVisible(false);
        jcbHum.setVisible(false);
        jcbTem.setVisible(false);
        
        //inicializo los puertos
        try {
            arduino=new PuertoSerie("COM14");
            cpu=new PuertoSerie("COM9");
        } catch (NoSuchPortException ex) {
            Logger.getLogger(InterfazReceptor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PortInUseException ex) {
            Logger.getLogger(InterfazReceptor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedCommOperationException ex) {
            Logger.getLogger(InterfazReceptor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InterfazReceptor.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        //doy formato a la grafica usando la coleccion 
            JFreeChart disChart=ChartFactory.createXYLineChart("DISTANCIA VS TIEMPO",
                    "TIEMPO","DISTANCIA", cpu.getDis(), PlotOrientation.VERTICAL, 
            true, true, false);            
            
            //creo un panel 
    ChartPanel dispPanel = new ChartPanel(disChart);
    //asigno el panel creado al ya existente en la interfaz
    dispPanel.setBounds(jPdistancia.getBounds());
    this.jPdistancia.add(dispPanel); 
    

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPdistancia = new javax.swing.JPanel();
        jcbDis = new javax.swing.JCheckBox();
        jcbTem = new javax.swing.JCheckBox();
        jcbCo2 = new javax.swing.JCheckBox();
        jcbHum = new javax.swing.JCheckBox();
        jCBSelector = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPdistanciaLayout = new javax.swing.GroupLayout(jPdistancia);
        jPdistancia.setLayout(jPdistanciaLayout);
        jPdistanciaLayout.setHorizontalGroup(
            jPdistanciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 586, Short.MAX_VALUE)
        );
        jPdistanciaLayout.setVerticalGroup(
            jPdistanciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 434, Short.MAX_VALUE)
        );

        jcbDis.setEnabled(false);
        jcbDis.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jcbDisStateChanged(evt);
            }
        });

        jcbTem.setEnabled(false);

        jcbCo2.setEnabled(false);

        jcbHum.setEnabled(false);

        jCBSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Distancia", "Temperatura", "CO2", "Humedad" }));
        jCBSelector.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBSelectorItemStateChanged(evt);
            }
        });
        jCBSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBSelectorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jCBSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jcbDis)
                        .addGap(11, 11, 11)
                        .addComponent(jcbTem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbCo2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbHum))
                    .addComponent(jPdistancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCBSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbDis)
                    .addComponent(jcbTem)
                    .addComponent(jcbCo2)
                    .addComponent(jcbHum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPdistancia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //cuando el estado del checkbox cambie este enviara los datos separados por -
    // y eliminara posibles tabluaciones
    private void jcbDisStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jcbDisStateChanged
        arduino.tx(jcbDis.getText().replace(String.valueOf((char)11),""));
        arduino.tx('-'+jcbTem.getText().replace(String.valueOf((char)11),""));
        arduino.tx('-'+jcbCo2.getText().replace("-",""));
        arduino.tx('-'+jcbHum.getText().replace(String.valueOf((char)11),""));
    }//GEN-LAST:event_jcbDisStateChanged

    //cuando el seleccionador del combobox cambie
    private void jCBSelectorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBSelectorItemStateChanged
        //extraigo el indice del combobox
        int aux=jCBSelector.getSelectedIndex();
        //creo una coleccion auxiliar
        XYSeriesCollection collection;
        //defino a que coleccion fue seleccionada 
        switch (aux){
            case 0:
                collection=cpu.getDis();
            break;
            case 1:
                collection=cpu.getTem();
            break;
            case 2:
                collection=cpu.getCo2();
            break;
            default:
                collection=cpu.getHum();
        }
        //limpio el panel
        jPdistancia.removeAll();
        //creo una nueva grafica con la coleccion selecionada
        JFreeChart disChart=ChartFactory.createXYLineChart(nombre[aux]+" VS TIEMPO",
                    "TIEMPO",nombre[aux], collection, PlotOrientation.VERTICAL, 
            true, true, false);            
  
    ChartPanel dispPanel = new ChartPanel(disChart);

    dispPanel.setBounds(jPdistancia.getBounds());
    this.jPdistancia.add(dispPanel);
        
    }//GEN-LAST:event_jCBSelectorItemStateChanged

    private void jCBSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBSelectorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBSelectorActionPerformed

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
            java.util.logging.Logger.getLogger(InterfazReceptor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazReceptor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazReceptor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazReceptor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazReceptor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jCBSelector;
    private javax.swing.JPanel jPdistancia;
    public static javax.swing.JCheckBox jcbCo2;
    public static javax.swing.JCheckBox jcbDis;
    public static javax.swing.JCheckBox jcbHum;
    public static javax.swing.JCheckBox jcbTem;
    // End of variables declaration//GEN-END:variables
}
