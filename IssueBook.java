import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class IssueBook {

    Scanner sc = new Scanner(System.in);

    public void issueBook() {

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Enter Student ID: ");
            int studentId = sc.nextInt();

            System.out.print("Enter Book ID: ");
            int bookId = sc.nextInt();

            // Check Book Availability
            String checkBook = "SELECT quantity FROM books WHERE id=?";

            PreparedStatement ps1 = con.prepareStatement(checkBook);
            ps1.setInt(1, bookId);

            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {

                int quantity = rs.getInt("quantity");

                if (quantity > 0) {

                    // Issue Book
                    String issue = "INSERT INTO issued_books(student_id, book_id, issue_date) VALUES(?,?,?)";

                    PreparedStatement ps2 = con.prepareStatement(issue);

                    ps2.setInt(1, studentId);
                    ps2.setInt(2, bookId);
                    ps2.setDate(3, Date.valueOf(LocalDate.now()));

                    ps2.executeUpdate();

                    // Reduce Quantity
                    String update = "UPDATE books SET quantity=quantity-1 WHERE id=?";

                    PreparedStatement ps3 = con.prepareStatement(update);

                    ps3.setInt(1, bookId);

                    ps3.executeUpdate();

                    System.out.println("Book Issued Successfully!");

                } else {

                    System.out.println("Book Out of Stock!");

                }

            } else {

                System.out.println("Book Not Found!");

            }

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    // Return Book
    public void returnBook() {

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Enter Book ID: ");
            int bookId = sc.nextInt();

            String delete = "DELETE FROM issued_books WHERE book_id=? LIMIT 1";

            PreparedStatement ps1 = con.prepareStatement(delete);

            ps1.setInt(1, bookId);

            int rows = ps1.executeUpdate();

            if (rows > 0) {

                String update = "UPDATE books SET quantity=quantity+1 WHERE id=?";

                PreparedStatement ps2 = con.prepareStatement(update);

                ps2.setInt(1, bookId);

                ps2.executeUpdate();

                System.out.println("Book Returned Successfully!");

            } else {

                System.out.println("No Issued Record Found!");

            }

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}