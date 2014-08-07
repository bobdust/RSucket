package bobdust.sockets;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

abstract class Requestor {
	
	private CommandPipeline pipeline;
	
	protected Requestor(String host, int port) throws UnknownHostException, IOException
	{
		Socket socket = new Socket(host, port);
		pipeline = new CommandPipeline(
				new SocketPipeline(socket), 
				new CommandDeserializer(){

					@Override
					public CommandBase deserialize(byte[] bytes) {
						return CommandResultImpl.fromBytes(CommandResultImpl.class, bytes);
					}});
		pipeline.open();
	}
	
	protected CommandResult send(Command command) throws InterruptedException, IOException, TimeoutException
	{
		CommandResult result = (CommandResult)pipeline.post(command);
		return result;
	}
	
	protected Object invoke(Object[] values) throws Exception
	{
		StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[2];
		String methodName = stackTrace.getMethodName();
		Command command = new CommandImpl(methodName, values);
		CommandResult result = send(command);
		Object returnValue = result.getReturnValue(); 
		if(returnValue != null)
		{
			return returnValue;
		}
		else
		{
			Exception exception = result.getException();
			if(exception != null)
			{
				throw(exception);
			}
		}
		return null;
	}
	
}
