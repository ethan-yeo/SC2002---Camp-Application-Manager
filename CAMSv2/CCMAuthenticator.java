package CAMSv2;

/**
 *The {@code CCMAuthenticator} class implements the Authenticator interface for CCM (Camp Committee Member) objects.
 * It provides a method to authenticate a camp committee member based on their email ID and password.
 * The authentication process involves checking the provided credentials against the CCM database.
 * If the credentials match an existing CCM, the method returns that CCM.
 * If the credentials do not match any CCM, the method returns null.
 * @author Ethan Yeo
 * @since 13-11-2023
 */

public class CCMAuthenticator implements IAuthenticator<CampCommitteeMember>{

    @Override
    public CampCommitteeMember authenticate(String userId, String password) {
        for (CampCommitteeMember campCommitteeMember : CampCommitteeDataBase.getInstance().getCampCommitteeMembersList()) {
            if (campCommitteeMember.getEmailID().equals(userId) && campCommitteeMember.getPassword().equals(password)) {
                System.out.println("Successfully Authenticated");
                return campCommitteeMember;
            }            
        }
        System.out.println("Wrong CampCommitteeMember credentials");
        return null;
    }
}
