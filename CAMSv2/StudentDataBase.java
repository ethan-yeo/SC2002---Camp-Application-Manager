package CAMSv2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * This {@code StudentDataBase} class implements the Authenticator interface for Student objects.
 * It provides a method to authenticate a student based on their user ID and password.
 * 
 * @author Zhu Yu Hao 
 * @since 13-11-2023
 */

public class StudentDataBase extends DataBase{
    private static StudentDataBase instance;
    private ArrayList<Student> studentList = new ArrayList<>();
    private String filePath = System.getProperty("user.dir") + "\\CAMSv2\\Data CSV\\Student_List.csv";

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private StudentDataBase() {
    }

    /**
     * This method returns an instance of the StudentDataBase class.
     * If an instance does not already exist, it is created.
     *
     * @return An instance of the StudentDataBase class.
     */

    public static StudentDataBase getInstance() {
        if (instance == null) {
            instance = new StudentDataBase();
        }
        return instance;
    }

     /**
     * This method loads the student data from a CSV file.
     * It clears the current student list and then reads the student data from the file.
     */
    public void loadToCSV() {
        studentList.clear();
        try (InputStream inputStream = new FileInputStream(this.filePath);
             Reader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
            reader.mark(1);
            String line;

            if (reader.read() != 0xFEFF) {
                reader.reset(); // Reset if the first character is not the BOM
            }

            while ((line = reader.readLine()) != null) {
                if (line.equals("")) {
                    return;
                }
                String[] values = line.split(",");
                String name = values[0].trim();
                String emailID = values[1];
                String faculty = values[2].trim();
                String password = values[3].trim(); // Assume default password

                UserGroup userGroup = UserGroup.NTU;
                // convert faculty to user group
                try {
                    userGroup = UserGroup.valueOf(faculty);
                } catch (Exception e) {
                    System.out.println("Cannot convert UserGroup in CSV into ENUM");
                }

                Student student = new Student(name, emailID, userGroup, password, Role.STUDENT);
                studentList.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method writes the student data to a CSV file.
     * It iterates over the list of students and writes each student's data to the file.
     */

    public void writeToCSV() {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(filePath, false))) {
            for (Student student : studentList) {
                printWriter.println(student.getName() + "," + student.getEmailID() + "," + student.getFaculty() + "," + student.getPassword());
            }
        } catch (IOException e) {
            System.out.println("CSV file not found");
        }
    }

     /**
     * This method returns the list of students in the database.
     *
     * @return An ArrayList of Student objects representing the students in the database.
     */
    public ArrayList<Student> getStudents() {
        return studentList;
    }
}