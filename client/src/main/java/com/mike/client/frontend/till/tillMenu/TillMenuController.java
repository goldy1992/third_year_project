package com.mike.client.frontend.till.tillMenu;

import com.mike.client.frontend.MainMenu.MenuController;
import com.mike.client.frontend.MainMenu.View.BarTabDialogSelect;
import com.mike.item.Tab;
import com.mike.item.dbItem.MenuPageDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Created by michaelg on 07/09/2016.
 */
public class TillMenuController extends MenuController {

//	private TillMenuModel model;
//	private TillMenuView view;

	public TillMenuController() {
		super();
	}

	@Override
	public <V extends JFrame> void init(V tillView, Tab tab) {
		List<MenuPageDAO> menuPageDAOs = getMenuPageDAOs();
		this.model = new TillMenuModel();
		model.init(menuPageDAOs, null);
		this.view = new TillMenuView(this, tillView, model, true);
		this.view.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() instanceof JButton) {
			dealWithButtons((JButton)ae.getSource());
		}
		super.actionPerformed(ae);

	}

	private void barTabPressed() {
		TillMenuModel model = (TillMenuModel)this.model;
		TillMenuView view = (TillMenuView)this.view;
		if (!model.isTabLoaded()) {
			System.out.println("tabLoaded == false");
			if (model.getOldTab().getNumberOfItems() + model.getNewTab().getNumberOfItems() <= 0) {
				System.out.println("get tab selected");
				view.getSelectorFrame().setState(BarTabDialogSelect.Functionality.GET_TAB);
			} else {
				view.getSelectorFrame().setState(BarTabDialogSelect.Functionality.ADD_TO_TAB);
			}

			if (view.getSelectorFrame().getState() == BarTabDialogSelect.Functionality.GET_TAB && view.getSelectorFrame().numberOfTabs <= 0) {
				view.getQuantityTextPane().setText("there are no tabs to display!");
			} else {
				view.getSelectorFrame().setVisible(true);
			}
		} else {
			model.getOldTab().mergeTabs(model.getNewTab());
			sendOrder();
			view.setUpTab(null);
			view.getOutputTextPane().setText("");
			model.setTabLoaded(false);
			view.dispose();
		}
	}

	public void writeLastReceipt() {
//		System.out.println("called last receipt");
//		if (this.lastReceipt == null)
//			return;
//		try
//		{
//			System.out.println("last receipt not null");
//			File file = new File("Last _Bill_Receipt_"
//					//+ oldTab.getTable().getTableNumber()
//					+ ".txt");
//			int i = 1;
//			while (file.exists())
//			{
//				file = new File("Last _Bill_Receipt_" //+ oldTab.getTable().getTableNumber()
//						+ "(" + i + ")" + ".txt");
//				i++;
//			} // while
//
//			// creates the file
//			file.createNewFile();
//			// Writes the content to the file
//			try ( FileWriter writer = new FileWriter(file) )
//			{
//				// Writes the content to the file
//				writer.write(this.lastReceipt);
//				writer.flush();
//			}
//			sendOrder();
//
//			this.dispose();
//
//		}
//		catch (IOException ex) {
//			Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);}
	} // writeBill

	@Override
	protected void sendOrder() {

	}


	@Override
	public void dealWithButtons(JButton button) {
		switch (button.getText()) {
			case "Bar Tab": barTabPressed(); break;
			case "Print Last Receipt": writeLastReceipt(); break;
			case "Send Order": sendOrder(); break;
			default: break;
		} // switch
	}

}
