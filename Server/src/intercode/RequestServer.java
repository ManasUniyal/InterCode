package intercode;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RequestServer implements Runnable {

    private ServerSocket serverSocket;
    private Socket socket;
    private int port = 6000;

    private void socketInitializer() {                      //Initializing the server socket, this method continues until a free port is found.
        while(true)
        {
            try
            {
                serverSocket = new ServerSocket(port);      //Create a new Server Socket
                break;
            }
            catch (IOException e)
            {
                port++;
            }
        }
        System.out.println("Server activity started on port " + port);
    }

    @Override
    public void run() {
        socketInitializer();
        while(true)
        {
            try
            {
                socket = serverSocket.accept();
                Thread t = new Thread(new HandleClient(socket));
                t.start();
            }
            catch (IOException e)
            {
//                e.printStackTrace();
            }
        }
    }

}
