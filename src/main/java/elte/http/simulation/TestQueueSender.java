package elte.http.simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestQueueSender {

	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		TestQueueSender http = new TestQueueSender();

		System.out.println("Testing 1 - Send Http GET request");

		String[] messages={"News","Politics","whether"};
		List<String> listMessages= new ArrayList<String>();
		Random rnd = new Random();
		for (int i = 0; i < 200; i++) {
			int r = rnd.nextInt(messages.length-1);
			listMessages.add(messages[r]);
		}
		
		for (String string : listMessages) {
			http.sendGet(string);
		}

	}

	// HTTP GET request
	private void sendGet(String message) throws Exception {

//		String url = "http://jbosseapjms-elte.rhcloud.com/QueueSendServletTest?jmsMessage="+message;
//		String url = "http://jbosseapjms-elte.rhcloud.com/QueueSendServlet?jmsMessage="+message;
		String url = "http://localhost:8080/jbosseapjms/QueueSendServlet?jmsMessage="+message;
		
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}
}
