/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import java.net.InetAddress;

/**
 *
 * @author mbbx9mg3
 */
public abstract class ResponseMessage extends Message {
    
    
    private RequestMessage request;
    
    public ResponseMessage(InetAddress from, InetAddress to, String messageID)
    {
       super(from, to, messageID);    
    }
    
}
