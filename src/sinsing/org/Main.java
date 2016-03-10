package sinsing.org;


import javax.sound.sampled.*;

import java.io.File;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static synchronized void play(final String fileName , final int count)
    {
        // Note: use .wav files
        new Thread(new Runnable() {
            public void run() {
                String filePath = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath() + fileName;
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(filePath));
                    clip.open(inputStream);
                    clip.loop(count);
                } catch (Exception e) {
                    System.out.println("play sound error: " + e.getMessage() + " for " + filePath);
                }
            }
        }).start();
    }

    public static long delayCalculator( String [] args ){
        long delay = 0 ;

        if( args.length != 3) {
            Scanner console = new Scanner(System.in);
            System.out.print( "input hours: ");
            delay = console.nextInt() * 3_600_000;
            System.out.print( "input minutes: ");
            delay += console.nextInt() * 60_000 ;
            System.out.print( "input seconds: ");
            delay += console.nextInt() * 1000 ;
        } else {
            delay += Integer.parseInt(args[0]) * 3_600_000;
            delay += Integer.parseInt(args[1]) * 60_000;
            delay += Integer.parseInt(args[2]) * 1000;
        }

        return delay;
    }

    public static void main(String[] args) throws Exception{

        long delay = delayCalculator( args );

        // creating timer task, timer
        TimerTask newTask = new TimerScheduleDelay();
        Timer timer = new Timer("TimerThread");

        // scheduling the task at interval
        timer.schedule(newTask, delay);
    }


    private static class TimerScheduleDelay extends TimerTask {
        @Override
        public void run() {
            play("../public/alarm/Alarm.wav", 99);
        }
    }
}