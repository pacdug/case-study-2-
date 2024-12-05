package main;

import model.*;
import service.*;
import exception.ValidationException;
import factory.UserFactory;
import util.FileUtil;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Tạo đối tượng Scanner để đọc dữ liệu từ bàn phím
        Scanner scanner = new Scanner(System.in);

        try {
            // Nhập thông tin người dùng
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            // Tạo User từ thông tin nhập vào
            User user = UserFactory.createUser(username, email);

            // Kiểm tra tính hợp lệ của User
            UserService userService = new UserService();
            userService.validateUser(user);

            // Nhập thông tin sản phẩm
            System.out.print("Enter product ID: ");
            String productId = scanner.nextLine();

            System.out.print("Enter product name: ");
            String productName = scanner.nextLine();

            System.out.print("Enter product price: ");
            double productPrice = scanner.nextDouble();  // Đọc số thực cho giá

            // Tạo Product từ thông tin nhập vào
            Product product = new Product(productId, productName, productPrice);

            // Tạo Order từ User và Product
            Order order = new Order(user, product);

            // Ghi Order vào file
            FileUtil.writeToFile("order.txt", "Order ID: " + order.getProduct().getProductId() +
                    "\nUser: " + order.getUser().getUsername() +
                    "\nProduct: " + order.getProduct().getName() +
                    "\nPrice: " + order.getProduct().getPrice());

            // Đọc Order từ file và hiển thị
            String orderContent = FileUtil.readFromFile("order.txt");
            System.out.println("Order Details: \n" + orderContent);

        } catch (ValidationException | IOException e) {
            e.printStackTrace();
        } finally {
            // Đóng Scanner để giải phóng tài nguyên
            scanner.close();
        }
    }
}
