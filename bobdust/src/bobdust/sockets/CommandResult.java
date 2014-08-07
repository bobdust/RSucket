package bobdust.sockets;

interface CommandResult extends BinarySequence {

	String getOperationName();
	Object getReturnValue();
	Exception getException();
	
}
