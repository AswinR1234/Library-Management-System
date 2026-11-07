import java.sql.*;
import java.util.Scanner;

public class Book {

    Scanner sc = new Scanner(System.in);

    // Add Book
    public void addBook() {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Book Title: ");
            String title = sc.nextLine();

            System.out.print("Enter Author Name: ");
            String author = sc.nextLine();

            System.out.print("Enter Quantity: ");
            int quantity = sc.nextInt();

            String sql = "INSERT INTO books(title, author, quantity) VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setInt(3, quantity);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Book Added Successfully!");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Books
    public void viewBooks() {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM books";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n===== BOOK LIST =====");

            while (rs.next()) {

                System.out.println("----------------------------");
                System.out.println("Book ID : " + rs.getInt("id"));
                System.out.println("Title   : " + rs.getString("title"));
                System.out.println("Author  : " + rs.getString("author"));
                System.out.println("Quantity: " + rs.getInt("quantity"));
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Search Book
    public void searchBook() {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Book ID: ");
            int id = sc.nextInt();

            String sql = "SELECT * FROM books WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\nBook Found!");
                System.out.println("Book ID : " + rs.getInt("id"));
                System.out.println("Title   : " + rs.getString("title"));
                System.out.println("Author  : " + rs.getString("author"));
                System.out.println("Quantity: " + rs.getInt("quantity"));

            } else {

                System.out.println("Book Not Found!");

            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update Book
    public void updateBook() {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Book ID to Update: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter New Title: ");
            String title = sc.nextLine();

            System.out.print("Enter New Author: ");
            String author = sc.nextLine();

            System.out.print("Enter New Quantity: ");
            int quantity = sc.nextInt();

            String sql = "UPDATE books SET title=?, author=?, quantity=? WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setInt(3, quantity);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Book Updated Successfully!");
            } else {
                System.out.println("Book Not Found!");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete Book
    public void deleteBook() {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Book ID to Delete: ");
            int id = sc.nextInt();

            String sql = "DELETE FROM books WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Book Deleted Successfully!");
            } else {
                System.out.println("Book Not Found!");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}