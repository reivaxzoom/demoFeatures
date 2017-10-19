package elte.ampq.remote;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class RemoteSender {
	
	private static final String QUEUE_NAME = "testQueue";

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("spotted-monkey.rmq.cloudamqp.com");
                factory.setUsername("xodamqhb");
                factory.setPassword("1tuXwWWTKHupFfebcdmw53z-yPrulGUG ");
                factory.setUri("amqp://xodamqhb:1tuXwWWTKHupFfebcdmw53z-yPrulGUG@spotted-monkey.rmq.cloudamqp.com/xodamqhb");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
	    channel.queueDeclare(QUEUE_NAME, true, false, false, null);
	    String message = "Testing amqp!";
            for (int i = 0; i < 5; i++) {
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                        
                System.out.println(" [x] Sent '" + message + "'"+ i);
                Thread.sleep(3000);
            }
	    

	    channel.close();
	    connection.close();
	}

}

