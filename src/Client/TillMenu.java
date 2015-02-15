/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Item.Tab;
import java.awt.Component;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import javax.swing.JPanel;

/**
 *
 * @author mbbx9mg3
 */
public class TillMenu extends Menu {
    
    @Override
    protected String[] getOptionNames() { 
        
       String[] x = {"Print Bill", "Print Last Receipt", "Void", 
           "Void Last Item",  "Split Bill", "Order On Hold", "Delivered", 
           "Bar Tab", "Other Payment Methods", "Debit Card Pay", "Send Order"};
       return x;
    }
    public TillMenu(java.awt.Frame parent, boolean modal, Tab tab, ObjectOutputStream stream) throws SQLException
    {
        super(parent, modal, tab, stream);
    }
    
    @Override
    protected MenuCardPanel initialiseMainCard(MenuCardPanel panel)
    {
        MenuCardPanel x = super.initialiseMainCard(panel);
        
        Component[] arrayOfItems = x.getComponents();
        
        JPanel option = (JPanel)arrayOfItems[2];
        option.setLayout(new java.awt.GridLayout(10, 1));
        return x;
    }
    
}