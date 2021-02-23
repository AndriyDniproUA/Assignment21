package com.sav.runnables;

import com.sav.utils.FileSaver;

import java.util.ArrayList;
import java.util.List;

public class FibonaciRunnable implements Runnable {
    private final int n;
    private final String filePath;

    public FibonaciRunnable(int n, String filePath) {
        this.n = n;
        this.filePath = filePath;
    }

    List<Integer> list = new ArrayList<>();

    @Override
    public void run() {
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                list.add(1);
            } else if (i == 1) {
                list.add(1);
            } else {
                list.add(list.get(i - 1) + list.get(i - 2));
            }
        }

        synchronized (filePath) {
            String result = "fibonaci: " + list.get(n - 1);
            System.out.println(result);
            FileSaver.save(result, filePath);
        }
    }
}
