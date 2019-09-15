package intercode;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HandleClient implements Runnable {

    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public HandleClient(Socket socket) {
        this.socket = socket;
        try
        {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                Object message = objectInputStream.readObject();
                Object response = process();
                objectOutputStream.writeObject(response);
                objectOutputStream.flush();
            } catch (IOException e) {
//                e.printStackTrace();
            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
            }

        }
    }

    private Object process() {
        return new Object();
    }
}
