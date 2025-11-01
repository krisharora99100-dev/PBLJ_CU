import java.sql.*;
import java.util.Scanner;

public class Partb {
    static final String URL = "jdbc:mysql://localhost:3307/studentDB";
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            conn.setAutoCommit(false);

            while (true) {
                System.out.println("\n1. Add Product 2. View Products 3. Update Product 4. Delete Product 5. Exit");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Price: ");
                        double price = sc.nextDouble();
                        System.out.print("Quantity: ");
                        int qty = sc.nextInt();

                        try (PreparedStatement ps = conn.prepareStatement(
                                "INSERT INTO Product (ProductName, Price, Quantity) VALUES (?, ?, ?)")) {
                            ps.setString(1, name);
                            ps.setDouble(2, price);
                            ps.setInt(3, qty);
                            ps.executeUpdate();
                            conn.commit();
                            System.out.println("Product added!");
                        } catch (SQLException e) {
                            conn.rollback();
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 2:
                        try (Statement stmt = conn.createStatement()) {
                            ResultSet rs = stmt.executeQuery("SELECT * FROM Product");
                            System.out.println("ProductID\tName\tPrice\tQuantity");
                            while (rs.next()) {
                                System.out.println(rs.getInt("ProductID") + "\t" +
                                        rs.getString("ProductName") + "\t" +
                                        rs.getDouble("Price") + "\t" +
                                        rs.getInt("Quantity"));
                            }
                        }
                        break;

                    case 3:
                        System.out.print("ProductID: ");
                        int updateId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("New Name: ");
                        String newName = sc.nextLine();
                        System.out.print("New Price: ");
                        double newPrice = sc.nextDouble();
                        System.out.print("New Quantity: ");
                        int newQty = sc.nextInt();

                        try (PreparedStatement ps = conn.prepareStatement(
                                "UPDATE Product SET ProductName=?, Price=?, Quantity=? WHERE ProductID=?")) {
                            ps.setString(1, newName);
                            ps.setDouble(2, newPrice);
                            ps.setInt(3, newQty);
                            ps.setInt(4, updateId);
                            int rows = ps.executeUpdate();
                            conn.commit();
                            System.out.println(rows + " updated.");
                        } catch (SQLException e) {
                            conn.rollback();
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 4:
                        System.out.print("ProductID: ");
                        int deleteId = sc.nextInt();

                        try (PreparedStatement ps = conn.prepareStatement(
                                "DELETE FROM Product WHERE ProductID=?")) {
                            ps.setInt(1, deleteId);
                            int rows = ps.executeUpdate();
                            conn.commit();
                            System.out.println(rows + " deleted.");
                        } catch (SQLException e) {
                            conn.rollback();
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 5:
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
} 
