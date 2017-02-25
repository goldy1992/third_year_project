package com.mike.client.frontend.MainMenu.voidItem;

import com.mike.client.frontend.Pair;
import com.mike.item.Item;
import com.mike.item.Tab;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * Created by Mike on 24/02/2017.
 */
public class VoidItemView extends javax.swing.JDialog {

    final static Logger logger = Logger.getLogger(VoidItemView.class);

    private final JButton submitButton = new JButton("Submit");
    private final JButton cancelButton = new JButton("Cancel");
    private ArrayList<Pair<Pair<JCheckBox, Item>, Pair<Component, Component>>> cBoxesOldTab;
    private ArrayList<Pair<Pair<JCheckBox, Item>, Pair<Component, Component>>> cBoxesNewTab;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane mainPanelScrollPane;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables


    /**
     * Creates new form VoidItemsDialog
     * @param parent
     * @param modal
     * @param newTab
     * @param oldTab
     */
    public VoidItemView(Dialog parent, boolean modal, Tab newTab, Tab oldTab) {
        super(parent, modal);
        cBoxesOldTab = addCheckBoxes(oldTab);
        cBoxesNewTab = addCheckBoxes(newTab);
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

        titlePanel = new javax.swing.JPanel();
        mainPanelScrollPane = new javax.swing.JScrollPane();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));
        getContentPane().add(titlePanel);
        titlePanel.setLayout(new GridLayout(0,3));
        titlePanel.add(new JLabel("Item"));
        titlePanel.add(new JLabel("Wastage"));
        titlePanel.add(new JLabel("Amount"));

        mainPanel.setLayout(new javax.swing.BoxLayout(mainPanel, javax.swing.BoxLayout.LINE_AXIS));
        mainPanelScrollPane.setViewportView(mainPanel);
        mainPanel.setLayout(new GridLayout(0, 3));
        for (Pair<Pair<JCheckBox, Item>, Pair<Component, Component>> cb : getcBoxesOldTab())
        {
            mainPanel.add(cb.getFirst().getFirst());
            mainPanel.add(cb.getSecond().getFirst());
            mainPanel.add(cb.getSecond().getSecond());
        }
        for (Pair<Pair<JCheckBox, Item>, Pair<Component, Component>> cb : getcBoxesNewTab())
        {
            mainPanel.add(cb.getFirst().getFirst());
            mainPanel.add(cb.getSecond().getFirst());
            mainPanel.add(cb.getSecond().getSecond());
        }

        getContentPane().add(mainPanelScrollPane);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private ArrayList<Pair<Pair<JCheckBox, Item>, Pair<Component, Component>>> addCheckBoxes(Tab tab) {
        ArrayList<Pair<Pair<JCheckBox, Item>, Pair<Component, Component>>>
                returnList = new ArrayList<>();
        for (Item item : tab.getItems()) {
            final Component first;
            final Component second;

            if (item.stockCount) {
                first = new JCheckBox("Wasted");
                first.setEnabled(false);
            } else {
                first = new JLabel("Stock Count Off");
            }

            if (item.getQuantity() > 1) {
                second = new JTextField("");
                second.setEnabled(false);
            } else {
                second = new JLabel("Only 1 Item");
            }
            final JCheckBox jcb = new JCheckBox(item.getQuantity() + " " + item.getName());
            jcb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (jcb.isSelected()) {
                        first.setEnabled(true);
                        second.setEnabled(true);
                    } else {
                        first.setEnabled(false);
                        second.setEnabled(false);
                    } // else
                } // actionPerformed
            });

            returnList.add(new Pair(new Pair(jcb, item), new Pair(first, second)));
        } // for

        return returnList;
    }  // add check boxes

    public void startDialog() {
        JPanel exitPanel = new JPanel();
        exitPanel.setLayout(new GridLayout(1,0));
        getContentPane().add(exitPanel);
        exitPanel.add(getSubmitButton());
        exitPanel.add(getCancelButton());

        pack();

        this.setVisible(true);
    }

    public JButton getSubmitButton() { return submitButton; }
    public JButton getCancelButton() { return cancelButton; }

    public ArrayList<Pair<Pair<JCheckBox, Item>, Pair<Component, Component>>> getcBoxesOldTab() { return cBoxesOldTab; }
    public ArrayList<Pair<Pair<JCheckBox, Item>, Pair<Component, Component>>> getcBoxesNewTab() { return cBoxesNewTab; }



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
            logger.error(ex);
        } catch (InstantiationException ex) {
            logger.error(ex);
        } catch (IllegalAccessException ex) {
            logger.error(ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            logger.error(ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
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

}
