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

            // Ghi thông tin Order vào file văn bản (order.txt)
            String orderText = "Order ID: " + order.getProduct().getProductId() +
                    "\nUser: " + order.getUser().getUsername() +
                    "\nProduct: " + order.getProduct().getName() +
                    "\nPrice: " + order.getProduct().getPrice();
            FileUtil.writeTextFile("order.txt", orderText);

            // Ghi thông tin Product vào file nhị phân (product.dat)
            FileUtil.writeBinaryFile("product.dat", product);

            // Ghi thông tin User vào file nhị phân (user.dat)
            FileUtil.writeBinaryFile("user.dat", user);

            // Đọc lại thông tin Order từ file văn bản và hiển thị
            System.out.println("Order Details (from text file): ");
            System.out.println(orderText);

            // Đọc lại thông tin Product từ file nhị phân và hiển thị
            Product readProduct = (Product) FileUtil.readFromBinaryFile("product.dat");
            System.out.println("Product Details (from binary file): ");
            System.out.println("Product ID: " + readProduct.getProductId());
            System.out.println("Product Name: " + readProduct.getName());
            System.out.println("Product Price: " + readProduct.getPrice());

            // Đọc lại thông tin User từ file nhị phân và hiển thị
            User readUser = (User) FileUtil.readFromBinaryFile("user.dat");
            System.out.println("User Details (from binary file): ");
            System.out.println("Username: " + readUser.getUsername());
            System.out.println("Email: " + readUser.getEmail());

        } catch (ValidationException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Đóng Scanner để giải phóng tài nguyên
            scanner.close();
        }
    }
}
