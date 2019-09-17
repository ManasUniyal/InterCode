package intercode;

import java.util.TimerTask;

public class Helper extends TimerTask
{
    public static int i = 0;
    public void run()
    {
        Main.timer_.setLabel("Hello");
    }
}