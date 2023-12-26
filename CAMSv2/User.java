package CAMSv2;

 
/**

 * The {@code User} User class represents a user in the CAMSv2 system.
 * Each user has an email ID, password, faculty, name, and role.
 * The role can be either staff, student, or camp committee member.
 * This class provides methods for creating and managing users.
 * @author Zhu YuHao
 * @since 13-11-2023
 */
public class User {
    //attributes
    protected boolean firstLogin = true;
    protected String emailID;
    protected String password;
    protected UserGroup faculty;
    protected String name;
    protected Role role; //either staff or student or camp committee member
    
    /**
     * Constructor for the User class.
     *
     * @param name The name of the user.
     * @param emailID The email ID of the user.
     * @param faculty The faculty of the user.
     * @param password The password of the user.
     * @param role The role of the user.
     */
    //methods
    public User(String name, String emailID, UserGroup faculty, String password, Role role){
        this.emailID = emailID; 
        this.password = password;
        this.faculty = faculty;
        this.name = name;
        this.role = role;
    }

    
     /**
     * This method changes the user's password.
     * It sets the user's password to the entered password.
     */
    public void changePassword(String newPassword){
        this.password = newPassword;
        System.out.println("The new password will take effect in your next login");
    }

    /**
     * This method returns the user's name.
     *
     * @return The user's name.
     */
    public String getName(){
        return this.name;
    }


     /**
     * This method sets the firstLogin boolean value to false by default
     *  
     */
    public void setFirstLogin() {
        firstLogin = false;
    }
     /**
     * This method sets the firstLogin boolean value
     *
     * @param firstLogin 
     */
    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    /**
     * This method checks if this is the first login for this account.
     *
     * @return firstLogin boolean
     */
    public boolean getFirstLogin() {
        return firstLogin;
    }

     /**
     * This method returns the user's faculty.
     *
     * @return The user's faculty.
     */
    public UserGroup getFaculty(){
        return this.faculty;
    }

     /**
     * This method returns the user's password.
     *
     * @return The user's password.
     */
    public String getPassword(){
        return this.password;
    }

     /**
     * This method returns the user's email ID.
     *
     * @return The user's email ID.
     */
    public String getEmailID(){
        return this.emailID;
    }

     /**
    * This method returns the user's role.
    *
    * @return The user's role.
    */
    public Role getRole(){
        return this.role;
    }
    /**
     * Displays the profile information of the user.
     * Prints the user's name, password, faculty, and role to the console.
     */
    public void displayProfile() {
        System.out.println("Name: " + name);
        System.out.println("Password: " + password); 
        System.out.println("Faculty: " + faculty.toString()); 
        System.out.println("Role: " + role.toString());  
    }
}
