package CAMSv2;

import java.util.ArrayList;
/**
 * This {@code View} An abstract class representing a generic user interface view.
 * Provides methods for displaying various messages and information on the console.
 */

public abstract class View {
    /**
     * Displays a header message on the console.
     *
     * @param header The header message to be displayed.
     */
    public void displayHeader(String header) {
        System.out.println("---------------" + header + "-------------------");        
    }
    /**
     * Displays a message indicating that an invalid option was selected.
     */
    public void displaySelectValidOption() {
        System.out.println("Select a valid option");
    }

    /**
     * Displays a message prompting the user to select an option.
     */

    public void displaySelectActionToTake() {
        System.out.println("Select an option: ");
    }
    /**
     * Displays a message indicating how to return to the previous page.
     */
    public void displayReturnToPreviousPage() {
        System.out.println("<--- Return to previous page (type 111)");
    }

    /**
     * Displays a message indicating that the operation was successful.
     */

    public void displaySuccessfulMessage() {
        System.out.println("Successful!");
    }
    /**
     * Displays a message indicating that the operation failed.
     */

    public void displayFailureMessage() {
        System.out.println("Failed!");
    }
    /**
     * Displays a message prompting the user to enter a camp name.
     */
    public void displayEnterCampName() {
        System.out.println("Please enter Camp Name: ");               
    }
    /**
     * Displays an option to view the remaining camp slots.
     */

    public void displayRemainingCampSlots() {
        System.out.println("3. View Remaining Camp Slots");
    }
    /**
     * Displays the remaining camp slots for a specific camp.
     *
     * @param camp The camp for which to display remaining slots.
     */

    public void displayRemainingCampSlots(Camp camp) {
        System.out.println((camp.getTotalSlots() - camp.getStudentList().size()));
        // Camp Manager should check that the camp is Visible and userGroup
    }
    /**
     * Displays a list of camps with their corresponding indices.
     *
     * @param camps The list of camps to be displayed.
     */
    public void displayListOfCamps(ArrayList<Camp> camps) {
        int counter = 1;
        for (Camp camp : camps) {
            System.out.println(counter + ": " + camp.getCampName());       
            counter++;     
        }
    }

}
