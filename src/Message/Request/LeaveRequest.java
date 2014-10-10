/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Message.Request;

import java.net.InetAddress;

/**
 *
 * @author mbbx9mg3
 */
public class LeaveRequest extends Request
{
    public LeaveRequest(InetAddress from, 
                              InetAddress to, 
                              String messageID, 
                              Request.RequestType type)
    {
        super(from, to, messageID, type);
    } // constructor
    

    
    public String toString()
    {
        String x = super.toString() + "SUBTYPE: Leave";
        x+= "\n";
        
        return x;
    }   
}
