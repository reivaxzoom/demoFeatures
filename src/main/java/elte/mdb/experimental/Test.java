// package elte.mdb.experimental;
//import java.util.Properties;
//
//import javax.jms.Connection;
//import javax.jms.ConnectionFactory;
//import javax.jms.Destination;
//import javax.jms.JMSException;
//import javax.jms.MessageProducer;
//import javax.jms.Queue;
//import javax.jms.QueueConnection;
//import javax.jms.QueueConnectionFactory;
//import javax.jms.QueueSender;
//import javax.jms.QueueSession;
//import javax.jms.Session;
//import javax.jms.TextMessage;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
// 
//public class Test {
//	
//	
////	public final static String CNN_FACTORY="/ConnectionFactory";
////	public final static String QUEUE_NAME="java:jboss/exported/TestQueueOne";
//	public final static String CNN_FACTORY="/RemoteConnectionFactory";
//    public final static String QUEUE_NAME="java:jboss/exported/TestQueueOne";
// 
//    private static QueueConnectionFactory qconFactory;
//    private static QueueConnection qcon;
//    private static QueueSession qsession;
//    private static QueueSender qsender;
//    private static Queue queue;
//    private static TextMessage msg;
//    
//    
//    
//    
//    
//	
//    public static void main(String[] args) throws JMSException, NamingException {
//        InitialContext ic = getInitialContext();
//        System.out.println(ic);
//    	
//        qconFactory = (QueueConnectionFactory) ic.lookup(CNN_FACTORY);
//        qcon = qconFactory.createQueueConnection();
//        qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
//        queue = (Queue) ic.lookup(QUEUE_NAME);
//        qsender = qsession.createSender(queue);
//        msg = qsession.createTextMessage();
//        qcon.start();
// 
//    	
//    	
////        Properties properties = new Properties();
////        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
////        properties.put(Context.PROVIDER_URL, "remote://localhost:4447");
////        
////        ConnectionFactory connectionFactory = null;
////        Destination destination = null;
//
//    }
// 
//    private static void sendMessage(ConnectionFactory connectionFactory, Destination destination) {
//        Connection connection = null;
//        Session session = null;
//        MessageProducer messageProducer = null;
// 
//        try {
////            connection = connectionFactory.createConnection("employee", "welcome1");
//            connection = connectionFactory.createConnection();
//            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            messageProducer = session.createProducer(destination);
// 
//            TextMessage text = session.createTextMessage();
//            text.setText("Send some useful message");
//            messageProducer.send(text);
//        } catch (JMSException e) {
//            e.printStackTrace();
//        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (JMSException f) {
//                    f.printStackTrace();
//                }
//            }
//        }
// 
//    }    
//    
//    
//    
//    public final static String JMS_CONNECTION_FACTORY_JNDI="jms/RemoteConnectionFactory";
//    public final static String JMS_QUEUE_JNDI="java:jboss/exported/TestQueueOne";
////    public final static String JMS_USERNAME="jmsuser";       //  The role for this user is "guest" in ApplicationRealm
////    public final static String JMS_PASSWORD="jmsuser@123";  
//    public final static String WILDFLY_REMOTING_URL="http-remoting://localhost:8080";
//   
//    
//    
//    private static InitialContext getInitialContext() throws NamingException {
//        InitialContext context=null;
//        try {
//              Properties props = new Properties();
//              props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
//              props.put(Context.PROVIDER_URL, WILDFLY_REMOTING_URL);   // NOTICE: "http-remoting" and port "8080"
////              props.put(Context.SECURITY_PRINCIPAL, JMS_USERNAME);
////              props.put(Context.SECURITY_CREDENTIALS, JMS_PASSWORD);
//              //props.put("jboss.naming.client.ejb.context", true);
//              context = new InitialContext(props); 
//          System.out.println("\n\tGot initial Context: "+context);     
//         } catch (Exception e) {
//              e.printStackTrace();
//         }
//       return context;
//     }
//    
//    
//    
//    
//}