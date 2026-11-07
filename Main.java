import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Book book = new Book();
        Student student = new Student();
        IssueBook issue = new IssueBook();

        while (true) {

            System.out.println("\n====================================");
            System.out.println("     LIBRARY MANAGEMENT SYSTEM");
            System.out.println("====================================");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Add Student");
            System.out.println("7. View Students");
            System.out.println("8. Issue Book");
            System.out.println("9. Return Book");
            System.out.println("10. Exit");
            System.out.println("====================================");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    book.addBook();
                    break;

                case 2:
                    book.viewBooks();
                    break;

                case 3:
                    book.searchBook();
                    break;

                case 4:
                    book.updateBook();
                    break;

                case 5:
                    book.deleteBook();
                    break;

                case 6:
                    student.addStudent();
                    break;

                case 7:
                    student.viewStudents();
                    break;

                case 8:
                    issue.issueBook();
                    break;

                case 9:
                    issue.returnBook();
                    break;

                case 10:
                    System.out.println("\nThank you for using the Library Management System!");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}