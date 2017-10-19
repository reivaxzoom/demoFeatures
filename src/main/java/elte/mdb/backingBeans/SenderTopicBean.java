package elte.mdb.backingBeans;


import java.io.PrintWriter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import elte.topics.testing.TopicMessageReceiver;

@ManagedBean
public class SenderTopicBean {

	public final static String CNN_FACTORY = "/ConnectionFactory";
	public final static String QUEUE_NAME = "java:jboss/exported/TestTopicOne";
	private String message;
	private String type;
	private String browseType;
	private String browseResult;

	static PrintWriter out;
	private TopicConnectionFactory tconFactory;
	private TopicConnection tcon;
	private TopicSession tsession;
	private Topic topic;
	private TopicMessageReceiver service;

	public void startService(){
		service= new TopicMessageReceiver();
		service.getMessages();
	}
	
	
	public void sendMessageTopic() {

		try {
			InitialContext ctx = new InitialContext();
			tconFactory = (TopicConnectionFactory) ctx.lookup(CNN_FACTORY);
			tcon = tconFactory.createTopicConnection();
			tsession = tcon.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			topic = (Topic) ctx.lookup(QUEUE_NAME);
			MessageProducer producer = tsession.createProducer(topic);
			TextMessage message = tsession.createTextMessage(getMessage());
			message.setStringProperty("tipo", getType());
			
			
			producer.send(message);
			System.out.println("Message sent: " + getMessage());
			
			FacesMessage confMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, confMessage);
			
			
			producer.close();
			tsession.close();
			tcon.close();
			ctx.close();
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getBrowseType() {
		return browseType;
	}


	public void setBrowseType(String browseType) {
		this.browseType = browseType;
	}


	public String getBrowseResult() {
		return browseResult;
	}


	public void setBrowseResult(String browseResult) {
		this.browseResult = browseResult;
	}
}
