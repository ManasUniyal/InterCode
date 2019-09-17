package intercode;

import java.util.Timer;
import java.util.TimerTask;

public class Initiator {

    public void run_(Timer_ timer_) {

    Timer timer = new Timer();
    TimerTask task = new Helper();

    timer.schedule(task,0,1000);
    }
}
