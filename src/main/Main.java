package main;

import model.*;
import service.*;
import exception.ValidationException;
import factory.UserFactory;
import util.FileUtil;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Create User using Factory
            User user = UserFactory.createUser("john_doe", "john.doe@example.com");

            // Validate User
            UserService userService = new UserService();
            userService.validateUser(user);

            // Create Product and Order
            Product product = new Product("P001", "Laptop", 1200.00);
            Order order = new Order(user, product);

            // Write Order to File
            FileUtil.writeToFile("order.txt", "Order ID: " + order.getProduct().getProductId() + "\nUser: " + order.getUser().getUsername());

            // Read Order from File
            String orderContent = FileUtil.readFromFile("order.txt");
            System.out.println("Order Details: \n" + orderContent);
        } catch (ValidationException | IOException e) {
            e.printStackTrace();
        }
    }
}
