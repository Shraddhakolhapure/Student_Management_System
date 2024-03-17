package student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
public class Start {

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Student Management System");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(br.readLine());

            switch (choice) {
                case 1:
                    addStudent(br);
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    updateStudent(br);
                    break;
                case 4:
                    deleteStudent(br);
                    break;
                case 5:
                    System.out.println("Exiting Student Management System");
                    br.close(); // Close the BufferedReader before exiting
                    return; // Exit the program
                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    private static void addStudent(BufferedReader br) throws IOException {
        System.out.println("Enter name of Student");
        String name = br.readLine();
        System.out.println("Enter phone number of Student");
        String number = br.readLine();
        System.out.println("Enter city name of Student");
        String city = br.readLine();

        // Create student object
        Student s = new Student(name, number, city);
        boolean ans = StudentDao.insertStudentToDB(s);

        if (ans) {
            System.out.println("Successfully Added");
        } else {
            System.out.println("Something went wrong. Please try again.");
        }
        System.out.println(s);
    }

    
    	private static void viewStudents()  {
    	    // Create a StudentDao object
    	    StudentDao studentDao = new StudentDao();

    	    // Get the list of students from the database
    	    List<Student> studentList = studentDao.getAllStudents();

    	    // Check if any students were found
    	    if (studentList.isEmpty()) {
    	        System.out.println("No students found in the database.");
    	    } else {
    	        // Iterate over the list and print student details
    	        System.out.println("----------------------------------");
    	        System.out.println("List of Students:");
    	        System.out.println("----------------------------------");
    	        for (Student student : studentList) {
    	            System.out.println("ID: " + student.getId());
    	            System.out.println("Name: " + student.getStudentName());
    	            System.out.println("Phone Number: " + student.getStudentPhone());
    	            System.out.println("City: " + student.getStudentCity());
    	            System.out.println("----------------------------------");
    	        }
    	    }
    	}
    
    	private static void updateStudent(BufferedReader br) throws IOException {
    	    // Prompt the user for the student ID
    	    System.out.print("Enter the ID of the student to update: ");
    	    int studentId = Integer.parseInt(br.readLine());

    	    // Create a StudentDao object
    	    StudentDao studentDao = new StudentDao();

    	    // Find the student by ID
    	    Student student = studentDao.getStudentById(studentId);

    	    // Check if the student exists
    	    if (student == null) {
    	        System.out.println("Student with ID " + studentId + " not found.");
    	    } else {
    	        // Display current student details
    	        System.out.println("Current Details:");
    	        System.out.println("ID: " + student.getId());
    	        System.out.println("Name: " + student.getStudentName());
    	        System.out.println("Phone Number: " + student.getStudentPhone());
    	        System.out.println("City: " + student.getStudentCity());

    	        // Prompt for updated details
    	        System.out.print("Enter new name (leave blank to keep current): ");
    	        String newName = br.readLine();
    	        System.out.print("Enter new phone number (leave blank to keep current): ");
    	        String newPhone = br.readLine();
    	        System.out.print("Enter new city (leave blank to keep current): ");
    	        String newCity = br.readLine();

    	        // Update the student object with new values (if provided)
    	        if (!newName.isEmpty()) {
    	            student.setStudentName(newName);
    	        }
    	        if (!newPhone.isEmpty()) {
    	            student.setStudentPhone(newPhone); // Fix the method invocation
    	        }
    	        if (!newCity.isEmpty()) {
    	            student.setStudentCity(newCity);
    	        }

    	        // Update the student in the database using StudentDao
    	        studentDao.updateStudentInDB(student);

    	        System.out.println("Student record updated successfully!");
    	    }
    	}

   
        
    	
    	private static void deleteStudent(BufferedReader br) throws IOException {
    	    // Prompt the user for the student ID
    	    System.out.print("Enter the ID of the student to delete: ");
    	    int studentId = Integer.parseInt(br.readLine());

    	    // Create a StudentDao object
    	    StudentDao studentDao = new StudentDao();

    	    // Find the student by ID
    	    Student student = studentDao.getStudentById(studentId);

    	    // Check if the student exists
    	    if (student == null) {
    	        System.out.println("Student with ID " + studentId + " not found.");
    	    } else {
    	        // Display current student details
    	        System.out.println("Current Details:");
    	        System.out.println("ID: " + student.getId());
    	        System.out.println("Name: " + student.getStudentName());
    	        System.out.println("Phone Number: " + student.getStudentPhone());
    	        System.out.println("City: " + student.getStudentCity());

    	        // Confirm deletion
    	        System.out.print("Are you sure you want to delete this student? (yes/no): ");
    	        String confirmation = br.readLine();

    	        if (confirmation.equalsIgnoreCase("yes")) {
    	            // Delete the student from the database using StudentDao
    	            studentDao.deleteStudentFromDB(studentId);
    	            System.out.println("Student record with ID " + studentId + " deleted successfully!");
    	        } else {
    	            System.out.println("Deletion cancelled.");
    	        }
    	    }
    	}
}
