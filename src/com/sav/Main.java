package com.sav;

import com.sav.runnables.FibonaciRunnable;
import com.sav.runnables.PhoneReaderRunnable;
import com.sav.runnables.ReadIntRunnable;

public class Main {
    public static void main(String[] args) {
        String filePath="out.txt";


        Thread th1 = new Thread(new FibonaciRunnable(15, filePath));
        th1.start();

        Thread th2 = new Thread(new ReadIntRunnable("numbers.txt", 10, filePath));
        th2.start();

        Thread th3 = new Thread(new PhoneReaderRunnable("test.txt", filePath));
        th3.start();


        try {
            th1.join();
            th2.join();
            th3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All processed are finished.");

    }
}







