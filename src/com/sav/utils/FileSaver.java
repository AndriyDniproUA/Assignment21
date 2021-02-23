package com.sav.utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileSaver {
    public static void save(String input, String filePath) {
        try (FileChannel channel = (FileChannel) Files.newByteChannel(Path.of(filePath),
                StandardOpenOption.WRITE, StandardOpenOption.APPEND)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.clear();
            buffer.put((input + "\n").getBytes(StandardCharsets.UTF_8));
            buffer.limit(buffer.position());
            buffer.position(0);
            channel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
