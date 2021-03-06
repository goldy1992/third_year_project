/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.goldy1992.rms.server;

import com.github.goldy1992.rms.message.EventNotification.EventNotification;
import com.github.goldy1992.rms.message.Message;
import com.github.goldy1992.rms.message.Request.Request;
import org.apache.log4j.Logger;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;

/**
 *
 * @author Mike
 */
@MessageEndpoint
public class MessageTypeRouter {

    final static Logger logger = Logger.getLogger(MessageTypeRouter.class);
    @Router(inputChannel="filterToMessageTypeRouterChannel")
    public String accept(Message message){
        logger.info("reached message router");
        if (message instanceof Request) {
        	return "messageRequestChannel";
        } else if (message instanceof EventNotification) {
            return "messageEventNotificationChannel";
        }
        
        return "messageRequestChannel";}
}

