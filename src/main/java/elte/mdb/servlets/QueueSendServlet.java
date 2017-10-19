package elte.mdb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QueueSendServlet extends HttpServlet {
	public final static String CNN_FACTORY = "/ConnectionFactory";
	public final static String QUEUE_NAME = "java:jboss/exported/TestQueueOne";
	private static final long serialVersionUID = 1L;
	static PrintWriter out;
	private QueueConnectionFactory qconFactory;
	private QueueConnection qcon;
	private QueueSession qsession;
	private Queue queue;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			out = response.getWriter();
			InitialContext ic = getInitialContext();
			init(ic, QUEUE_NAME);
			sendMsg(request.getParameter("jmsMessage"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init(Context ctx, String queueName) throws NamingException, JMSException {
		try {
			qconFactory = (QueueConnectionFactory) ctx.lookup(CNN_FACTORY);
			qcon = qconFactory.createQueueConnection();
			qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			queue = (Queue) ctx.lookup(queueName);
			MessageProducer producer = qsession.createProducer(queue);
			TextMessage redMessage = qsession.createTextMessage("Red");
			redMessage.setStringProperty("color", "red");
			TextMessage greenMessage = qsession.createTextMessage("Green");
			greenMessage.setStringProperty("color", "green");
			TextMessage blueMessage = qsession.createTextMessage("Blue");
			blueMessage.setStringProperty("color", "blue");
			TextMessage blackMessage = qsession.createTextMessage("Black");
			blackMessage.setStringProperty("color", "black");
			ObjectMessage objMessage = qsession.createObjectMessage(new String("demoniotaz"));
			objMessage.setStringProperty("color", "yellow");

			System.out.println("Message sent: " + redMessage.getText());
			producer.send(greenMessage);
			System.out.println("Message sent: " + greenMessage.getText());
			producer.send(blueMessage);
			System.out.println("Message sent: " + blueMessage.getText());
			producer.send(blackMessage);
			System.out.println("Message sent: " + blackMessage.getText());
			producer.send(objMessage);
			System.out.println("Message sent: " + objMessage);

			Thread.sleep(5000);
		} catch (Exception e) {
		} finally {
			// Step 12. Be sure to close our JMS resources!
			if (ctx != null) {
				ctx.close();
			}
			if (qcon != null) {
				qcon.close();
			}
		}

	}

	private void sendMsg(String userMessage) throws IOException, JMSException {
		out.println("");
		out.println("");
		out.println("Sended from old servlet");
		out.println("<h1>Queue Sender Servlet</h1>");
		out.println("Following Messages has been sent !!!");
		out.println("<BR>====================================<BR>");
		// msg.setText(userMessage);
		// qsender.send(msg);
		out.println("Message Sent => " + userMessage);
		out.println("<BR>====================================");
		out.println("");
		out.println("");
		out.println("");
		qcon.close();
		qsession.close();

	}

	private static InitialContext getInitialContext() throws NamingException {
		return new InitialContext();
	}
}
