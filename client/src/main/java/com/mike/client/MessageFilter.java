/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mike.client;

import com.mike.message.Message;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.messaging.handler.annotation.Headers;

import java.util.Map;

/**
 *
 * @author Mike
 */
@MessageEndpoint
public class MessageFilter {
    
    @Filter(inputChannel = "inputChannel", outputChannel="filterToMessageTypeRouterChannel")
    public boolean accept(Object message, @Headers Map<String, Object> headerMap){
        System.out.println("message received filter\nFilter headers: " + headerMap);
        if (message instanceof Message) {
        	System.out.println("returning true");
        	return true; 
        } 
        
    	return false;
        
    }
}