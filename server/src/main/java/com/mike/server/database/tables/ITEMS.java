package com.mike.server.database.tables;

import java.util.HashSet;
import java.util.Set;

public class ITEMS {
	
	private long id;
	private String name;
	private float price;
	private int quantity;
	private boolean stockCountOn;
	private boolean needAgeCheck;
	private FOOD_OR_DRINK foodOrDrink;
	
	private Set<MENU_PAGE> menuPages = new HashSet<>(0);
	
	public static ITEMS createItem(String name, float price, int quantity, boolean stockCountOn, 
			boolean needAgeCheck, FOOD_OR_DRINK foodOrDrink) {
		ITEMS item = new ITEMS();
		item.name = name;
		item.price = price;
		item.quantity = quantity;
		item.stockCountOn = stockCountOn;
		item.needAgeCheck = needAgeCheck;
		item.foodOrDrink = foodOrDrink;
		return item;
		
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isStockCountOn() {
		return stockCountOn;
	}

	public void setStockCountOn(boolean stockCountOn) {
		this.stockCountOn = stockCountOn;
	}

	public boolean isNeedAgeCheck() {
		return needAgeCheck;
	}

	public void setNeedAgeCheck(boolean needAgeCheck) {
		this.needAgeCheck = needAgeCheck;
	}

	public FOOD_OR_DRINK getFoodOrDrink() {
		return foodOrDrink;
	}

	public void setFoodOrDrink(FOOD_OR_DRINK foodOrDrink) {
		this.foodOrDrink = foodOrDrink;
	}


	public Set<MENU_PAGE> getMenuPages() {
		return menuPages;
	}


	public void setMenuPages(Set<MENU_PAGE> menuPages) {
		this.menuPages = menuPages;
	}
	
}
