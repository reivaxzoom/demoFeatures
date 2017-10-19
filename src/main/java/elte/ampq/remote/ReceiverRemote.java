package elte.ampq.remote;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class ReceiverRemote {

	private final static String QUEUE_NAME = "testQueue";

	public static void main(String[] argv) throws java.io.IOException, java.lang.InterruptedException, URISyntaxException, NoSuchAlgorithmException, KeyManagementException {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("spotted-monkey.rmq.cloudamqp.com");
                factory.setUsername("xodamqhb");
                factory.setPassword("1tuXwWWTKHupFfebcdmw53z-yPrulGUG ");
                factory.setUri("amqp://xodamqhb:1tuXwWWTKHupFfebcdmw53z-yPrulGUG@spotted-monkey.rmq.cloudamqp.com/xodamqhb");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, consumer); 

		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println(" [x] Received '" + message + "'");
		}		
	}

}

