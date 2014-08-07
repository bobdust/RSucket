package bobdust.sockets;

class CommandImpl extends CommandBase implements Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6501789125198521864L;
	private Object[] parameters;
	
	public CommandImpl()
	{
		super();
	}
	
	public CommandImpl(String operationName, Object[] parameters)
	{
		super(operationName);
		this.parameters = parameters;
	}
	
	@Override
	protected void copyFrom(CommandBase commandBase)
	{
		super.copyFrom(commandBase);
		Command command = (Command)commandBase;
		parameters = command.getParameters();
	}
		
	@Override
	public Object[] getParameters() {
		return parameters;
	}

	@Override
	public CommandResult doReturn() {
		return new CommandResultImpl(operationName);
	}

	@Override
	public CommandResult doReturn(Object value) {
		return new CommandResultImpl(operationName, value);
	}

	@Override
	public CommandResult doThrow(Throwable exception) {
		return new CommandResultImpl(operationName, exception);
	}

}
