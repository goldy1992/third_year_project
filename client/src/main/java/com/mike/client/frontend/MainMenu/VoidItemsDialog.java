/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mike.client.frontend.MainMenu;

import com.mike.client.frontend.Pair;
import com.mike.item.Item;
import com.mike.item.Tab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mbbx9mg3
 */
public class VoidItemsDialog extends javax.swing.JDialog {

    private final Tab oldTab;
    private final Tab newTab;  
    private final JButton submitButton = new JButton("Submit");
    private final JButton cancelButton = new JButton("Cancel");    
    ArrayList<Pair<Pair<JCheckBox, Item>, Pair<Component, Component>>> cBoxesOldTab;
    ArrayList<Pair<Pair<JCheckBox, Item>, Pair<Component, Component>>> cBoxesNewTab;
    
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
        for (Pair<Pair<JCheckBox, Item>, Pair<Component, Component>> cb : cBoxesOldTab)
        {
            mainPanel.add(cb.getFirst().getFirst());
            mainPanel.add(cb.getSecond().getFirst());
            mainPanel.add(cb.getSecond().getSecond());
        }
        for (Pair<Pair<JCheckBox, Item>, Pair<Component, Component>> cb : cBoxesNewTab)
        {
            mainPanel.add(cb.getFirst().getFirst());
            mainPanel.add(cb.getSecond().getFirst());
            mainPanel.add(cb.getSecond().getSecond());
        }

        getContentPane().add(mainPanelScrollPane);

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    private ArrayList<Pair<Pair<JCheckBox, Item>, Pair<Component, Component>>> 
        addCheckBoxes(Tab tab)
    {
        ArrayList<Pair<Pair<JCheckBox, Item>, Pair<Component, Component>>> 
            returnList = new ArrayList<>();
        for (Item item : tab.getItems())    
        {
            final Component first;
            final Component second;

            if (item.stockCount)
            {
                first = new JCheckBox("Wasted");
                first.setEnabled(false);
            } // if
            else
                first = new JLabel("Stock Count Off");
            
            
            if (item.getQuantity() > 1)
            {
                second = new JTextField("");
                second.setEnabled(false);
            } // if
            else second = new JLabel("Only 1 Item");
            
            final JCheckBox jcb = new JCheckBox(item.getQuantity() + " " + item.getName());
            jcb.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    if (jcb.isSelected())
                    {
                        first.setEnabled(true);
                        second.setEnabled(true);
                    } // if
                    else
                    {
                        first.setEnabled(false);
                        second.setEnabled(false);                        
                    } // else
                } // actionPerformed               
            });
            
            returnList.add(new Pair(new Pair(jcb, item), new Pair(first, second)));   
        } // for
        
        return returnList;
    }  // add check boxes
    
    public Pair<Tab, Tab> startDialog()
    {
        JPanel exitPanel = new JPanel();
        exitPanel.setLayout(new GridLayout(1,0));
        final VoidItemsDialog thisInstance = this;
        submitButton.addActionListener(new ActionListener()
        {
            public void addToStock(Item item, int quantity) 
            {
                try
                {
                    Connection con;
                    //initialise the connection to the database
                    con = DriverManager.getConnection(
                        "jdbc:mysql://dbhost.cs.man.ac.uk:3306/mbbx9mg3", 
                        "mbbx9mg3",
                        "Fincherz+2013");

                    // query: UPDATE `3YP_ITEMS` SET `QUANTITY` = `QUANTITY` - 1 WHERE ID = 27 
                    PreparedStatement numberOfButtonsQuery = null;
                    String query = "UPDATE `3YP_ITEMS` " 
                        + "SET `QUANTITY` = `QUANTITY` + \"" + quantity + "\" " 
                        + "WHERE `3YP_ITEMS`.`ID` = \"" 
                        + item.getID() + "\"";

                    System.out.println("Query: " + query);

                    numberOfButtonsQuery = con.prepareStatement(query);
                    numberOfButtonsQuery.executeUpdate();

                    con.close();    
                }
                catch (SQLException ex) 
                {
                    Logger.getLogger(VoidItemsDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            } // addToStock
            
            public boolean removeItems(ArrayList<Pair<Pair<JCheckBox, Item>, 
                Pair<Component, Component>>> cBoxes, Tab tab)
                    
                   
            {
                CopyOnWriteArrayList<Item> temp = new  CopyOnWriteArrayList<>();  
                temp.addAll(tab.getItems());
 
                // the first for check for errors
                for (Pair<Pair<JCheckBox, Item>, Pair<Component, Component>> jCB : cBoxes)
                {
                    if (jCB.getFirst().getFirst().isSelected() && (jCB.getSecond().getSecond() instanceof JTextField))
                    {
                        int amount = 0;
                        try
                        {
                            JTextField tf = (JTextField)jCB.getSecond().getSecond();
                            amount = Integer.parseInt(tf.getText());
                        }
                        catch (NumberFormatException e)
                        {
                            JOptionPane.showMessageDialog(thisInstance, "Incorrect number format! Please check your formatting!");
                            return false;
                        }

                        if (amount >= jCB.getFirst().getSecond().getQuantity() ||
                                amount <= 0)
                        {
                            JOptionPane.showMessageDialog(thisInstance, "Incorrect number format! Please check your formatting!");
                            return false;
                        }
                    } // if
                }  // for               
                
                
                for (Pair<Pair<JCheckBox, Item>, Pair<Component, Component>> jCB : cBoxes)
                {
                    if (jCB.getFirst().getFirst().isSelected())
                    {
                        for (Item i : temp)
                            if (jCB.getFirst().getSecond() == i)
                            {
                                if (jCB.getFirst().getSecond().getQuantity() == 1)
                                {
                                    temp.remove(i); 
                                    if (i.stockCount)
                                    {
                                        JCheckBox tf = (JCheckBox)jCB.getSecond().getFirst();
                                        if (!tf.isSelected())
                                            addToStock(i, 1);
                                    } // if
                                } // if
                                else
                                {
                                    JTextField tf = (JTextField)jCB.getSecond().getSecond();
                                    int amount = Integer.parseInt(tf.getText());     
                                    if (amount == jCB.getFirst().getSecond().getQuantity())
                                       temp.remove(i);
                                    else
                                        i.setQuantity(i.getQuantity() - amount);
                         
                                    if (i.stockCount)
                                    {
                                        JCheckBox tf1 = (JCheckBox)jCB.getSecond().getFirst();
                                        if (!tf1.isSelected())
                                            addToStock(i, amount);
                                    } // if
                                } // else
                            } // if                  
                    } // if
                } // for     
                
                tab.removeAll();
                for (Item i :temp )
                    tab.addItem(i);
                return true;
            }
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if (removeItems(cBoxesOldTab, oldTab)
              &&  removeItems(cBoxesNewTab, newTab)) 
                thisInstance.setVisible(false);
            } // actionPerformed
        });
       
        cancelButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                thisInstance.dispose();
            }
            
        });
        getContentPane().add(exitPanel);
        exitPanel.add(submitButton);
        exitPanel.add(cancelButton);
        
        pack();
        
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane mainPanelScrollPane;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
}
