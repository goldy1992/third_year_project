package com.mike.server.database;

import com.mike.server.database.tables.FOOD_OR_DRINK;
import com.mike.server.database.tables.ITEMS;
import com.mike.server.database.tables.MENU_PAGE;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InitialiseDatabase {
	
	@Autowired
	public DatabaseConnector dbCon;
	
	public DatabaseConnector getDbCon() {
		return dbCon;
	}

	public void setDbCon(DatabaseConnector dbCon) {
		this.dbCon = dbCon;
	}

	public InitialiseDatabase() {;
	}
	
	public void init() {
		Map<Long, ITEMS> items = new HashMap<>();
		
		ITEMS i = ITEMS.createItem("14oz Cola", 1.5f, 100, false, false, FOOD_OR_DRINK.FOOD);
		items.put(dbCon.insert(i), i);
		
		i = ITEMS.createItem("14oz Diet Cola", 1.5000f, 100, false, false, FOOD_OR_DRINK.DRINK);
		items.put(dbCon.insert(i), i);
		i = ITEMS.createItem("4oz Dash Cola", 0.3f, 1000, false, false, FOOD_OR_DRINK.DRINK);
		items.put(dbCon.insert(i), i);
		i = ITEMS.createItem("4oz Dash Diet Cola", 0.3f, 1000, false, false, FOOD_OR_DRINK.DRINK);
		items.put(dbCon.insert(i), i);
		i = ITEMS.createItem("Pint Cola", 2.0f, 100, false, false, FOOD_OR_DRINK.DRINK);
		items.put(dbCon.insert(i), i);
		i = ITEMS.createItem("Pint Diet Cola", 2.0f, 100, false, false, FOOD_OR_DRINK.DRINK);
		items.put(dbCon.insert(i), i);
		i = ITEMS.createItem("Cheese Pie", 6.95f, 5509, true, false, FOOD_OR_DRINK.FOOD);
		items.put(dbCon.insert(i), i);
		i = ITEMS.createItem("Beef Burger", 5.9f, 100, true, false, FOOD_OR_DRINK.FOOD);
		items.put(dbCon.insert(i), i);
		i = ITEMS.createItem("Beef Lasagne", 8.95f, 150, false, false, FOOD_OR_DRINK.FOOD);
		items.put(dbCon.insert(i), i);

		Map<String, MENU_PAGE> menuPages = new HashMap<>();
		MENU_PAGE mp = MENU_PAGE.createMenuPage("BURGER_PAGE", "FOOD_PAGE", "Burgers");
		menuPages.put(dbCon.insert(mp), mp);
		mp = MENU_PAGE.createMenuPage("FOOD_PAGE", "MAIN_PAGE", "Food Menu");
		menuPages.put(dbCon.insert(mp), mp);
		mp = MENU_PAGE.createMenuPage("GRILLS_PAGE", "FOOD_PAGE", "Grills");
		menuPages.put(dbCon.insert(mp), mp);
		mp = MENU_PAGE.createMenuPage("MAIN_PAGE", null, "Main Page");
		menuPages.put(dbCon.insert(mp), mp);
		mp = MENU_PAGE.createMenuPage("MIN_MIX_PAGE", "MAIN_PAGE", "Minerals/Mixers");
		menuPages.put(dbCon.insert(mp), mp);
		mp = MENU_PAGE.createMenuPage("TRAGO_PAGE", "MAIN_PAGE", "Spirits/Liqueurs");
		menuPages.put(dbCon.insert(mp), mp);

		List<ITEMS> newItems = new ArrayList<>();
		String query = "FROM ITEMS i where i.name = '14oz Cola'";
		newItems.add(dbCon.getFirst(ITEMS.class, query));
		dbCon.updatePosInMenuTable(newItems, "MAIN_PAGE");
		
		newItems = new ArrayList<>();
		query = "FROM ITEMS i where i.name = '14oz Cola'";
		newItems.add(dbCon.getFirst(ITEMS.class, query));
		query = "FROM ITEMS i where i.name = '14oz Diet Cola'";
		newItems.add(dbCon.getFirst(ITEMS.class, query));
		query = "FROM ITEMS i where i.name = '4oz Dash Cola'";
		newItems.add(dbCon.getFirst(ITEMS.class, query));
		query = "FROM ITEMS i where i.name = '4oz Dash Diet Cola'";
		newItems.add(dbCon.getFirst(ITEMS.class, query));
		query = "FROM ITEMS i where i.name = 'Pint Cola'";
		newItems.add(dbCon.getFirst(ITEMS.class, query));
		query = "FROM ITEMS i where i.name = 'Pint Diet Cola'";
		newItems.add(dbCon.getFirst(ITEMS.class, query));
		dbCon.updatePosInMenuTable(newItems, "MIN_MIX_PAGE");

		newItems = new ArrayList<>();
		query = "FROM ITEMS i where i.name = 'Cheese Pie'";
		newItems.add(dbCon.getFirst(ITEMS.class, query));
		query = "FROM ITEMS i where i.name = 'Beef Lasagne'";
		newItems.add(dbCon.getFirst(ITEMS.class, query));
		dbCon.updatePosInMenuTable(newItems, "FOOD_PAGE");

		newItems = new ArrayList<>();
		query = "FROM ITEMS i where i.name = 'Beef Burger'";
		newItems.add(dbCon.getFirst(ITEMS.class, query));
		dbCon.updatePosInMenuTable(newItems, "BURGER_PAGE");

//		insertPosInMenu(29L, "FOOD_PAGE");
	}
	
	

}