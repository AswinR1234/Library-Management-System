import java.sql.*;
import java.util.Scanner;

public class Student {

    Scanner sc = new Scanner(System.in);

    // Add Student
    public void addStudent() {

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Department: ");
            String department = sc.nextLine();

            String sql = "INSERT INTO students(student_name, department) VALUES(?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, department);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student Added Successfully!");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Students
    public void viewStudents() {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM students";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n===== STUDENT LIST =====");

            while (rs.next()) {

                System.out.println("----------------------------");
                System.out.println("Student ID : " + rs.getInt("student_id"));
                System.out.println("Name       : " + rs.getString("student_name"));
                System.out.println("Department : " + rs.getString("department"));
                System.out.println("Phone      : " + rs.getString("phone"));
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}