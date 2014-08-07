package bobdust.rpc.helloworld;

import java.io.IOException;

import bobdust.sockets.*;

public class Listener {
	
	public static void main(String[] args) throws IOException
	{
		Responder<GreetingAssistant> assistant = ResponderFactory.get(GreetingAssistantImpl.class, 1234);
		assistant.start();
		System.out.println("Greeting Assistant started.");
	}

}
