package sinsing.org;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.SynchronousQueue;

public class Main {

    public static void main(String[] args) {
        // creating timer task, timer
        TimerTask tasknew = new TimerScheduleDelay();
        Timer timer = new Timer();

        Scanner console = new Scanner(System.in);
        System.out.print( "input hours: ");
        long delay = console.nextInt() * 3_600_000;
        System.out.print( "input minutes: ");
        delay += console.nextInt() * 60_000 ;
        System.out.print( "input seconds: ");
        delay += console.nextInt() * 1000 ;

        // scheduling the task at interval
        timer.schedule(tasknew, delay);
        System.exit(0);
    }

    private static class TimerScheduleDelay extends TimerTask {
        @Override
        public void run() {
            System.out.println("Alarm !");
        }
    }
}
