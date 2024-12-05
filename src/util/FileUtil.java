package util;

import java.io.*;

public class FileUtil {

    public static void writeToFile(String filename, String content) throws IOException {
        if (filename == null || filename.isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be null or empty.");
        }
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
        } catch (IOException e) {
            throw new IOException("Failed to write to file: " + filename, e);
        }
    }

    public static String readFromFile(String filename) throws IOException {
        if (filename == null || filename.isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be null or empty.");
        }

        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new IOException("Failed to read from file: " + filename, e);
        }

        return content.toString();
    }
}


