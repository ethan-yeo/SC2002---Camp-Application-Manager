package CAMSv2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.ArrayList;

/**

 *The {@code CampInformation} class represents information about a camp, including its details and committee members.
 * @author Zhu Yu Hao
 * @since 13-11-2023
 */
public class CampInformation {

    private String campName = "";
    private ArrayList<LocalDate> dates = new ArrayList<LocalDate>(); 
    private LocalDateTime registrationClosingDate = LocalDateTime.now();
    private UserGroup userGroup = UserGroup.NTU;
    private String location = "";
    private int totalSlots = 1;

    private HashSet<CampCommitteeMember> campCommitteeSlots = new HashSet<CampCommitteeMember>();
    private String description = "";
    private String staffInCharge = "";

    /**
     * Initialization Constructor for CampInformation.
     * @param campName The name of the camp.
     * @param dates The dates of the camp.
     * @param registrationClosingDate The closing date for camp registration.
     * @param userGroup The user group associated with the camp.
     * @param location The location of the camp.
     * @param totalSlots The total number of slots available in the camp.
     * @param description The description of the camp.
     * @param staffInCharge The staff in charge of the camp.
     */
    public CampInformation(String campName , ArrayList<LocalDate> dates , LocalDateTime registrationClosingDate, UserGroup userGroup , String location , int totalSlots, String description, String staffInCharge){

        this.campName = campName;
        this.dates = dates;
        this.registrationClosingDate = registrationClosingDate;
        this.userGroup = userGroup;
        this.location = location;
        this.totalSlots = totalSlots;
        this.description = description;
        this.staffInCharge = staffInCharge;

    }

    // default constructor
    public CampInformation() {
        dates.add(LocalDate.now());
    }

    
    /**
     * Adds a Camp Committee Member to the set of committee members associated with the camp.
     * @param student The CampCommitteeMember to be added to the camp's committee.
     */
    public void addCampCommitteeMember(CampCommitteeMember student){
        this.campCommitteeSlots.add(student);
    }

    // public void editCampInfo(int choice){ //do we really want this here? i put in cmapManeger
    //     //switch case 1- date etc
    //     //ask what they want chng
    //     //scanf(newChange)
    //     // this.Dates = newChange
    // }


    //get methods
    /**
     * Retrieves the name of the camp.
     * @return The name of the camp.
     */
    public String getCampName(){
            return this.campName;
    }

    /**
     * Retrieves the name of the staff in charge of the camp.
     * @return The name of the staff in charge.
     */
    public String getStaffName(){
            return this.staffInCharge;
    }

    /**
     * Retrieves the dates of the camp.
     * @return The dates of the camp.
     */
    public ArrayList<LocalDate> getDates(){
        return this.dates;
    }

    /**
     * Retrieves the registration closing date for the camp.
     * @return The registration closing date.
     */
    public LocalDateTime getRegistrationClosingDate(){
        return this.registrationClosingDate;
    }

    /**
     * Retrieves the user group associated with the camp.
     * @return The user group of the camp.
     */
    public UserGroup getUserGroup(){
        return this.userGroup;
    }

    /**
     * Retrieves the location of the camp.
     * @return The location of the camp.
     */
    public String getLocation(){
        return this.location;
    }

    /**
     * Retrieves the total number of slots available in the camp.
     * @return The total number of slots in the camp.
     */
    public int getTotalSlots(){
        return this.totalSlots;
    }

    /**
     * Retrieves the set of committee members associated with the camp.
     * @return The set of committee members in the camp.
     */
    public HashSet<CampCommitteeMember> getCampCommitteeSlots(){
        return this.campCommitteeSlots;
    }

    /**
     * Retrieves the description of the camp.
     * @return The description of the camp.
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Sets the name of the camp.
     * @param campName The new name for the camp.
     */
    //set methods
    public void setCampName(String campName){
        this.campName = campName;
    }

    /**
     * Sets the dates of the camp.
     * @param Dates The new dates for the camp.
     */
    public void setDates(ArrayList<LocalDate> Dates){
        this.dates = Dates;
    }

    /**
     * Sets the registration closing date for the camp.
     * @param closingDate The new registration closing date for the camp.
     */
    public void setRegistrationClosingDate(LocalDateTime closingDate){
        this.registrationClosingDate = closingDate;
    }

    /**
     * Sets the user group associated with the camp.
     * @param userGroup The new user group for the camp.
     */
    public void setUserGroup(String userGroup){
        for (UserGroup usergroups : UserGroup.values()) {
            if(userGroup.equals(usergroups.toString())){
                this.userGroup = usergroups;
            }
        }
    }

    /**
     * Sets the location of the camp.
     * @param location The new location for the camp.
     */
    public void setLocation(String location){
        this.location = location;
    }

    /**
     * Sets the total number of slots available in the camp.
     * @param totalSlots The new total number of slots in the camp.
     */
    public void setTotalSlots(int totalSlots){
        this.totalSlots = totalSlots;
    }

    /**
     * Sets the description of the camp.
     * @param description The new description for the camp.
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * Sets the staff in charge of camp
     */

    public void setStaffInCharge(String staff_in_charge) {
        staffInCharge = staff_in_charge;
    }

    

}
