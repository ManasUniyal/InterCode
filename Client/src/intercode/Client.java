package intercode;

import java.io.*;
import java.net.Socket;

public class Client implements Serializable {

	private String name;
	private Socket socket;
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;

	public Client(String ip, int port, String name) {
		try {
			this.name = name;
			this.socket = new Socket(ip, port);
			this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			this.objectInputStream = new ObjectInputStream(socket.getInputStream());
			System.out.println("ClientDetails created.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send_message(Object object) throws IOException {
		objectOutputStream.writeObject(object);
		objectOutputStream.flush();
	}

	public Object receive_message() throws IOException, ClassNotFoundException {
		return (Object) objectInputStream.readObject();
	}

}