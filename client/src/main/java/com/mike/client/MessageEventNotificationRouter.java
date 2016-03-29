package com.mike.client;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;

import com.mike.message.EventNotification.EventNotification;
import com.mike.message.EventNotification.TableStatusEvtNfn;

@MessageEndpoint
public class MessageEventNotificationRouter {
	 @Router(inputChannel = "typeRouterToEventNotificationChannel")
	  public String accept(EventNotification notification){
	        if (notification instanceof TableStatusEvtNfn) {
	        	System.out.println("received table status event nfn");
	        	return "tableStatusEvtNotificationChannel";
	        
	        }
	        return "";
	        
	 }


}