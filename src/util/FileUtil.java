package util;

import java.io.*;

public class FileUtil {

    public static void writeToFile(String filename, Object object) throws IOException {
        if (filename == null || filename.isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be null or empty.");
        }
        if (object == null) {
            throw new IllegalArgumentException("Object cannot be null.");
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(object);
        } catch (IOException e) {
            throw new IOException("Failed to write object to file: " + filename, e);
        }
    }

    public static Object readFromFile(String filename) throws IOException, ClassNotFoundException {
        if (filename == null || filename.isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be null or empty.");
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new IOException("Failed to read object from file: " + filename, e);
        }
    }
}
