package bobdust.sockets;

import java.io.IOException;

public abstract class ResponderFactory<T> {
	
	public static <T, U extends T> Responder<T> get(final Class<U> clazz, int port) throws IOException
	{
		Responder<T> responder = new Responder<T>(port){
			@Override
			protected T getCommander() throws InstantiationException, IllegalAccessException {
				return clazz.newInstance();
			}};
		return responder;
	}

	private ResponderFactory()
	{		
	}
	
}
