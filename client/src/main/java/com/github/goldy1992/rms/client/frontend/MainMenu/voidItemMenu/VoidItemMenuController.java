package com.github.goldy1992.rms.client.frontend.MainMenu.voidItemMenu;

import com.github.goldy1992.rms.client.backend.MessageSender;
import com.github.goldy1992.rms.client.frontend.MainMenu.voidItemMenu.voidItem.VoidItem;
import com.github.goldy1992.rms.client.frontend.Pair;
import com.github.goldy1992.rms.item.Item;
import com.github.goldy1992.rms.item.Tab;
import com.github.goldy1992.rms.message.Response.databaseResponse.QueryResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mike on 24/02/2017.
 */
public class VoidItemMenuController implements ActionListener{

    final static Logger logger = Logger.getLogger(VoidItemMenuController.class);

    @Autowired
    private MessageSender messageSender;

    private VoidItemMenuModel model;
    private VoidItemMenuView view;

    public VoidItemMenuController(){}

    public void setMessageSender(MessageSender messageSender) { this.messageSender = messageSender; }

    public Pair<Tab, Tab> startDialog(Dialog parent, boolean modal, Tab oldTab, Tab newTab) {
        model = new VoidItemMenuModel(oldTab, newTab);
        view = new VoidItemMenuView(parent, modal, model.getOldTab(), model.getNewTab(), this, model.getVoidItems());

        view.startDialog();
        return new Pair<>(model.getOldTab(), model.getNewTab());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getSubmitButton()) {
            if (!validAmounts(model.getVoidItems())) {
                JOptionPane.showMessageDialog(view, "Incorrect number format! Please check your formatting!");
            } else {
                List<VoidItem> selectedVoidItems = new ArrayList<>();
                for (VoidItem i : model.getVoidItems()) {
                    if (i.getVoidItemModel().isSelected()) {
                        selectedVoidItems.add(i);
                    }
                }
                removeItems(selectedVoidItems, model.getOldTab(), model.getNewTab());
                view.setVisible(false);
            }

        } else if (e.getSource() == view.getCancelButton()) {
            view.dispose();
        }
    }

    private void addToStock(Item item, int quantity) {
        // TODO; revise this method to make sure it works
        String query = "UPDATE `ITEMS` "
                + "SET `QUANTITY` = `QUANTITY` + \"" + quantity + "\" "
                + "WHERE `ITEMS`.`ID` = \""
                + item.getID() + "\"";
        QueryResponse response = messageSender.sendDbQuery(query);
        logger.info(response);
    } // addToStock

    private void removeItems(List<VoidItem> voidItems, Tab oldTab, Tab newTab) {
        Map<Integer, Integer> itemQuantityMapToAddToStock = new HashMap<>();
        for (VoidItem voidItem : voidItems) {
            if (!removeItemFromTab(voidItem, newTab)) {
                removeItemFromTab(voidItem, oldTab);
            } // if
            oldTab.calculateTotal();
            newTab.calculateTotal();
            if (voidItem.getVoidItemModel().getItem().isStockCount() && !voidItem.getVoidItemModel().isWasted()) {
                itemQuantityMapToAddToStock.put(voidItem.getVoidItemModel().getItem().getID(), voidItem.getVoidItemModel().getAmountToVoid());
            }
            messageSender.addItemsToStock(itemQuantityMapToAddToStock);
        } // for each
    } // removeItems

    private boolean removeItemFromTab(VoidItem i, Tab tab) {
        Item itemToRemove = i.getVoidItemModel().getItem();
        int itemQuantity = itemToRemove.getQuantity();
        int amountToRemove = i.getVoidItemModel().getAmountToVoid();
        for (Item item : tab.getItems()) {
            if (item.equals(i.getVoidItemModel().getItem())) {
                if (amountToRemove == itemQuantity) {
                    return tab.removeItem(itemToRemove);
                } else {
                    tab.getItem(itemToRemove).setQuantity(itemQuantity - amountToRemove);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean validAmounts(List<VoidItem> voidItems) {
        for (VoidItem i: voidItems) {
            if (i.getVoidItemModel().isSelected() && !validVoidAmount(i)) {
                return false;
            } // if
        } // for
        return true;
    }

    private boolean validVoidAmount(VoidItem voidItem) {
        return  null != voidItem.getVoidItemModel().getAmountToVoid() &&
                voidItem.getVoidItemModel().getAmountToVoid() >= 1 &&
                voidItem.getVoidItemModel().getAmountToVoid() <= voidItem.getVoidItemModel().getItem().getQuantity();
    }

} // class
