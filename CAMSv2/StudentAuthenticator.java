package CAMSv2;
/**
 * This {@code StudentAuthenticator} class implements the Authenticator interface for Student objects.
 * It provides a method to authenticate a student based on their user ID and password.
 * 
 * @author Zhu Yu Hao 
 * @since 13-11-2023
 */

public class StudentAuthenticator implements IAuthenticator<Student>{

 /**
     * This method authenticates a student based on their user ID and password.
     * It checks if there is a student in the database with the given user ID and password.
     * If such a student is found, a success message is printed and the student is returned.
     * If no such student is found, a failure message is printed and null is returned.
     *
     * @param userId The user ID of the student to authenticate.
     * @param password The password of the student to authenticate.
     * @return The authenticated student if the authentication was successful, null otherwise.
     */
    @Override
    public Student authenticate(String userId, String password) {
        for (Student student : StudentDataBase.getInstance().getStudents()) {
            if (student.getEmailID().equals(userId) && student.getPassword().equals(password)) {
                System.out.println("Successfully Authenticated");
                return student;
            }
        }
        System.out.println("Wrong Student Credentials");
        return null;
    }
    
}
