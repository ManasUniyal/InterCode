package Chat.Main;


import Chat.Constant.Request;
import Chat.Request.*;
import intercode.Main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class HandleClient implements Runnable {

	private String name;
	private String group;

	private Socket socket;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;

	public HandleClient(Socket socket) {
		this.socket = socket;
		try {
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			WhoIAm ob1 = (WhoIAm) objectInputStream.readObject();
			Main.GAMER.add_client("extra", ob1.getName(),objectOutputStream);
			System.out.println("Client Got and name set");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

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



	@Override
	public void run() {
		Object got = null;
		while (true){
			try {
				got = objectInputStream.readObject();
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
		}else if (req.equals(String.valueOf(Request.CREATEGROUP))){

				//      Do error handelling latter

				Main.GAMER.add_group(((CreateGroup)(obj)).getName(),"");
				this.group = ((CreateGroup)(obj)).getName();
				Main.GAMER.remove_client("extra",this.name);
				Main.GAMER.add_client(((CreateGroup)(obj)).getName(),this.name,this.objectOutputStream);

		}else if (req.equals(String.valueOf(Request.JOINGROUP))){

				//      Do error handelling latter

				this.group = ((JoinGroup)(obj)).getName();
				Main.GAMER.add_client(((JoinGroup)(obj)).getName(),this.name,this.objectOutputStream);
				Main.GAMER.remove_client("extra",this.name);
				Main.GAMER.send_message(new GroupJoined("hi"),this.group);


		}

	}

	private void _message(Message obj) {
		System.out.println("Got the message");
		Main.GAMER.send_message(obj,this.group);
	}
















//
//	@Override
//	public void run() {
//
//		boolean flag;
//
//
//		while (true) {
//			try {
//
//				Object message = (Object) objectInputStream.readObject();
//				System.out.println("Message received");
//
//
//				/*      If Else for server handelling           */
//
//				String req = (String) message.toString();
//
//
//
////				if (req.equals(String.valueOf(Request.GROUPPASS))){
////
////					System.out.println("Group creation request");
////
////					do {
////						GroupPass ob2 = (GroupPass) message;
////						if(GAMER.add_group(ob2.get_group_name(),ob2.get_password())){
////							GAMER.send_message(new Response(0,""),ob2.get_group_name(),ob2.get_client_name());
////							flag = false;
////							GAMER.remove_client("extra",ob2.get_client_name());
////							GAMER.add_client(ob2.get_group_name(),ob2.get_client_name(),objectOutputStream);
////							System.out.println("Client successfully added to the specified group");
////						}
////						else{
////							flag = true;
////							GAMER.send_message(new Response(1,"Group already exist please try a new name."),ob2.get_group_name(),ob2.get_client_name());
////							System.out.println("There was a problem retrying");
////						}
////					}while(flag);
////
////				}else if (req.equals(String.valueOf(Request.GROUPLIST))){
////
////					System.out.println("Group list Request");
////
////					GroupList ob3 = (GroupList)(message);
////					GAMER.send_message((Object)GAMER.get_group_list(),ob3.getter());
////
////				}
////
//
//
//
//
//
//
//
//
//
//				/*  Do Rest of processing on the object here    */
//
//			} catch (Exception e) {
//				System.out.println("Client Disconnected");
//				e.printStackTrace();
//			}
//		}
//	}
//
//
//}
}