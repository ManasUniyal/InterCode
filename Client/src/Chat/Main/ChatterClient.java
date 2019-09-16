package Chat.Main;

import Chat.Request.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ChatterClient {

	private String name;
	private String group;

	private Socket socket;
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;

	public ChatterClient(String name, String ip, int port){
		this.name = name;
		try {
			socket = new Socket(ip,port);
			this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			this.objectInputStream = new ObjectInputStream(socket.getInputStream());
			this.objectOutputStream.writeObject(new WhoIAm(name));
			this.objectOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void write(Object obj){
		try {
			objectOutputStream.writeObject(obj);
			objectOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Object read(){
		try {
			return objectInputStream.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean createGroup(String name){
		System.out.println("kill");
		write(new CreateGroup(name));
		System.out.println("hi");
		Response res = (Response)read();
		System.out.println("die");
		if (res.getStatus() == 0){
			startRead();
			this.group = name;
			return true;
		}else{
			return false;
		}
	}

	public boolean joinGroup(String name){
		write(new JoinGroup(name));
		System.out.println("dieeeeeeeeeeee");
		Response res = (Response)read();
		System.out.println("hiiiiiiiiiii");
		if (res.getStatus() == 0){
			startRead();
			this.group = name;
			return true;
		}else{
			return false;
		}
	}

	public void sendMessage(Message message){
		write(message);
		System.out.println("sent");

	}

	private void startRead(){
		new Thread(new ChatterClientListen(objectInputStream)).start();
	}

}
