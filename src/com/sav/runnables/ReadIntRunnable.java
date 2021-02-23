package com.sav.runnables;

import com.sav.utils.FileSaver;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReadIntRunnable implements Runnable {
    private final String inputFile;
    private final int n;
    private final String filePath;

    public ReadIntRunnable(String inputFile, int n, String filePath) {
        this.inputFile = inputFile;
        this.n = n;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        int sum = 0;
        int counter = 0;

        Path path = Paths.get(inputFile);
        Scanner scanner = null;
        try {
            scanner = new Scanner(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (scanner.hasNext() && counter < n) {
            if (scanner.hasNextInt()) {
                sum += scanner.nextInt();
                counter++;
            } else {
                scanner.next();
            }
        }

        synchronized (filePath) {
            String result = "Sum: " + sum;
            System.out.println(result);
            FileSaver.save(result, filePath);
        }
    }
}
