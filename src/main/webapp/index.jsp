<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
          <title> Simple App Level JMS Queue/Topic Demo </title>
   </head>
   <body>
      <center>
         <form action="QueueSendServlet">
             
            <label>Enter Message to Send:</label>
            <br>
            <label>Esta es otra prueba</label><Br>
            <textarea name="jmsMessage"cols="20" rows="10"> Message Here</textarea>
            <br>
                <input type="Submit" value="Send JMS Message" /> 
                <input type="Reset" value="Clear" />
         </form>
      </center>
   </body>
</html>