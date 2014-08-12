package bobdust.sockets;

import java.io.IOException;

abstract class PipelineDecorator extends PipelineBase {
	@Override
	public void write(byte[] buffer) throws IOException {
		pipeline.write(buffer);
	}

	@Override
	public int read(byte[] buffer) throws IOException {
		return pipeline.read(buffer);
	}

	private Pipeline pipeline;
	
	protected PipelineDecorator(Pipeline pipeline)
	{
		this.pipeline = pipeline;
	}
	
	@Override
	public void onException(Exception exception)
	{
		pipeline.onException(exception);
		close();
	}
}
