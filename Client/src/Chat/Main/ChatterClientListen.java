package Chat.Main;

import Chat.Constant.Request;
import Chat.Request.Message;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ChatterClientListen implements Runnable{


	/**
	 * When an object implementing interface <code>Runnable</code> is used
	 * to create a thread, starting the thread causes the object's
	 * <code>run</code> method to be called in that separately executing
	 * thread.
	 * <p>
	 * The general contract of the method <code>run</code> is that it may
	 * take any action whatsoever.
	 *
	 * @see Thread#run()
	 */

	private ObjectInputStream objectInputStream;

	public ChatterClientListen(ObjectInputStream objectInputStream){
		this.objectInputStream = objectInputStream;
	}

	@Override
	public void run() {
		Object got = null;
		while (true){
			try {
				got = objectInputStream.readObject();
				System.out.println("got");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			process(got);

		}
	}

	private void process(Object obj){

		String req =(String) obj.toString();
		if (req.equals(String.valueOf(Request.MESSAGE))){
			_message((Message)obj);
		}else if (req.equals(String.valueOf(Request.GROUPJOINED))){
			System.out.println("Joined");
		}

	}

	private void _message(Message obj) {
		System.out.println("Got the message" + obj.getContent());
	}

}
