package util;

import model.Order;
import model.Product;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil{

    public static void writeBinaryFile(String filename, Object object) throws IOException {
        if (filename == null || filename.isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be null or empty.");
        }
        if (object == null) {
            throw new IllegalArgumentException("Object cannot be null.");
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(object);
        } catch (IOException e) {
            throw new IOException("Failed to write object to binary file: " + filename, e);
        }
    }

    public static void writeTextFile(String filename, String content) throws IOException {
        if (filename == null || filename.isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be null or empty.");
        }
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null.");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
        } catch (IOException e) {
            throw new IOException("Failed to write content to text file: " + filename, e);
        }
    }

    public static Object readFromBinaryFile(String filename) throws IOException, ClassNotFoundException {
        if (filename == null || filename.isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be null or empty.");
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new IOException("Failed to read object from binary file: " + filename, e);
        }
    }

}
