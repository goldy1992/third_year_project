package com.github.goldy1992.rms.server;

import com.github.goldy1992.rms.message.Request.RegisterClientRequest;
import com.github.goldy1992.rms.message.Table;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Goldy
 */
public class Server
{
    private HashSet<String> waiterClient = new HashSet<>();
    private HashSet<String> tillClient = new HashSet<>();
    private String barClient = null;
    private String kitchenClient = null;
    private HashMap<Integer, Table> tables;
    
    public Server(HashSet<String> waiterClient,
                HashSet<String> tillClient) throws IOException {
        this.setWaiterClient(waiterClient);
        this.setTillClient(tillClient);
        
    } // myserver
    
    public Server() {         }


	public HashSet<String> getWaiterClient() {
		return waiterClient;
	}
	public void setWaiterClient(HashSet<String> waiterClient) {
		this.waiterClient = waiterClient;
	}

	public HashSet<String> getTillClient() {
		return tillClient;
	}
	public void setTillClient(HashSet<String> tillClient) {
		this.tillClient = tillClient;
	}

	public String getBarClient() {
		return barClient;
	}
	public void setBarClient(String barClient) {
		this.barClient = barClient;
	}

	public String getKitchenClient() {
		return kitchenClient;
	}
	public void setKitchenClient(String kitchenClient) {
		this.kitchenClient = kitchenClient;
	}

	public HashMap<Integer, Table> getTables() {
		return tables;
	}
	public void setTables(HashMap<Integer, Table> tables) {
		this.tables = tables;
	}

	public boolean registerClient(RegisterClientRequest.ClientType clientType, String address) {
		switch(clientType)
		{
			case BAR:
				if (this.barClient == null) {
					barClient = address;
					return true;
				}
				return false;
			case KITCHEN:
				if (this.kitchenClient == null) {
					kitchenClient = address;
					return true;
				}
				return false;
			case WAITER:
				return waiterClient.add(address);
			case TILL:
				return tillClient.add(address);
			default: return false;
		} // switch
	}

	public boolean removeClient(String ipAddress) {
		for (String client : waiterClient) {
			if (ipAddress.equals(client)) {
				return waiterClient.remove(client);
			}
		}

		for (String client : tillClient) {
			if (ipAddress.equals(client)) {
				return tillClient.remove(client);
			}
		}

		if (ipAddress.equals(kitchenClient)) {
			kitchenClient = null;
			return true;
		}

		if (ipAddress.equals(barClient)) {
			barClient = null;
			return true;
		}

		return false;
	}

	public HashSet<String> getClients() {
    	HashSet<String> toReturn = new HashSet<>();
    	toReturn.addAll(getTillClient());
    	toReturn.addAll(getWaiterClient());
    	return toReturn;
	}
} // MySocket class
