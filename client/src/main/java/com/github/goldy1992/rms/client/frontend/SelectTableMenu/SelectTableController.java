/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.goldy1992.rms.client.frontend.SelectTableMenu;

import com.github.goldy1992.rms.client.backend.MessageSender;
import com.github.goldy1992.rms.client.frontend.MainMenu.MenuController;
import com.github.goldy1992.rms.client.frontend.SelectTableMenu.View.SelectTableView;
import com.github.goldy1992.rms.message.EventNotification.TableStatusEvtNfn;
import com.github.goldy1992.rms.message.Response.TabResponse;
import com.github.goldy1992.rms.message.Response.TableStatusResponse;
import com.github.goldy1992.rms.message.Table.TableStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import static com.github.goldy1992.rms.client.frontend.SelectTableMenu.SelectTableModel.NO_TABLE_SELECTED;
import static com.github.goldy1992.rms.message.Table.TableStatus.*;

/**
 *
 * @author Mike
 */
@MessageEndpoint
public class SelectTableController implements ActionListener, WindowListener {

	final static Logger logger = Logger.getLogger(SelectTableController.class);
	
	@Autowired
	private MessageSender messageSender;
	
	@Autowired
	private MenuController menuController;
	 
    public SelectTableModel model;
    public SelectTableView view;
    private boolean initialised = false;
	
	public void setMessageSender(MessageSender messageSender) { this.messageSender = messageSender; }
	public void setMenuController(MenuController menuController) {this.menuController = menuController; }
    public boolean isInitialised() {
		return initialised;
	}
    

	public void setInitialised(boolean initialised) {
		this.initialised = initialised;
	}

	public void init() {
		TableStatusResponse response =  this.messageSender.sendTableStatusRequest(new ArrayList<>());
		ArrayList<TableStatus> ts = new ArrayList<>();
		for(Integer i : response.getTableStatuses().keySet()) {
    			ts.add(response.getTableStatuses().get(i));
		}
		this.model = new SelectTableModel(ts);
        this.view = new SelectTableView(this, ts);
        view.setVisible(true);
    }
    
    private void selectCleanTable(ActionEvent event) {
        int tableSelected = model.getTableSelected();
        TableStatus selectedTableStatus = model.getTableStatus(tableSelected);
        if (selectedTableStatus == DIRTY) {
            messageSender.sendTableStatusEventNotification(tableSelected, FREE);
        } // if    	
    }

	private void selectOpenTable(ActionEvent event) {
		int tableSelected = model.getTableSelected();
		if (tableSelected == NO_TABLE_SELECTED) {
			view.getOutputLabel().setOutputLabelNoTableSelected();
		} else if (model.getTableStatus(tableSelected) == IN_USE) {
			view.getOutputLabel().setOutputLabelTableInUse(tableSelected);
		} else {
			view.setTableStatus(tableSelected, IN_USE);
			messageSender.sendTableStatusEventNotification(tableSelected, IN_USE);
			TabResponse response = messageSender.sendTabRequest(tableSelected);
			getMenuController().init(this.view, response.getTab());
			logger.info("table selected");
		} // else
	}

	@Override
    public void actionPerformed(ActionEvent event) {
        
        if (view.isCleanTableSelected(event)) {
        	selectCleanTable(event);
        	return;
        } // if getSource

        
        if (view.isOpenTableSelected(event)) {
        	selectOpenTable(event);
            return;
        } // if open table
        
        if(view.isMoveTableSelected(event)) {
        	 return;
        } // TODO: implement move table
        
    	for (int i = 1; i <= model.getNumberOfTables(); i++) {
    		if (view.isTableXSelected(event, i)) {
	            view.getOutputLabel().setOutputLabelOpenQuery(i);
	            model.setTableSelected(i);    		
	            return;
    		}
    	}
    } // action performed    
    
    
    @ServiceActivator(inputChannel="tableStatusEvtNotificationChannel")
    public void setTableStatus(TableStatusEvtNfn tableStatusEvtNfn) {
		logger.info(tableStatusEvtNfn);
    	int tableNumber = tableStatusEvtNfn.getTableNumber();
    	TableStatus tableStatus = tableStatusEvtNfn.getTableStatus();
		model.setTableStatus(tableNumber, tableStatus);
    	view.setTableStatus(tableNumber, tableStatus);
    }

	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowOpened(WindowEvent e) {}
	@Override
	public void windowClosing(WindowEvent e) {
		int confirm = JOptionPane.showOptionDialog(null,
				"Are You Sure to Close Application?", "Exit Confirmation",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, null, null);

		if (confirm == 0) {
			if (messageSender.leaveRequest()) {
				view.dispose();
				System.exit(0);
			}
		}
	}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {	}
	@Override
	public void windowActivated(WindowEvent e) {	}
	@Override
	public void windowDeactivated(WindowEvent e) {	}

	public MenuController getMenuController() {
		return menuController;
	}
}
