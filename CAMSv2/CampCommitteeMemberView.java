package CAMSv2;


/**
 * The {@code CampCommitteeMemberView} class for Camp Committee Members, managing display and user interaction.
 * Extends functionality from the StudentView class.
 * @author Zhu Yu Hao
 * @since 13-11-2023
 */
public class CampCommitteeMemberView extends StudentView {

    /**
     * Displays specific camp-related options based on user roles.
     * @param isRegistered Indicates if the user is registered for the camp.
     * @param isCCM Indicates if the user is a Camp Committee Member.
     */
    public void displayCampSpecificOptions(boolean isRegistered, boolean isCCM) {
        if (isCCM) {
            System.out.println("1. Submit an Enquiry");
            System.out.println("2. View Remaining Camp Slots");
            System.out.println("3. View Camp Details");
            System.out.println("4. Manage Camp Suggestions"); 
            System.out.println("5. Manage Camp Enquiries");
            System.out.println("6. Generate Camp Attendance Report");
            System.out.println("7. Generate Student Enquiry Report");
        }
        else {
            super.displayCampSpecificOptions(isRegistered);
        }

    }

    /**
     * Displays the menu for camp-related enquiries.
     */
    public void displayCampEnquiriesMenu() {
        System.out.println("1. View Camp Enquiries");
        System.out.println("2. Reply to an Enquiry"); 
    }

    /**
     * Displays the menu for managing camp suggestions.
     */
    public void displaySuggestionsMenu() {
        System.out.println("1. Submit a suggestion"); 
        System.out.println("2. View suggestion"); 
        System.out.println("3. Edit suggestion"); 
        System.out.println("4. Delete suggestion"); 
    }

    /**
     * Displays a prompt to submit a suggestion.
     */
    public void displaySubmitSuggestion() {
        System.out.println("Please enter the suggestion: ");
    }

    /**
     * Displays a prompt to get the index of a suggestion.
     */
    public void displayGetSuggestionIndex() {
        System.out.println("Please enter the suggestion index: ");        
    }

    /**
     * Displays camp details.
     * @param camp The Camp object containing camp details.
     */
    public void displayCampDetails(Camp camp){

        System.out.println("Camp name: " + camp.getCampName() + '\n' +
                "Camp date: " + camp.getDates()    + '\n' +
                "Camp Registration Closing Date: " + camp.getRegistrationClosingDate() + '\n' +
                "Camp User Group: " + camp.getUserGroup() +'\n' +
                "Location: "+ camp.getLocation() +'\n' +
                "Total slots: " + camp.getTotalSlots() + '\n'+
                "Description" + camp.getDescription() + '\n'
        );
        System.out.println("Camp Committee Slot: " + camp.getCampCommitteeSlots().size());
        System.out.println("Camp Committee Members: ");
        int counter = 1;
        for (Student student : camp.getCampCommitteeSlots()) {
            System.out.println( counter + ". " + student.getName());
            counter++;
        }
    }

    /**
     * Displays the options for report filtering.
     * This method prints the available filter options for generating a report,
     * including attendee, camp committee member, and all options.
     */
    public void displayReportFilter() {
        System.out.println("1. Attendee");
        System.out.println("2. Camp Committee Member");       
        System.out.println("3. All");     
    }

    /**
     * Displays a prompt to get a reply.
     */
    public void displayGetReply() {
        System.out.println("Please enter your reply: ");
    }

    /**
     * Displays all advices for a suggestion.
     * @param suggestion The Suggestion object containing advices.
     */
    public void displayAllAdvices(Suggestion suggestion) {
        // int counter = 1;
        System.out.println("Suggestion Id" + " | " + "Suggestion" +  " | " + "Approved");
        for (Advice advice : suggestion.getAdviceList()) {
            System.out.println(advice.getId() + " | " + advice.getAdvice() + " | " + advice.getApproved());
            // counter++;
        }
    }

}
