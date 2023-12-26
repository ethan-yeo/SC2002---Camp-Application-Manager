package CAMSv2;

import java.util.ArrayList;

/**
 * This {@code StudentView} class's objects displays student's view
 * 
 * @author Zhu Yu Hao
 * @since 13-11-2023
 */

public class StudentView extends View{

// --------------------General--------------------
 /**
     * This method displays a header.
     *
     * @param header The header to display.
     */
    public void displayHeader(String header) {
        System.out.println("---------------" + header + "-------------------");        
    }

    /**
     * This method displays a message indicating that the user should select a valid option.
     */
    public void displaySelectValidOption() {
        System.out.println("Select a valid option!");
    }

    /**
     * This method displays a message prompting the user to select an action.
     */
    public void displaySelectActionToTake() {
        System.out.println("Select an option: ");
    }


    /**
     * This method displays the student menu.
     */
    public void displayStudentMenu() {
        System.out.println("Select which action you would like to take");
        System.out.println("1. Change Password"); 
        System.out.println("2. View list of camps available"); 
        System.out.println("3. View registered camps");  
        System.out.println("4. View Profile"); 
        System.out.println("5. Manage own Enquiries (View, Edit and Delete)");
        System.out.println("6. Select a Camp to access more options(submit enquiry, register/withdraw, view available slots)");
    }

    /**
     * This method displays a message indicating that the user can return to the previous page.
     */
    public void displayReturnToPreviousPage() {
        System.out.println("<--- Return to previous page (type 111)");
    }

    /**
     * This method displays a success message.
     */
    public void displaySuccessfulMessage() {
        System.out.println("Successful!");
    }

     /**
     * This method displays a failure message.
     */

    public void displayFailureMessage() {
        System.out.println("Failed!");
    }


// -----------------Enquiries------------------
    /**
     * Displays details of multiple enquiries, including their IDs, descriptions, statuses, and camp names.
     *
     * @param questions The list of Question objects representing the enquiries to be displayed.
     */
    public void displayEnquiries(ArrayList<Question> questions) {
        System.out.println("Enquiry Id" + " | " + "Enquiry" + " | " + "Processed" + " | " + "Camp");
        for (Question question : questions) {
            displayEnquiry(question);
        }
    
    }
    /**
     * Displays details of a specific enquiry, including its ID, description, status, and camp name.
     * Also displays replies to the enquiry if any.
     *
     * @param question The Question object representing the enquiry to be displayed.
     */
    public void displayEnquiry(Question question) {

            System.out.println(question.getQuestionId() + " | " + question.getQuestion() + " | " + question.getStatus() + " | " + question.getCampName());
            for (Reply reply : question.getReplies()) {
                System.out.println(reply.getName() + " Replied: " + reply.getReply());
            }
        
    }
    /**
     * Displays a prompt asking the user to enter the ID of the enquiry.
     */
    public void displayGetEnquiryId() {
        System.out.println("Please enter the enquiry ID: ");
    }

    /**
     * Displays the menu options for managing enquiries.
     */
    public void displayEnquiriesMenu() {
        System.out.println("1. View all enquiries");  
        System.out.println("2. Edit an enquiry"); 
        System.out.println("3. Delete an enquiry");        
    }
    /**
     * Displays a prompt asking the user to enter a new enquiry description.
     */
    public void displayEnterNewEnquiryDescription() {
        System.out.println("Enter new Enquiry Description: ");
    }




// ---------------------Camp Specific-----------------------

    /**
     * Displays options specific to a camp, such as submitting an enquiry, withdrawing or registering, and viewing remaining slots.
     *
     * @param isRegistered True if the student is already registered for the camp, false otherwise.
     */
    public void displayCampSpecificOptions(boolean isRegistered) {
        System.out.println("1. Submit an Enquiry");
        if (isRegistered) {
            System.out.println("2. Withdraw");  
        }
        else {
            System.out.println("2. Register");
        }
        System.out.println("3. View Remaining Camp Slots");
    }



// --------------Register------------------------
    /**
     * Displays available roles for registration.
     */
    public void displayRoleToRegister() {
        for (Role role : Role.values()) {
            if (role.equals(Role.STAFF)) {continue;}
            System.out.println(role.toString());
        }
        System.out.println("Enter the role you would like to register (Full Uppercase): ");
    }
// --------------Withdrawal----------------------
    /**
     * Displays a message indicating successful withdrawal from a specific camp.
     *
     * @param camp The camp from which the withdrawal occurred.
     */
    public void displayWithdrawalFromCamp(Camp camp) {
        System.out.println("Withdrawn from " + camp.getCampName());
    }
// -------------Password-------------------------

    /**
     * Displays a prompt asking the user to enter a new password.
     */
    public void displayEnterNewPassword() {
        System.out.println("Enter your new Password: ");       
    }
    /**
     * Displays a message indicating that the password has been changed successfully.
     */

    public void displayPasswordChangedSuccessfully() {
        System.out.println("Password changed successfully!");         
    }



// -------------------Camp General---------------
    /**
     * Displays the camps a student is registered for, along with their roles (Attendee or Camp Committee Member).
     *
     * @param camps    The list of camps the student is registered for.
     * @param ccmCamp  The camp where the student is a Camp Committee Member, or null if not applicable.
     */

    public void displayRegisteredCampsAndRole(ArrayList<Camp> camps, Camp ccmCamp) {
        int counter = 1;
        for (Camp camp : camps) {
            if (ccmCamp != null && camp.equals(ccmCamp)) {
                System.out.println(counter + ": " + camp.getCampName() + "(" + "Camp Committee Member" + ")");  
            }
            else {
                System.out.println(counter + ": " + camp.getCampName() + "(" + "Attendee" + ")");       
            }
            counter++;     
        }    
    }

// ------------------Profile----------------------
}
