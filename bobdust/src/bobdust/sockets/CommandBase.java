package bobdust.sockets;

import java.io.*;

abstract class CommandBase extends BinarySequenceBase 
	implements BinarySequence, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5008581139377401105L;
	
	protected String operationName;

	public String getOperationName() {
		return operationName;
	}
	
	protected CommandBase()
	{
		
	}
	
	public CommandBase(String operationName)
	{
		this.operationName = operationName;
	}
	
	protected void copyFrom(CommandBase command)
	{
		operationName = command.getOperationName();
	}
	
	@Override
	public void write(OutputStream stream) throws IOException {
		ObjectOutputStream writer = new ObjectOutputStream(stream);
		writer.writeObject(this);
	}

	@Override
	public void read(InputStream stream) throws IOException {
		ObjectInputStream reader = new ObjectInputStream(stream);
		try
		{
			CommandBase command = (CommandBase)reader.readObject();
			copyFrom(command);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
}

