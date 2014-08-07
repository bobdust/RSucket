package bobdust.sockets;

import java.util.*;
import java.util.concurrent.*;

class ChannelContext {
	private static ConcurrentHashMap<UUID, ChannelContext> contexts;
	private static ConcurrentHashMap<String, UUID> tokens;
	static
	{
		contexts = new ConcurrentHashMap<UUID, ChannelContext>();
		tokens = new ConcurrentHashMap<String, UUID>();
	}
	
	public static ChannelContext getCurrent()
	{
		long threadId = Thread.currentThread().getId();
		UUID token = tokens.get(String.valueOf(threadId));
		return contexts.get(token);
	}
	
	private static void setCurrent(UUID token)
	{
		long threadId = Thread.currentThread().getId();
		tokens.put(String.valueOf(threadId), token);
	}
	
	public static ChannelContext get(UUID token)
	{
		if(contexts.containsKey(token))
		{
			return contexts.get(token);
		}
		return null;
	}
	
	public static ChannelContext create(UUID token)
	{
		ChannelContext context = new ChannelContext(token);
		contexts.put(token, context);
		setCurrent(token);
		return context;
	}
	
	public static ChannelContext create()
	{
		return create(UUID.randomUUID());
	}
	
	private UUID token;
	private Object waitHandle;
	
	public UUID getToken() {
		return token;
	}

	public Object getWaitHandle() {
		return waitHandle;
	}

	private ChannelContext(UUID token)
	{
		this.token = token;
		waitHandle = new Object();
	}

	public void close() {
		contexts.remove(token);
	}	
	
}
