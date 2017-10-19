package elte.topics.testing;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TopicMessageReceiver {
	
	public final static String CNN_FACTORY="/ConnectionFactory";
	public final static String QUEUE_NAME="java:jboss/exported/TestTopicOne";
	
	private  ConnectionFactory connectionFactory;
	private Topic topic;
	private MessageConsumer politicsConsumer;
	private MessageConsumer weatherConsumer;
	private MessageConsumer newsConsumer;
	private volatile boolean result = true;

	public void getMessages()   {
		Connection connection;
		
		try {
			InitialContext ic =  new InitialContext();
			connectionFactory = (TopicConnectionFactory) ic.lookup(CNN_FACTORY);
			topic = (Topic) ic.lookup(QUEUE_NAME);
			connection = connectionFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(topic);
			
	         
	         // Step 8. Prepare two selectors
	         String redSelector = "color='red'";
	         String greenSelector = "color='green'";

	         // Step 9. Create a JMS Message Consumer that receives 'red' messages
	         MessageConsumer redConsumer = session.createConsumer(topic, redSelector);
	         redConsumer.setMessageListener(new SimpleMessageListener("red"));

	         // Step 10. Create a second JMS message consumer that receives 'green' messages
	         MessageConsumer greenConsumer = session.createConsumer(topic, greenSelector);
	         greenConsumer.setMessageListener(new SimpleMessageListener("green"));

	         // Step 11. Create another JMS message consumer that receives all messages.
	         MessageConsumer allConsumer = session.createConsumer(topic);
	         allConsumer.setMessageListener(new SimpleMessageListener("all"));

	         

			
			connection.start();
			System.out.println("connectionStarted");
			
			// Step 12. Create three messages, each has a color property
	         TextMessage redMessage = session.createTextMessage("Red");
	         redMessage.setStringProperty("color", "red");
	         TextMessage greenMessage = session.createTextMessage("Green");
	         greenMessage.setStringProperty("color", "green");
	         TextMessage blueMessage = session.createTextMessage("Blue");
	         blueMessage.setStringProperty("color", "blue");

	         // Step 13. Send the Messages
	         producer.send(redMessage);
	         System.out.println("Message sent: " + redMessage.getText());
	         producer.send(greenMessage);
	         System.out.println("Message sent: " + greenMessage.getText());
	         producer.send(blueMessage);
	         System.out.println("Message sent: " + blueMessage.getText());

			session.close();
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	public void stopService(){
		try {
			newsConsumer.setMessageListener(null);
			weatherConsumer.setMessageListener(null);
			politicsConsumer.setMessageListener(null);
		} catch (JMSException e) {
			e.printStackTrace();
		};
	}


	public MessageConsumer getPoliticsConsumer() {
		return politicsConsumer;
	}


	public void setPoliticsConsumer(MessageConsumer politicsConsumer) {
		this.politicsConsumer = politicsConsumer;
	}


	public MessageConsumer getWeatherConsumer() {
		return weatherConsumer;
	}


	public void setWeatherConsumer(MessageConsumer weatherConsumer) {
		this.weatherConsumer = weatherConsumer;
	}


	public MessageConsumer getNewsConsumer() {
		return newsConsumer;
	}


	public void setNewsConsumer(MessageConsumer newsConsumer) {
		this.newsConsumer = newsConsumer;
	}

	public class SimpleMessageListener implements MessageListener
	   {

	      private final String name;

	      public SimpleMessageListener(final String listener)
	      {
	         name = listener;
	      }

	      public void onMessage(final Message msg)
	      {
	         TextMessage textMessage = (TextMessage)msg;
	         try
	         {
	            String colorProp = msg.getStringProperty("color");
	            System.out.println("Receiver " + name +
	                               " receives message [" +
	                               textMessage.getText() +
	                               "] with color property: " +
	                               colorProp);
	            if (!colorProp.equals(name) && !name.equals("all"))
	            {
	               result = false;
	            }
	         }
	         catch (JMSException e)
	         {
	            e.printStackTrace();
	            result = false;
	         }
	      }

	   }
}

