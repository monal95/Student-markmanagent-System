import java.util.ArrayList;
import java.util.Scanner;

// Class representing a Student
class Student {
    private String id;
    private String name;
    private int[] marks = new int[5]; // Array to store marks for 5 subjects

    // Constructor
    public Student(String id, String name, int[] marks) {
        this.id = id;
        this.name = name;
        this.marks = marks.clone(); // Clone to ensure immutability
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int[] getMarks() {
        return marks;
    }

    // Setter for marks
    public void setMarks(int[] marks) {
        this.marks = marks.clone(); // Clone to ensure immutability
    }

    // Override toString() to display student details
    @Override
    public String toString() {
        String[] subjects = MarkManagementSystem.getSubjects(); // Get subject names
        StringBuilder marksDisplay = new StringBuilder();
        for (int i = 0; i < marks.length; i++) {
            marksDisplay.append(subjects[i]).append(": ").append(marks[i]).append(", ");
        }
        // Remove trailing comma and space
        if (marksDisplay.length() > 0) {
            marksDisplay.setLength(marksDisplay.length() - 2);
        }
        return "ID: " + id + ", Name: " + name + ", Marks: [" + marksDisplay + "]";
    }
}

// Main application class
public class MarkManagementSystem {
    private static final String[] subjects = {
        "Java",
        "Design Analysis and Algorithm",
        "Data Processing and Visualisation",
        "Machine Learning",
        "DMLA"
    }; // Subject names

    private static ArrayList<Student> studentList = new ArrayList<>(); // List to store students
    private static Scanner scanner = new Scanner(System.in); // Scanner for user input

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = getValidIntegerInput("Choose an option: ");

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAllStudents();
                case 3 -> searchStudentById();
                case 4 -> updateStudentMarks();
                case 5 -> deleteStudentById();
                case 6 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Please choose a valid number from the menu.");
            }
        }
    }

    // Get subject names
    public static String[] getSubjects() {
        return subjects;
    }

    // Display the main menu
    private static void showMenu() {
        System.out.println("\n--- Student Mark Management System ---");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Update Marks");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
    }

    // Add a new student to the system
    private static void addStudent() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        int[] marks = new int[5];
        for (int i = 0; i < subjects.length; i++) {
            marks[i] = getValidIntegerInput("Enter marks for " + subjects[i] + ": ");
        }

        studentList.add(new Student(id, name, marks));
        System.out.println("Student added successfully!");
    }

    // View all students
    private static void viewAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        System.out.println("\n--- Student Records ---");
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    // Search for a student by ID
    private static void searchStudentById() {
        System.out.print("Enter Student ID to search: ");
        String id = scanner.nextLine();

        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                System.out.println("Student found: " + student);
                return;
            }
        }

        System.out.println("No student found with ID: " + id);
    }

    // Update the marks of a specific student
    private static void updateStudentMarks() {
        System.out.print("Enter Student ID to update marks: ");
        String id = scanner.nextLine();

        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                int[] newMarks = new int[5];
                for (int i = 0; i < subjects.length; i++) {
                    newMarks[i] = getValidIntegerInput("Enter new marks for " + subjects[i] + ": ");
                }
                student.setMarks(newMarks);
                System.out.println("Marks updated successfully!");
                return;
            }
        }

        System.out.println("No student found with ID: " + id);
    }

    // Delete a student by ID
    private static void deleteStudentById() {
        System.out.print("Enter Student ID to delete: ");
        String id = scanner.nextLine();

        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                studentList.remove(student);
                System.out.println("Student deleted successfully!");
                return;
            }
        }

        System.out.println("No student found with ID: " + id);
    }

    // Helper method to get valid integer input
    private static int getValidIntegerInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
