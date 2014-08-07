package bobdust.sockets;

import java.io.*;

interface BinarySequence {
	
	void write(OutputStream stream) throws IOException;
	void read(InputStream stream) throws IOException;
	byte[] toBytes();

}
