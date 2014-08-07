package bobdust.sockets;

interface CommandDeserializer {
	CommandBase deserialize(byte[] bytes);
}
