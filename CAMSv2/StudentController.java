package CAMSv2;

import java.util.ArrayList;

/**
 * Student Controller class
 */

 /** 
 * This {@code StudentController} class represents the controller for the Student model.
 * It extends the BaseController class and includes additional properties and methods specific to a Student.
 * 
 * @author Zhu Yu Hao
 * @since 13-11-2023
 */

public class StudentController extends BaseController<Student, StudentView>{
    Camp camp = null;

     /**
     * Constructor for the StudentController class.
     * It initializes the student and the view.
     *
     * @param student The student to be controlled.
     * @param view The view to be used.
     */
    StudentController(Student student, StudentView view) {
        super(student, view);
    }

     /**
     * This method starts the program for the student.
     * It displays the student menu and handles the student's choices.
     */
    public void startProgram() {
        if (user.getFirstLogin()) {
            System.out.println("Please change to a new password!");
            user.setFirstLogin();
        }
        boolean running = true;
        while (running) {
            view.displayHeader("STUDENT MENU");
            view.displayStudentMenu();
            view.displayReturnToPreviousPage();
            try {
                int choice = sc.nextInt();
                // get rid of buffered carriage return
                sc.nextLine();
                running = handleStudentMenu(choice);                
            } catch (Exception e) {
                sc.nextLine();
                view.displaySelectValidOption();
            }
        }
    }

     /**
     * This method handles the student's menu choices.
     *
     * @param choice The student's menu choice.
     * @return true if the program should continue running, false otherwise.
     */
    protected boolean handleStudentMenu(int choice) {
        switch (choice) {
            case 1:
                return enterChangePassword();
            case 2:
                return enterDisplayListOfCampsAvailable();
            case 3:
                return enterDisplayRegisteredCampAndRole(user.getRegisteredCamps(), null);
            case 4:
                return enterDisplayProfile();
            case 5:
                return enterEnquiriesMenu();
            case 6:
                return enterCampSpecificOptions();
            case 111:
                return false; // Exit the loop
            default:
                return true; // Invalid choice, continue loop
        }
    }
    protected boolean enterChangePassword() {
        view.displayHeader("Change Password");
        System.out.println("Enter new password");
        String newPassword = sc.nextLine();
        user.changePassword(newPassword);
        return true;
    }

    protected boolean enterDisplayListOfCampsAvailable() {
        view.displayHeader("LIST OF CAMPS AVAILABLE");
        view.displayListOfCamps(CampManager.getInstance().getCampListByFacultyAndVisibility(user.getFaculty()));
        return true;
    }

    protected boolean enterDisplayRegisteredCampAndRole(ArrayList<Camp> camps, Camp ccmCamp) {
        view.displayHeader("REGISTERED CAMPS");
        view.displayRegisteredCampsAndRole(user.getRegisteredCamps(), ccmCamp);
        return true;
    }

    protected boolean enterDisplayProfile() {
        view.displayHeader("PROFILE");
        user.displayProfile();
        return true;
    }

    protected boolean enterEnquiriesMenu() {
        boolean goToLoginPage = false;
        boolean running = true;
        do {
            view.displayHeader("ENQUIRIES MENU");    
            view.displayEnquiriesMenu();
            view.displayReturnToPreviousPage();
            try {
                int choice = sc.nextInt();
                // get rid of carriage
                sc.nextLine();
                running = handleEnquiriesMenu(choice, goToLoginPage);                
            } catch (Exception e) {
                sc.nextLine();
                view.displaySelectValidOption();
            }
        } while (running);
        return !goToLoginPage;
    }

    protected boolean handleEnquiriesMenu(int choice, boolean goToLoginPage) {

        switch (choice) {
            case 1:
                // view enquiries
                return enterDisplayEnquiries();
            case 2:
                // edit enquiry
                return enterEditEnquiry();
            case 3:
                // delete enquiry
                return enterDeleteEnquiry();
            case 111:
                return false;
        }
        return true;
    }

    protected boolean enterDisplayEnquiries() {
        view.displayHeader("DISPLAY ENQUIRIES");
        view.displayEnquiries(user.getEnquiries().getQuestions());
        return true;
    }

    /**
     * Display message to get enquiryId and get input
     * @return Enquiry
     */
    protected Question enterGetEnquiry() {
        view.displayGetEnquiryId();
        try {
            int Id = sc.nextInt();
            sc.nextLine();
            Question question = user.getEnquiryById(Id);
            return question;            
        } catch (Exception e) {
            sc.nextInt();
            System.out.println("Please provide a valid EnquiryId!");
            return null;
        }        
    }

    /**
     * This method handles the student's choice to view the remaining time slots for a camp.
     */

    protected boolean enterEditEnquiry() {
        enterDisplayEnquiries();
        view.displayHeader("EDIT ENQUIRY");
        Question question = enterGetEnquiry();
        if (question == null) {
            view.displayFailureMessage();
            return true;
        }

        view.displayEnterNewEnquiryDescription();
        String description = sc.nextLine();

        user.editEnquiry(question, description);
        return true;
    }

     /**
     * This method handles the student's choice to delete an enquiry.
     * It prompts the student for the enquiry ID and then deletes the enquiry.
     */

    protected boolean enterDeleteEnquiry() {
        enterDisplayEnquiries();
        view.displayHeader("DELETE ENQUIRY");
        Question question = enterGetEnquiry();
        String campName = question.getCampName();
        Camp camp = CampManager.getInstance().getCamp(campName);
        user.deleteEnquiry(question.getQuestionId(), camp);
        return true;
    }

    /**
     * This method handles the student's selection of a camp.
     * It prompts the student for the camp name and sets the selected camp.
     *
     * @return true if the camp was successfully selected, false otherwise.
     */
    protected Camp handleCampSelection() {
        view.displayHeader("CAMP SELECTION");
        // select camp
        view.displayListOfCamps(CampManager.getInstance().getCampListByFacultyAndVisibility(user.getFaculty()));
        view.displayEnterCampName();

        // enter camp name
        String campName = sc.nextLine();

        Camp camp = user.ifCampNameInAvailableListOfCamps(campName);
        if (camp == null) {return null;}
        System.out.println("You chosed Camp: " + camp.getCampName());
        return camp;
    }

    /**
     * This method handles the student's selection of camp-specific options.
     * It displays the camp-specific menu and handles the student's choices.
     *
     * @return true if the program should continue running, false otherwise.
     */

    protected boolean enterCampSpecificOptions() {
        this.camp = handleCampSelection();
        if (this.camp == null) {return true;}
        GoToMainMenu goToMainMenu = new GoToMainMenu();
        boolean running = true;
        do {
            boolean studentRegistered = camp.isStudentRegistered(user.getName());
            // System.out.println("student registered: " + studentRegistered);
            view.displayHeader("CAMP MENU");
            view.displayCampSpecificOptions(studentRegistered);
            view.displayReturnToPreviousPage();

            try {
                int choice = sc.nextInt();
                // clear buffer
                sc.nextLine();           
                running = handleCampSpecificOptions(choice, studentRegistered, goToMainMenu);     
            } catch (Exception e) {
                sc.nextLine();
                view.displaySelectValidOption();
            }
            // System.out.println("running " + running);
            // System.out.println("goTologinPageReturnVal " + goToMainMenu);
        } while (running);
        return !goToMainMenu.getBooleanValue();
    }

     /**
     * This method handles the student's choice to submit an enquiry to a camp.
     * It prompts the student for the enquiry description and then creates the enquiry.
     */
    protected boolean handleSubmitEnquiryToCamp() {
        // Submit Enquiry to camp
        view.displayEnterNewEnquiryDescription();
        String description = sc.nextLine();
        user.createEnquiry(description, this.camp);
        view.displayHeader("DISPLAY ENQUIRIES");
        camp.printEnquiriesList();
        return true;
    }

    /**
     * This method handles the student's choice to view the remaining time slots for a camp.
     */
    protected boolean handleViewRemainingTimeSlots() {
        view.displayHeader("REMAINING CAMP SLOTS");
        view.displayRemainingCampSlots(this.camp);   
        return true;     
    }

    protected boolean handleCampSpecificOptions(int choice, boolean studentRegistered, GoToMainMenu goToLoginPage) {
        switch (choice) {
            case 1:
                return handleSubmitEnquiryToCamp();
            case 2:
                if (studentRegistered) {
                    // withdraw student
                    return enterCampWithdrawal();
                }
                else {
                    // register Student
                    // If he chose CCM, auto logs out
                    return enterCampRegister(goToLoginPage);
                }

            case 3:
                return handleViewRemainingTimeSlots();

            case 111:
                // reset controller camp state
                this.camp = null;
                return false;
        }
        return true;
    }

     /**
     * This method handles the student's choice to withdraw from a camp.
     */
    protected boolean enterCampWithdrawal() {
        user.withdrawFromCamp(this.camp);
        view.displayWithdrawalFromCamp(this.camp);
        return true;
    }

     /**
     * This method handles the student's choice to enter the camp registration process.
     * @param goToMainMenu this is a object that is being passed to tell the program to exit back to main menu.
     * @return true if the registration was successful, false otherwise.
     */
    protected boolean enterCampRegister(GoToMainMenu goToMainMenu) {
        // check if camp is full
        if (!user.canRegisterCamp(this.camp)) {return true;}
        Role role;
        while (true) {
            view.displayHeader("SELECT ROLES");
            view.displayRoleToRegister();
            String input = sc.nextLine();
            try {
                role = Role.valueOf(input);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Your input does not match any roles!");
            }
        }
        // if registered as CCM, return false
        boolean result = user.registerCampRole(role, this.camp);
        // System.out.println("Result: " + result);
        // set goToLoginPage as true
        goToMainMenu.setBooleanValue(!result);
        // System.out.println("goToLoginPage: " + goToMainMenu.getBooleanValue());
        return result;
    }
}


