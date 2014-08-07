package bobdust.sockets;

import java.io.IOException;

interface Pipeline {
	
	String getId();
	void write(byte[] buffer) throws IOException;
	int read(byte[] buffer) throws IOException;
	void open();
	void close();
	void send(BinarySequence data) throws IOException;
	void setReceiver(PipelineReceiver receiver);

}
