package CAMSv2;

/**
 * The {@code Controller} class for managing interactions between the Staff user and the system.
 * Handles staff-specific actions such as changing passwords, creating and managing camps,
 * generating reports, and interacting with camp-related functionalities.
 */
public class StaffController extends BaseController<Staff, StaffView>{
    Camp camp;
    /**
     * Constructor for the StaffController class.
     *
     * @param user The staff user associated with the controller.
     * @param view The view associated with the controller for user interface interactions.
     */
    public StaffController(Staff user, StaffView view) {
        super(user, view);
    }
    /**
     * Starts the staff user program, allowing them to perform various actions in the system.
     * Displays the staff menu and handles user choices.
     */
    @Override
    public void startProgram() {
        if (user.getFirstLogin()) {
            System.out.println("Please change to a new password!");
            user.setFirstLogin();
        }
        boolean running = true;
        while (running) {
            view.displayHeader("STAFF MENU");
            view.displayStaffMenu();
            view.displayReturnToPreviousPage();
            try {
                int choice = sc.nextInt();
                // get rid of buffered carriage return
                sc.nextLine();
                running = handleStaffMenu(choice);                
            } catch (Exception e) {
                sc.nextLine();
                view.displaySelectValidOption();
            }
        }
    }
    /**
     * Handles the user's choice from the staff menu and executes the corresponding action.
     *
     * @param choice The user's selected option.
     * @return True if the program should continue running; false if the user wants to exit.
     */
    private boolean handleStaffMenu(int choice) {
        switch (choice) {
            case 1:
                // Change Password
                return enterChangePassword();
            case 2:
                // View camps
                return enterViewCamps();
            case 3:
                // Create Camp
                return enterCreateCamp();
            case 4:
                // Select a camp
                return enterCampSelection();
            case 5:
                // Generate Attendance Report
                return enterGenerateAttendanceReport();
            case 6:
                // Display Performance report
                return enterGeneratePerformanceReport();
            case 7:
                return enterGenerateEnquiriesReport();
            case 8:
                return enterDisplayProfile();
            case 111:
                return false;
            default:
                view.displaySelectValidOption();
                return true;
        }
    }
    private boolean enterDisplayProfile() {
        view.displayHeader("PROFILE");
        user.displayProfile();
        return true;
    }
    /**
     * Executes the action to generate the student enquiries report.
     *
     * @return True to continue program execution.
     */
    private boolean enterGenerateEnquiriesReport() {
        view.displayHeader("GENERATE STUDENT ENQUIRIES REPORT");
        user.generateStudentsEnquiryReport();
        return true;
    }

    /**
     * Initiates the process of generating the attendance report based on user-selected filters.
     *
     * @return True to continue program execution.
     */
    protected boolean enterGenerateAttendanceReport() {
        boolean running = true;
        while(running) {        
            view.displayHeader("GENERATE ATTENDANCE REPORT");
            view.displayReportFilter();
            view.displaySelectActionToTake();
            view.displayReturnToPreviousPage();
            try {
                int choice = sc.nextInt();
                sc.nextLine();
                running = handleGenerateAttendanceReport(choice);                
            } catch (Exception e) {
                sc.nextLine();
                view.displaySelectValidOption();
            }
        }
        return true;
    }

    /**
     * Handles the user's choice in generating the attendance report based on the selected filter.
     *
     * @param choice The user's selected filter option.
     * @return True to continue program execution.
     */
    protected boolean handleGenerateAttendanceReport(int choice) {
        IReportFilter filter;
        switch (choice) {
            case 1:
                filter = new AttendeeFilter();
                break;
            case 2:
                filter = new CCMFilter();
                break;
            case 3:
                filter = new AllFilter();
                break;
            case 111:
                return false;
            default:
                return true;
        }
        // generate report
        user.generateStaffReport(filter);
        return false;

    }
    /**
     * Initiates the process of generating the performance report for the selected camp.
     *
     * @return True to continue program execution.
     */
    protected boolean enterGeneratePerformanceReport() {
        view.displayHeader("GENERATE PERFORMANCE REPORT");
        // generate report
        user.generateCampCommitteeReport();
        return true;
    }


    /**
     * Initiates the process of creating a new camp.
     *
     * @return True to continue program execution.
     */
    protected boolean enterCreateCamp() {
        view.displayHeader("CREATE CAMP");
        CampManager.getInstance().createCamp(user);
        return true;
    }
    /**
     * Initiates the process of viewing camps in the system.
     *
     * @return True to continue program execution.
     */
    protected boolean enterViewCamps() {
        view.displayHeader("CAMPS");        
        view.displayListOfCamps(CampManager.getInstance().getCampList());
        return true;
    }
    /**
     * Initiates the process of changing the user's password.
     *
     * @return True to continue program execution.
     */
    protected boolean enterChangePassword() {
        view.displayHeader("CHANGE PASSWORD");
        System.out.println("Enter new password");
        String newPassword = sc.nextLine();
        user.changePassword(newPassword); 
        return true;       
    }
    /**
     * Initiates the process of selecting a camp for further actions.
     *
     * @return True to continue program execution.
     */
    protected boolean enterCampSelection() {
        view.displayHeader("CAMP SELECTION");
        // select camp
        view.displayListOfCamps(CampManager.getInstance().getCampList());
        view.displayEnterCampName();

        // enter camp name
        String campName = sc.nextLine();

        Camp camp = CampManager.getInstance().getCamp(campName);
        if (camp == null) {
            System.out.println("Unable to find " + campName);
            return true;
        }
        System.out.println("You chosed Camp: " + camp.getCampName());
        this.camp = camp;

        boolean isInCreatedCamps = user.getCreatedCamps().contains(camp);
        boolean running = true;
        while (running) {
            view.displayHeader("CAMP MENU");
            if (isInCreatedCamps) {
                view.displayViewMyCampsMenu();
            }
            else {
                view.displayViewOtherCampsMenu();
            }
            view.displayReturnToPreviousPage();
            try {
                int choice = sc.nextInt();
                sc.nextLine();

                if (isInCreatedCamps) {
                    running = handleCampSelectionOfMyCamp(choice);                
                }
                else {
                    running = handleCampSelectionOfOtherCamps(choice);                   
                }                
            } catch (Exception e) {
                sc.nextLine();
                view.displaySelectValidOption();
            }
        }
        return true;
    }
    /**
     * Handles the user's choice when selecting other camps (not created by the staff).
     *
     * @param choice The user's selected option.
     * @return True to continue program execution.
     */
    protected boolean handleCampSelectionOfOtherCamps(int choice) {
        switch (choice) {
            case 1:
                return enterDisplayListOfCamps();
            case 111:
                return false;
            default:
                return true;
        }
    }
    /**
     * Displays information about the selected camp.
     *
     * @return True to continue program execution.
     */
    protected boolean enterDisplayListOfCamps() {
        view.displayHeader("CAMP INFORMATION");
        camp.printCampInfoAndList();
        // print the student list as well

        return true;
    }
    /**
     * Handles the user's choice when selecting a camp created by the staff.
     *
     * @param choice The user's selected option.
     * @return True to continue program execution.
     */
    protected boolean handleCampSelectionOfMyCamp(int choice) {
        switch (choice) {
            case 1:
                return enterDisplayListOfCamps();
            case 2:
                return enterEditCamp();
            case 3:
                return enterDeleteCamp();
            case 4:
                return enterManageEnquiries();
            case 5:
                return enterManageSuggestions();
            case 111:
                return false;
            default:
                return true;
        }
    }

    /**
     * Initiates the process of managing suggestions for the selected camp.
     *
     * @return True to continue program execution.
     */
    protected boolean enterManageSuggestions() {
        boolean running = true;
        while(running) {
            view.displayHeader("Suggestions Menu");
            view.displayCampSuggestionsMenu();
            view.displayReturnToPreviousPage();
            // view.displaySelectActionToTake();
            try {
                int choice = sc.nextInt();
                sc.nextLine();
                running = handleManageSuggestions(choice);                
            } catch (Exception e) {
                sc.nextLine();
                view.displaySelectValidOption();
            }
        }
        return true;
    }
    /**
     * Handles the user's choice when managing suggestions for the selected camp.
     *
     * @param choice The user's selected option.
     * @return True to continue program execution.
     */
    private boolean handleManageSuggestions(int choice) {
        switch (choice) {
            case 1:
                
                return enterViewCampSuggestions();

            case 2:
                return enterApproveSuggestion();

            case 111:
                return false;
        
            default:
                return true;
        }
    }
    /**
     * Displays a list of suggestions for the selected camp.
     *
     * @return True to continue program execution.
     */
    protected boolean enterViewCampSuggestions() {
        view.displayHeader("CAMP SUGGESTIONS LIST");
        camp.printSuggestionList();
        return true;
    }
    /**
     * Initiates the process of approving a suggestion for the selected camp.
     *
     * @return True to continue program execution.
     */

    protected boolean enterApproveSuggestion() {
        enterViewCampSuggestions();
        view.displayGetSuggestionId();
        try {
            int id = sc.nextInt();
            sc.nextLine();

            return user.approveAdvice(camp, id);            
        } catch (Exception e) {
            sc.nextLine();
            view.displaySelectValidOption();
            return true;
        }
    }
    /**
     * Initiates the process of replying to enquiries for the selected camp.
     *
     * @return True to continue program execution.
     */
    protected boolean enterReplyCampEnquiries() {
        camp.printEnquiriesList();
        view.displayGetEnquiryId();
        try {
            int id = sc.nextInt();
            sc.nextLine();
            Question question = camp.getEnquiryFromCamp(id);
            if (question == null) {
                System.out.println("Please provide a valid EnquiryId!");
                return true;
            }
            view.displayGetReply();
            String reply = sc.nextLine();
            question.setReply(new Reply(user.getName(), reply));
            camp.printEnquiriesList();

            System.out.println("Successfully sent reply!");
            return true;
                      
        } catch (Exception e) {
            sc.nextLine();
            System.out.println("Please provide a valid EnquiryId!");
            return true;

        }

    }

    /**
     * Displays a list of enquiries for the selected camp.
     *
     * @return True to continue program execution.
     */
    protected boolean enterViewCampEnquiries() {
        view.displayHeader("CAMP ENQUIRIES LIST");
        camp.printEnquiriesList();
        return true;
    }
    /**
     * Initiates the process of managing enquiries for the selected camp.
     *
     * @return True to continue program execution.
     */
    protected boolean enterManageEnquiries() {
        boolean running = true;
        while(running) {
            view.displayHeader("Enquiries Menu");
            view.displayCampEnquiriesMenu();
            view.displayReturnToPreviousPage();
            // view.displaySelectActionToTake();
            try {
                int choice = sc.nextInt();
                sc.nextLine();
                running = handleManageEnquiries(choice);
            } catch (Exception e) {
                sc.nextLine();
                view.displaySelectValidOption();
            }
        }
        return true;
    }
    /**
     * Handles the user's choice when managing enquiries for the selected camp.
     *
     * @param choice The user's selected option.
     * @return True to continue program execution.
     */
    protected boolean handleManageEnquiries(int choice) {
        switch (choice) {
            case 1:
                
                return enterViewCampEnquiries();

            case 2:
                return enterReplyCampEnquiries();

            case 111:
                return false;
        
            default:
                return true;
        }
    }
    /**
     * Initiates the process of deleting the selected camp.
     *
     * @return True to continue program execution.
     */
    protected boolean enterDeleteCamp() {
        CampManager.getInstance().deleteCamp(camp);
        view.displaySuccessfulMessage();
        return false;
    }
    /**
     * Initiates the process of editing the details of the selected camp.
     *
     * @return True to continue program execution.
     */
    protected boolean enterEditCamp() {
        CampManager.getInstance().editCamp(camp.getCampName(), user.getName());    
        return true;
    }


    
}
