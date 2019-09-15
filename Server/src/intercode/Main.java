package intercode;

import Chat.Main.Owner;
import Chat.Main.Server;

public class Main {

    public final static int SIZE = 2;
    public static Owner GAMER;

    public static void main(String[] args) {

        Thread t = new Thread(new RequestServer());
        t.start();

        GAMER = new Owner();

        new Server().initiateSocket();
    }
}
