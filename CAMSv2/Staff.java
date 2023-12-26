package CAMSv2;

import java.util.*;
/**
 * The {@code Staff} Represents a staff member in the system, extending the User class.
 * Staff members have the ability to create and manage camps, approve advice, and generate various reports.
 */
public class Staff extends User {
    private HashSet<Camp> createdCamps;
    /**
     * Constructor for the Staff class.
     *
     * @param emailID The email ID of the staff member.
     * @param password The password of the staff member.
     * @param faculty The faculty to which the staff member belongs.
     * @param name The name of the staff member.
     * @param role The role of the staff member.
     */
    public Staff(String emailID, String password, UserGroup faculty, String name, Role role){
        super(emailID,password,faculty,name,role);
        // initialize the createdCamps array
        createdCamps = searchForCreatedCamps();
    }
    /**
     * Searches for camps created by the staff member.
     *
     * @return A HashSet of camps created by the staff member.
     */
    private HashSet<Camp> searchForCreatedCamps() {
        HashSet<Camp> camps = new HashSet<Camp>();
        for (Camp camp : CampManager.getInstance().getCampList()) {
            if (camp.getStaffName().equals(name)) {
                camps.add(camp);
            }
        }
        return camps;
    }
    /**
     * Gets the set of camps created by the staff member.
     *
     * @return A HashSet of camps created by the staff member.
     */
    public HashSet<Camp> getCreatedCamps() {
        return createdCamps;
    }
    /**
     * Overrides the changePassword method in User class and writes changes to CSV.
     *
     * @param newPassword The new password for the staff member.
     */

    @Override
    public void changePassword(String newPassword) {
        super.changePassword(newPassword);
        StaffDataBase.getInstance().writeToCSV();
    }
    /**
     * Approves the advice for a specific camp based on the advice ID.
     *
     * @param camp The camp for which the advice is being approved.
     * @param id The ID of the advice to be approved.
     * @return True if the approval is successful, false otherwise.
     */
    public boolean approveAdvice(Camp camp, int id){
        // get the Advice object (Suggestion is referring to advice here)
        Advice advice = camp.getAdviceFromCamp(id);
        if (advice == null) {
            System.out.println("Please provide a valid Suggestion Id!");
            return true;
            }

        advice.setApproval(true);
        addPointsForApprovedSuggestions(advice.getName());
        System.out.println("Successfully set Suggestion status");
        camp.printSuggestionList();

        return true;
    }
    /**
     * Generates a report for each camp created by the staff member.
     *
     * @param filter The filter to be applied to the report.
     */

    public void generateStaffReport(IReportFilter filter) {
        // get staff camps
        for (Camp camp : createdCamps) {
            // call each camp to generate report
            camp.generateCampReport(filter);
        }
    }
    /**
     * Generates a report for the camp committee of each camp created by the staff member.
     */

    public void generateCampCommitteeReport() {
        for (Camp camp : createdCamps) {
            camp.generateCampCommitteeReport();
        }
    }
    /**
     * Generates a report for student enquiries for each camp created by the staff member.
     */

    public void generateStudentsEnquiryReport() {
        for (Camp camp : createdCamps) {
            camp.generateStudentsEnquiryReport();
        }
    }
    /**
     * Gets the name of the camp based on its index in the list of camps.
     *
     * @param indexOfCamp The index of the camp in the list.
     * @return The name of the camp.
     */

    public String getCampName(int indexOfCamp){
        String campName;

        //find name of camp
        ArrayList<Camp> campList = CampManager.getInstance().getCampList();
        campName = campList.get(indexOfCamp).getCampName();

        return campName;
    }

    /**
     * Adds points for approved suggestions to the corresponding camp committee member.
     *
     * @param campCommitteeMemberName The name of the camp committee member.
     */
    public void addPointsForApprovedSuggestions(String campCommitteeMemberName){
        for (CampCommitteeMember campCommitteeMember : CampCommitteeDataBase.getInstance().getCampCommitteeMembersList()) {
            if(campCommitteeMember.getName().equals(campCommitteeMemberName)){
                campCommitteeMember.addPointsByOne();
            }            
        }
    }
}
