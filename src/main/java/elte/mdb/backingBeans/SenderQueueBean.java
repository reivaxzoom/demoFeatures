package elte.mdb.backingBeans;

import java.io.PrintWriter;
import java.util.Enumeration;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import elte.mdb.refactoring.AsynchMessReceiver;

@ManagedBean
public class SenderQueueBean {

	public final static String CNN_FACTORY = "/ConnectionFactory";
	public final static String QUEUE_NAME = "java:jboss/exported/TestQueueOne";
	private String message;
	private String type;
	private String browseType;
	private String browseResult;

	static PrintWriter out;
	private QueueConnectionFactory qconFactory;
	private QueueConnection qcon;
	private QueueSession qsession;
	private Queue queue;
	private AsynchMessReceiver service;

	public void startService(){
		service= new AsynchMessReceiver();
		service.getMessages();
	}
	
	

	public void  queueBrowser(){
		StringBuilder out= new StringBuilder("OutputStarted\n");
		try {
			InitialContext ctx = new InitialContext();
			qconFactory = (QueueConnectionFactory) ctx.lookup(CNN_FACTORY);
			qcon = qconFactory.createQueueConnection();
			qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			queue = (Queue) ctx.lookup(QUEUE_NAME);
			String selector = "tipo='"+getBrowseType() +"'";
			QueueBrowser browser = qsession.createBrowser(queue,selector);
			
			  Enumeration<TextMessage> messageEnum = browser.getEnumeration();
		         while (messageEnum.hasMoreElements())
		         {
		            TextMessage message = (TextMessage)messageEnum.nextElement();
		            String tipo= message.getStringProperty("tipo");
		            out.append("Browsing: " + message.getText()+" tipo: "+tipo +"\n");
		            System.out.println("Browsing: " + message.getText()+"; tipo: "+tipo);
		         }
		         // Step 11. Close the browser
		         browser.close();
		         setBrowseResult(out.toString());
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessageQueue() {

		try {
			InitialContext ctx = new InitialContext();
			qconFactory = (QueueConnectionFactory) ctx.lookup(CNN_FACTORY);
			qcon = qconFactory.createQueueConnection();
			qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			queue = (Queue) ctx.lookup(QUEUE_NAME);
			MessageProducer producer = qsession.createProducer(queue);
			TextMessage message = qsession.createTextMessage(getMessage());
			message.setStringProperty("tipo", getType());
			
			
			System.out.println("Message sent: " + getMessage());
			producer.send(message);
			
			FacesMessage confMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, confMessage);
			
			
			producer.close();
			qsession.close();
			qcon.close();
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
