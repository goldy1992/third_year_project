package com.mike.client.frontend.till.tillMenu;

import com.mike.client.frontend.MainMenu.Model.MenuModel;
import com.mike.client.frontend.till.tillMenu.barTabMenu.BarTabDialogView;
import com.mike.client.frontend.MainMenu.View.MenuView;
import com.mike.client.frontend.Pair;
import com.mike.message.Table;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author mbbx9mg3
 */
public class TillMenuView extends MenuView {
	/**
	 * Creates new form Menu
	 *
	 * @param tillMenuController
	 * @param parent
	 * @param tillMenuModel
	 * @param modal
	 */
    public TillMenuView(TillMenuController tillMenuController, java.awt.Frame parent, MenuModel tillMenuModel, boolean modal) {
        super(tillMenuController, parent, tillMenuModel, modal, null);
    }

    public boolean tabLoaded = false;

    @Override
    protected String[] getOptionNames() {
       String[] x = {"Print Bill", "Print Last Receipt", "Void",
           "Void Last Item",  "Split Bill", "Order On Hold", "Delivered",
           "Bar Tab", "Other Payment Methods", "Debit Card Pay", "Cash Pay", "Send Order"};
       return x;
    }
} // class