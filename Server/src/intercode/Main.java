package intercode;

public class Main {

    public static void main(String[] args) {
        Thread t = new Thread(new RequestServer());
        t.start();
    }
}
