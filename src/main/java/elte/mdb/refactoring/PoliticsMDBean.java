package elte.mdb.refactoring;

import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.ejb.MessageDriven;
import javax.ejb.ActivationConfigProperty;
 
//@MessageDriven(mappedName = "java:jboss/exported/TestQueueOne", activationConfig =  {
//		 @ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue" ),
//		 @ActivationConfigProperty(propertyName="destination", propertyValue="java:jboss/exported/TestQueueOne"),
//	    @ActivationConfigProperty(propertyName = "messageSelector",
//	            propertyValue = "tipo = 'politics' ")
//	    , @ActivationConfigProperty(propertyName = "subscriptionDurability",
//	            propertyValue = "Durable")
//	    , @ActivationConfigProperty(propertyName = "clientId",
//	            propertyValue = "MyID")
//	    , @ActivationConfigProperty(propertyName = "subscriptionName",
//	            propertyValue = "MySub")
//	    })



public class PoliticsMDBean implements MessageListener{
	
	public PoliticsMDBean() {
		super();
	}
	

	public void onMessage(Message message){
        TextMessage textMessage = (TextMessage) message;
        try{
        	Thread.sleep((long)(Math.random() * 2000)); 
			String tipo= textMessage.getStringProperty("tipo");
            System.out.println("~PoliticsMDB Received: "+ textMessage.getText()+" "+tipo);
        }
        catch (JMSException e){
        	   e.printStackTrace();
        } catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}