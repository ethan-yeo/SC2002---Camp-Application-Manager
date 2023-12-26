package CAMSv2;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The {@code CampManager} class is responsible for managing camps and their related operations.
 * It follows the Singleton pattern to ensure a single instance of the class.
 *
 * @author Zhu Yu Hao
 * @since 2023-11-24
 */

// to become Singleton
public class CampManager {

    // ... (existing code)

    /**
     * Private constructor to prevent external instantiation.
     */
    //attributes
    private static CampManager instance;


    private ArrayList<Camp> campList = new ArrayList<Camp>();
    public Scanner sc = new Scanner(System.in);

    //method
    private CampManager() {};

    /**
     * Retrieves the singleton instance of the CampManager class.
     *
     * @return The singleton instance of CampManager.
     */
    public static CampManager getInstance() {
        if (instance == null) {
            instance = new CampManager();
        }
        return instance;
    }
    /**
     * Enters, checks, and sets the camp name for the specified camp.
     *
     * @param camp The camp for which the name is set.
     */
    private void enterCheckAndSetCampName(Camp camp) {

        boolean running = true;
        while (running) {
            String campName;
            System.out.println("Enter camp name: ");
            campName = sc.nextLine();
            //check if camp already exists
            if(getCamp(campName) != null){
                System.out.println("Camp with this name already exists");
                continue;
            }else if (campName.equals("")) {
                System.out.println("Camp name cannot be blank");
                continue;            
            } else {
                camp.setCampName(campName);   
                break;                     
            }
        }

    }
    /**
     * Creates a new camp with the provided staff as the staff in charge.
     *
     * @param staff The staff in charge of the new camp.
     */
    
    public void createCamp(Staff staff){

        Camp camp = new Camp();
        camp.setStaff_in_charge(staff.getName());
        enterCheckAndSetCampName(camp);
        enterSetCampDates(camp);
        enterSetRegistrationClosingDate(camp);
        enterSetUserGroup(camp);
        enterSetLocation(camp);
        enterSetCampSlots(camp);
        enterSetCampDescription(camp);
        addCamp(camp);
        staff.getCreatedCamps().add(camp);

        System.out.println(camp.getCampName() + " camp created");

    }
    /**
     * Checks if a user group exists based on the provided user group string.
     *
     * @param userGroup The user group to check for existence.
     * @return The UserGroup enum value if it exists, otherwise null.
     */

    public UserGroup checkUserGroupExist(String userGroup){
        //returns null if user group doesnt exist
        // returns type of usergroup if it does exist
        for (UserGroup usergroups : UserGroup.values()) {
            if(userGroup.equals(usergroups.toString())){
                return usergroups;
            }
        }
        return null;
    }
    /**
     * Adds a camp to the camp list.
     *
     * @param camp The camp to be added.
     */

    public void addCamp(Camp camp){
        campList.add(camp);
    }

    /**
     * Retrieves a camp based on its name.
     *
     * @param campName The name of the camp to retrieve.
     * @return The camp with the specified name, or null if not found.
     */

    public Camp getCamp(String campName){
        //for each camp in campList
        ArrayList<Camp> campList = getCampList();
        Camp camp;

        if(campList != null) {
            for (int i = 0; i < campList.size(); i++) {
                camp = campList.get(i);
                if (campName.equals(camp.getCampName())) {
                    return camp;
                }//if
            }//for
        }
        return null;//if it doesnt find a camp
    }
    /**
     * Checks if a staff member is in charge of a specific camp.
     *
     * @param campName The name of the camp.
     * @param Staffname The name of the staff member.
     * @return {@code true} if the staff member is in charge, {@code false} otherwise.
     */

    public boolean getStaffinCharge(String campName, String Staffname){
        String staffIC;
        Camp camp = new Camp();
        camp = getCamp(campName);
        staffIC = camp.getStaffName();

        return staffIC.equals(Staffname);
    }
    /**
     * Enters and sets the camp name after checking for validity.
     *
     * @param camp The camp for which the name is set.
     */
    public void enterSetCampName(Camp camp) {
        System.out.println("Enter new camp name: ");
        String newCampName = sc.nextLine();
        camp.setCampName(newCampName);
    }
    /**
     * Enters and sets the camp dates after validating the input.
     *
     * @param camp The camp for which dates are set.
     */

    public void enterSetCampDates(Camp camp) {
        boolean running = true;
        while(running) {
            System.out.println("Enter start date (YYYY-MM-DD):");
            String startDateString = sc.nextLine();
        
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                LocalDate startDate = LocalDate.parse(startDateString, formatter);
                if (!startDate.isAfter(LocalDate.now())) {
                    System.out.println("Input Date must be in the future");
                    continue;
                }

                System.out.println("Enter camp duration (days):");
                int numOfDays = sc.nextInt();
                sc.nextLine();     

                ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
                for (int i = 0; i < numOfDays; i++) {
                    dates.add(startDate.plusDays(i));
                }
            
                // Printing the dates to verify for debugging
                // for (LocalDate date : dates) {
                //     System.out.println(date.format(formatter));
                // }

                camp.setDates(dates);
                break;
            } catch (Exception e) {
                System.out.println("Please follow the required format!");
                continue;
            }

        }

    }

    /**
     * Enters and sets the registration closing date after validating the input.
     *
     * @param camp The camp for which the registration closing date is set.
     */

    public void enterSetRegistrationClosingDate(Camp camp) {
        //Ensures that the registration closing date is after the local clock and before start of camp
        do{
            System.out.println("Enter registration closing date(YYYY-MM-DD): ");
            LocalDateTime currentDateTime = LocalDateTime.now();
            String userInput = sc.nextLine();
            userInput += " 23:59";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            try {
                LocalDateTime registrationClosingDate = LocalDateTime.parse(userInput, formatter);
                LocalDateTime startDate = camp.getDates().get(0).atStartOfDay();
                currentDateTime = LocalDateTime.now();
                if (registrationClosingDate.isAfter(currentDateTime) && registrationClosingDate.isBefore(startDate)) {
                    camp.setRegistrationClosingDate(registrationClosingDate);
                    break;
                } else {
                    System.out.println("Input date must be in the future and be before the start of camp.");
                }                
            } catch (Exception e) {
                System.out.println("Please follow the date format!");
                continue;
            }
        }while(true);
    }
    /**
     * Edits the details of a camp, such as name, dates, registration closing date, user group, location, slots, and description.
     *
     * @param campName The name of the camp to be edited.
     * @param staffName The name of the staff member editing the camp.
     */

    public void editCamp(String campName,String staffName){
        int choice = 0;
        Camp currentCamp = getCamp(campName);
        String errorMessage = "Staff is not in charge";
        String exitMessage = "Exiting edit";

        if(getStaffinCharge(campName, staffName)) {
            boolean running = true;
            while(running){
                currentCamp.printCampInfoTable();
                System.out.println("Select an option to edit(Eg. 1): ");
                System.out.println("<--- Exit edit menu (type 111)");
                try {
                    choice = sc.nextInt();
                    sc.nextLine();                    
                } catch (Exception e) {
                    sc.nextLine();
                    System.out.println("Please select a valid option!");
                    continue;
                }
                switch(choice){

                        case 1: 
                            enterSetCampName(currentCamp);
                            break;

                            case 2:
                            enterSetCampDates(currentCamp);
                            break;

                        case 3:  
                            enterSetRegistrationClosingDate(currentCamp);
                            break;

                        case 4: enterSetUserGroup(currentCamp);
                            break;

                        case 5:  enterSetLocation(currentCamp);
                            break;

                        case 6:
                            enterSetCampSlots(currentCamp);
                            break;

                        case 7: 
                            enterSetCampDescription(currentCamp);
                            break;
                        case 8: 
                            changeVisibility(campName);
                            break;
                        case 111:
                            running = false;
                }//end switch
            }//end while

            System.out.println(exitMessage);
        }//end if
        else{
            System.out.println(errorMessage);
        }//end else
        return;
    }//end edit camp

    /**
     * Enters and sets the description of the camp.
     *
     * @param currentCamp The camp for which the description is set.
     */
    private void enterSetCampDescription(Camp currentCamp) {
        System.out.println("Enter new description: ");
        String description = sc.nextLine();
        currentCamp.setDescription(description);
        
    }

    /**
     * Enters and sets the total slots of the camp after validating the input.
     *
     * @param currentCamp The camp for which the total slots are set.
     */
    private void enterSetCampSlots(Camp currentCamp) {
        boolean running = true;
        while (running) {
            System.out.println("Enter Camp Slots: ");
            String campSlots = sc.nextLine();
            int length = currentCamp.getStudentList().size();
            try {
                int input = Integer.parseInt(campSlots);
                if (input < length) {
                    System.out.println("Value too low");
                    continue;
                    
                }else {
                    currentCamp.setTotalSlots(input);
                    running = false;                          
                }           
            } catch (Exception e) {
                System.out.println("You did not enter an integer!");
                continue;
            }
                        
        }

    }

    /**
     * Enters and sets the location of the camp.
     *
     * @param currentCamp The camp for which the location is set.
     */
    private void enterSetLocation(Camp currentCamp) {
        boolean running = true;
        while (running) {
            System.out.println("Enter new location: ");
            String location = sc.nextLine();
            if (location.equals("")) {
                System.out.println("Location cannot be blank");
                continue;
            }
            currentCamp.setLocation(location);
            running = false;            
        }    
    }
    /**
     * Enters and sets the user group of the camp after checking for validity.
     *
     * @param camp The camp for which the user group is set.
     */
    private void enterSetUserGroup(Camp camp) {
        boolean running = true;
        while(running) {
            System.out.println("Enter user group: ");

            String userGroup = sc.nextLine();
            userGroup = userGroup.toUpperCase();
            if (userGroup.equals("")) {
                System.out.println("User Group cannot be blank");
                continue;
            }
            //check if its a valid userGroup
            UserGroup validUserGroup = checkUserGroupExist(userGroup);

            //if its not valid user group, break
            if(validUserGroup == null){
                System.out.println("Invalid User Group!");
                continue;
            } else {
                camp.setUserGroup(userGroup);
                running = false;
            }            
        }
    }
    /**
     * Deletes a camp from the camp list.
     *
     * @param camp The camp to be deleted.
     */
    public void deleteCamp(Camp camp){
        campList.remove(camp);
    }
    /**
     * Changes the visibility of a camp based on user input.
     *
     * @param campName The name of the camp to change visibility.
     */

    public void changeVisibility(String campName){
        //ask the user whether true/false
        String settings;
        boolean choice;
        System.out.println("Set visibility to on or off?");
        settings = sc.nextLine();
        if(settings.equals("on"))
            choice = true;
        else
            choice = false;

        Camp camp = getCamp(campName);
        camp.setVisibility(choice);
        //sc.close();
    }
    /**
     * Generates a list of camps for a specific staff member.
     *
     * @param staffName The name of the staff member.
     * @return An ArrayList of camps associated with the staff member.
     */
    public ArrayList<Camp> StaffCampListGenerator(String staffName){
        //hello ethan
        //this function prints staff camp list as well as return the array
        //for each camp in campList
        int numOfCamps;
        int index=1;
        Camp curCamp = new Camp();
        ArrayList<Camp> staffCampList = new ArrayList<>();
        //ArrayList<Camp> campList = new ArrayList<Camp>();
        numOfCamps = campList.size();
        if(numOfCamps==0){
            System.out.println("You have no camps");
        }

        else{
            System.out.println("Your List:");
            for(int i=0;i<numOfCamps;i++){
            curCamp = campList.get(i);

            if(curCamp.getStaffName().equals(staffName)){
                System.out.println(index + ". " + curCamp.getCampName());
                index++;
                staffCampList.add(curCamp);
                }
            }
        }

        return staffCampList;

    }
    /**
     * Gets the list of all camps.
     *
     * @return An ArrayList of all camps.
     */

    public ArrayList<Camp> getCampList(){
        // Should also check for Visiblity and UserGroup
        return campList;
    }

    /**
     * Checks if the input string represents a valid date in the format "yyyy-MM-dd".
     *
     * @param input The string to be checked for a valid date.
     * @return True if the input is a valid date, false otherwise.
     * @throws DateTimeParseException If the input string cannot be parsed as a valid date.
     */
    public boolean isValidDate(String input) {
        // Define a custom date format that you expect
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            // Try to parse the input as a LocalDate using the defined format
            LocalDate date = LocalDate.parse(input, dateFormatter);
            return true; // Input is a valid date
        } catch (DateTimeParseException e) {
            return false; // Input is not a valid date
        }
    }
    /**
     * Retrieves a list of camps filtered by faculty and visibility.
     *
     * @param faculty The user group representing the faculty for filtering.
     * @return An ArrayList of camps that match the specified faculty and have visibility set to true.
     */
    public ArrayList<Camp> getCampListByFacultyAndVisibility(UserGroup faculty) {
        ArrayList<Camp> filteredCamps = new ArrayList<Camp>();
        for (Camp camp : getCampList()) {
            UserGroup campUserGroup = camp.getUserGroup();

            if(camp.visibility){
                if(campUserGroup.equals(faculty) || campUserGroup.equals(UserGroup.NTU)){
                    filteredCamps.add(camp);
                }
            }
        }
        return filteredCamps;
    }
}

