package CAMSv2;

/**
 * The {@code StaffAuthenticator} class implements the Authenticator interface for Staff objects.
 * It provides a method to authenticate a staff member based on their email ID and password.
 * The authentication process involves checking the provided credentials against the staff database.
 * If the credentials match an existing staff member, the method returns that staff member.
 * If the credentials do not match any staff member, the method returns null.
*@author Benjamin Fernandez
*@since 13-11-2023
 */
public class StaffAuthenticator implements IAuthenticator<Staff>{

    @Override
    public Staff authenticate(String emailID, String password) {
        for (int i = 0; i <StaffDataBase.getInstance().getStaffList().size(); i++) {
            Staff staff = StaffDataBase.getInstance().getStaffList().get(i);
            if (staff.getEmailID().equals(emailID) && staff.getPassword().equals(password)) {
                System.out.println("Successfully Authenticated");
                return staff;
            }
        }
        System.out.println("Wrong Credentials");
        return null;
    }
    
}
