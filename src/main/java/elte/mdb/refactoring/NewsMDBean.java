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
//	            propertyValue = "tipo = 'news' ")
//	    , @ActivationConfigProperty(propertyName = "subscriptionDurability",
//	            propertyValue = "Durable")
//	    , @ActivationConfigProperty(propertyName = "clientId",
//	            propertyValue = "MyID")
//	    , @ActivationConfigProperty(propertyName = "subscriptionName",
//	            propertyValue = "MySub")
//	    })

 
public class NewsMDBean implements MessageListener
{
    public void onMessage(Message message)
    {
        TextMessage textMessage = (TextMessage) message;
        try
       {
        	Thread.sleep((long)(Math.random() * 1000));
            System.out.println("+NewsMDBReceived:    "+ textMessage.getText()+" "+textMessage.getJMSType());
        }
        catch (JMSException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}