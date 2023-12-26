package CAMSv2;
/**
 *  The {@code StaffView} Class represents the view for staff members in the CAMSv2 system, providing methods to display menus and prompts.
 */

public class StaffView extends View{
    /**
     * Displays the main menu options for staff members.
     */

    public void displayStaffMenu() {
        System.out.println("1. Change Password"); 
        System.out.println("2. View camps");
        System.out.println("3. Create a camp");
        System.out.println("4. Select a camp"); 
        System.out.println("5. Generate attendance report of all my camps");
        System.out.println("6. Generate performance report of all my camps");
        System.out.println("7. Generate enquiries report of all my camps");
        System.out.println("8. View Profile");                
    }
    /**
     * Displays the submenu options for staff members who have created the selected camp.
     */
    public void displayViewMyCampsMenu() {
        displayViewOtherCampsMenu();
        System.out.println("2. Edit camp"); 
        System.out.println("3. Delete camp"); 
        System.out.println("4. Manage camp Enquiries"); 
        System.out.println("5. Manage camp Suggestions"); 
    }
    /**
     * Displays a prompt to get the reply for a camp enquiry.
     */
    public void displayGetReply() {
        System.out.println("Please enter your reply: ");
    }
    /**
     * Displays a prompt to get the ID of a camp enquiry.
     */
    public void displayGetEnquiryId() {
        System.out.println("Please enter the enquiry ID: ");
    }

    /**
     * Displays a prompt to get the ID of a camp suggestion.
     */
    public void displayGetSuggestionId() {
        System.out.println("Please enter the Suggestion ID: ");
    }
    /**
     * Displays the submenu options for staff members who have not created the selected camp.
     */
    public void displayViewOtherCampsMenu() {
        System.out.println("1. View Camp Details"); 
    }
    /**
     * Displays the menu options for managing camp enquiries.
     */
    public void displayCampEnquiriesMenu() {
        System.out.println("1. View Camp Enquiries");
        System.out.println("2. Reply to an Enquiry"); 
    }
    /**
     * Displays the menu options for managing camp suggestions.
     */

    public void displayCampSuggestionsMenu() {
        System.out.println("1. View Camp Suggestions");
        System.out.println("2. Approve Suggestion");
    }
    /**
     * Displays the menu options for selecting a report filter.
     */
    public void displayReportFilter() {
        System.out.println("1. Attendee");
        System.out.println("2. Camp Committee Member");       
        System.out.println("3. All");     
    }

}
