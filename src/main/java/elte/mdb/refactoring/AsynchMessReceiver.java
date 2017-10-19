package elte.mdb.refactoring;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class AsynchMessReceiver {
	
	public final static String CNN_FACTORY="/ConnectionFactory";
	public final static String QUEUE_NAME="java:jboss/exported/TestQueueOne";
	
	private  ConnectionFactory connectionFactory;
	private Queue queue;
	private MessageConsumer politicsConsumer;
	private MessageConsumer weatherConsumer;
	private MessageConsumer newsConsumer;
	

	public void getMessages()  {
		Connection connection;
		
		try {
			InitialContext ic =  new InitialContext();
			connectionFactory = (QueueConnectionFactory) ic.lookup(CNN_FACTORY);
			queue = (Queue) ic.lookup(QUEUE_NAME);
			connection = connectionFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			
			 String politicsSel = "tipo='politics'";
	         String weatherSel = "tipo='weather'";
	         String newsSel = "tipo='news'";
			
	         politicsConsumer = session.createConsumer(queue, politicsSel);
	         politicsConsumer.setMessageListener(new PoliticsMDBean());
	         weatherConsumer = session.createConsumer(queue, weatherSel);
	         weatherConsumer.setMessageListener(new WeatherMDBean());
	         newsConsumer = session.createConsumer(queue,newsSel);
	         newsConsumer.setMessageListener(new NewsMDBean());

			
			connection.start();
			System.out.println("connectionStarted");
			
			politicsConsumer.close();
			weatherConsumer.close();
			newsConsumer.close();
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
}
