package intercode;

import Chat.Main.Owner;
import Chat.Main.Server;
import Utilities.SqlQueryExecuter;

import java.sql.SQLOutput;

public class Main {

    public final static int SIZE = 2;
    public static Owner GAMER;
    public static SqlQueryExecuter SQLQUERYEXECUTER;

    public static void main(String[] args) {

        SQLQUERYEXECUTER = new SqlQueryExecuter("root", "root", "jdbc:mysql://localhost/intercode");

        Thread t = new Thread(new RequestServer());
        t.start();

        GAMER = new Owner();

        new Server().initiateSocket();
    }
}
