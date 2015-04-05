/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.StringReader;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author Sandeep
 */
@MessageDriven(mappedName = "jms/Queue")
public class productListener implements MessageListener{
    
    @Inject
    private Productlist productList;

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                
                String json = ((TextMessage) message).getText();
                JsonObject ob = Json.createReader(new StringReader(json)).readObject();
                productList.add(new product(ob));
                
            }
        } catch (JMSException ex) {
            System.out.println("JMS Exception " + ex.getMessage());
        } catch (Exception ex) {
            
            System.out.println("Show exception in addition of product: " + ex.getMessage());
        }
    }
    
}
