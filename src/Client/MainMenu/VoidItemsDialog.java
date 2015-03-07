/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.MainMenu;

import Client.Pair;
import Item.Item;
import Item.Tab;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 *
 * @author mbbx9mg3
 */
public class VoidItemsDialog extends javax.swing.JDialog {

    private final Tab oldTab;
    private final Tab newTab;    
    ArrayList<JCheckBox> cBoxes = new ArrayList<>();
    
    /**
     * Creates new form VoidItemsDialog
     * @param parent
     * @param modal
     * @param tab
     */
    public VoidItemsDialog(Dialog parent, boolean modal, Pair<Tab, Tab> tab) {
        super(parent, modal);
        this.oldTab = tab.getFirst();
        this.newTab = tab.getSecond();
        addCheckBoxes();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanelScrollPane = new javax.swing.JScrollPane();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        mainPanel.setLayout(new javax.swing.BoxLayout(mainPanel, javax.swing.BoxLayout.LINE_AXIS));
        mainPanelScrollPane.setViewportView(mainPanel);
        mainPanel.setLayout(new GridLayout(oldTab.getItems().size() + newTab.getItems().size(), 1));
        mainPanel.setAlignmentX(JScrollPane.RIGHT_ALIGNMENT);
        for (JCheckBox cb : cBoxes)
        mainPanel.add(cb);

        getContentPane().add(mainPanelScrollPane);
        mainPanelScrollPane.setAlignmentX(RIGHT_ALIGNMENT);

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    private void addCheckBoxes()
    {
        for (Item item : oldTab.getItems())    
        {
            JCheckBox jcb = new JCheckBox(item.getQuantity() + " " + item.getName());
            jcb.setAlignmentX(RIGHT_ALIGNMENT);
            jcb.setHorizontalTextPosition(SwingConstants.LEFT);
            cBoxes.add(jcb);   
        }
        
        for (Item item : newTab.getItems())        
        {
            JCheckBox jcb = new JCheckBox(item.getQuantity() + " " + item.getName());
            jcb.setAlignmentX(RIGHT_ALIGNMENT);
            jcb.setHorizontalTextPosition(SwingConstants.LEFT);
            cBoxes.add(jcb);   
        }    
    } 
    
    public Pair<Tab, Tab> startDialog()
    {
        this.setVisible(true);
        return new Pair<>(oldTab, newTab);
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
            java.util.logging.Logger.getLogger(VoidItemsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VoidItemsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VoidItemsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VoidItemsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VoidItemsDialog dialog = new VoidItemsDialog(new JDialog(), true, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane mainPanelScrollPane;
    // End of variables declaration//GEN-END:variables
}
