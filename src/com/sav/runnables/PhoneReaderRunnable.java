package com.sav.runnables;

import com.sav.utils.FileSaver;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneReaderRunnable implements Runnable {
    private final String inputFile;
    private final String filePath;

    public PhoneReaderRunnable(String inputFile, String filePath) {
        this.inputFile = inputFile;
        this.filePath = filePath;
    }

    @Override
    public void run() {


        Path path = Paths.get(inputFile);
        Scanner scanner = null;
        try {
            scanner = new Scanner(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pattern pattern = Pattern.compile("\\+38\\d{10}");
        Matcher matcher;
        String phones = "";
        boolean firstEntry = true;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            matcher = pattern.matcher(line);
            if (matcher.find()) {
                if (!firstEntry) {
                    phones += ", " + matcher.group();
                } else {
                    phones += matcher.group();
                    firstEntry = false;
                }
            }
        }

        synchronized (filePath) {
            String result = "Phones: " + phones;
            System.out.println(result);
            FileSaver.save(result,filePath);
        }
    }
}
