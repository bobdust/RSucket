package bobdust.sockets;

interface Command extends BinarySequence {

	String getOperationName();
	Object[] getParameters();
	CommandResult doReturn();
	CommandResult doReturn(Object value);
	CommandResult doThrow(Throwable throwable);
	
}
