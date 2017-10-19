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
//	            propertyValue = "tipo = 'weather' ")
//	    , @ActivationConfigProperty(propertyName = "subscriptionDurability",
//	            propertyValue = "Durable")
//	    , @ActivationConfigProperty(propertyName = "clientId",
//	            propertyValue = "MyID")
//	    , @ActivationConfigProperty(propertyName = "subscriptionName",
//	            propertyValue = "MySub")
//	    })


public class WeatherMDBean implements MessageListener{
	public void onMessage(Message message){
        TextMessage textMessage = (TextMessage) message;
        try{
        	Thread.sleep((long)(Math.random() * 3000));
            System.out.println("-WheatherMDBReceived:  "+ textMessage.getText()+" "+textMessage.getJMSType());
        }
        catch (JMSException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}