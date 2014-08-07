package bobdust.sockets;

import java.io.IOException;

interface PipelineReceiver {

	void receive(Pipeline pipeline, BinarySequence data) throws IOException;
	
}
