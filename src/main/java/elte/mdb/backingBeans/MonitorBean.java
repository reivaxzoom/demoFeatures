
package elte.mdb.backingBeans;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.hornetq.api.core.management.ObjectNameBuilder;
import org.hornetq.api.jms.management.JMSQueueControl;

import elte.topics.testing.TopicMessageReceiver;

@ManagedBean	
public class MonitorBean {

//	private static final String JMX_URL = "service:jmx:rmi:///jndi/rmi://localhost:3000/jmxrmi";
	private static final String JMX_URL = "service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi";
	
	public final static String CNN_FACTORY = "/ConnectionFactory";
	public final static String QUEUE_NAME = "java:jboss/exported/TestTopicOne";
	private String message;
	private String type;
	private String browseType;
	private String browseResult;

	static PrintWriter out;
	private TopicConnectionFactory connectionFactory;
	private TopicConnection tcon;
	private TopicSession tsession;
	private Topic topic;
	private TopicMessageReceiver service;

	
	public MonitorBean() {
	
		}
	
	public void startService(){
		service= new TopicMessageReceiver();
		service.getMessages();
	}
	
	
	
	public void getMessages()   {
		Connection connection;
		
		try {
			InitialContext ic =  new InitialContext();
			connectionFactory = (TopicConnectionFactory) ic.lookup(CNN_FACTORY);
			topic = (Topic) ic.lookup(QUEUE_NAME);
			connection = connectionFactory.createTopicConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(topic);
			 TextMessage message = session.createTextMessage("This is a text message");
	         System.out.println("Sent message: " + message.getText());
	         
	         // Step 9. Retrieve the ObjectName of the queue. This is used to identify the server resources to manage
	         ObjectName on = ObjectNameBuilder.DEFAULT.getJMSQueueObjectName(topic.getTopicName());

	         // Step 10. Create JMX Connector to connect to the server's MBeanServer
	         JMXConnector connector = JMXConnectorFactory.connect(new JMXServiceURL(MonitorBean.JMX_URL), new HashMap());

	         // Step 11. Retrieve the MBeanServerConnection
	         MBeanServerConnection mbsc = connector.getMBeanServerConnection();

	         // Step 12. Create a JMSQueueControl proxy to manage the queue on the server
	         JMSQueueControl queueControl = MBeanServerInvocationHandler.newProxyInstance(mbsc,
	                                                                                      on,
	                                                                                       JMSQueueControl.class,
	                                                                                      false);
	         // Step 13. Display the number of messages in the queue
	         System.out.println(queueControl.getName() + " contains " + queueControl.getMessageCount() + " messages");

	         // Step 14. Remove the message sent at step #8
	         System.out.println("message has been removed: " + queueControl.removeMessage(message.getJMSMessageID()));

	         // Step 15. Display the number of messages in the queue
	         System.out.println(queueControl.getName() + " contains " + queueControl.getMessageCount() + " messages");

	         // Step 16. We close the JMX connector
	         connector.close();

	         // Step 17. Create a JMS Message Consumer on the queue
	         MessageConsumer messageConsumer = session.createConsumer(topic);

	         // Step 18. Start the Connection
	         connection.start();

	         // Step 19. Trying to receive a message. Since the only message in the queue was removed by a management
	         // operation, there is none to consume.
	         // The call will timeout after 5000ms and messageReceived will be null
	         TextMessage messageReceived = (TextMessage)messageConsumer.receive(5000);
	         System.out.println("Received message: " + messageReceived);

			session.close();
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
