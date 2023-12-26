package CAMSv2;


/**
 * The {@code CampCommitteeMember} class represents a Camp Committee Member, extending the Student class.
 * Manages committee-specific actions within a camp.
 * @author Zhu Yu Hao
 * @since 13-11-2023
 */

public class CampCommitteeMember extends Student {
    private int points;
    private Camp camp;
    private Suggestion suggestion; 


    /**
     * Constructor for CampCommitteeMember.
     * @param name      The name of the committee member.
     * @param emailID   The email ID of the committee member.
     * @param faculty   The faculty or user group of the committee member.
     * @param password  The password of the committee member.
     * @param role      The role of the committee member.
     * @param camp      The associated camp for the committee member.
     */
    public CampCommitteeMember(String name, String emailID, UserGroup faculty, String password, Role role, Camp camp) {
        super(name, emailID, faculty, password, role);
        points = 0;
        this.camp = camp;
        // initialize Suggestion
        this.suggestion = new Suggestion(name);
    }

    /**
     * Overrides the password change method and updates the database.
     */
    @Override
    public void changePassword(String newPassword) {
        super.changePassword(newPassword);
        CampCommitteeDataBase.getInstance().writeToCSV();
    }


    /**
     * Submits a suggestion for the associated camp.
     * @param suggestion The suggestion to be submitted.
     */
    public void submitSuggestion(String suggestion){
        Advice advice = new Advice(suggestion, SuggestionManager.getInstance().getId(), this.name);
        SuggestionManager.getInstance().createSuggestion(advice, this.camp, this.name);
        System.out.println("Suggestion added");
    }

    /**
     * Generates a camp report based on the provided filter.
     *
     * @param filter An implementation of the {@code IReportFilter} interface to filter the report data.
     */
    public void generateCampReport(IReportFilter filter) {
        camp.generateCampReport(filter);
    }

    /**
     * Generates a report for the camp committee.
     * This method triggers the generation of a report specifically tailored for the camp committee.
     */
    public void generateCampCommitteeReport() {
        camp.generateCampCommitteeReport();
    }

    /**
     * Generates a report regarding student enquiries.
     * This method triggers the generation of a report specifically focused on student enquiries.
     */
    public void generateStudentsEnquiryReport() {
        camp.generateStudentsEnquiryReport();
    }



    /**
     * Increments the points of the committee member by one.
     */
    public void addPointsByOne(){
        points +=1;
    }

    /**
     * Retrieves the associated camp.
     * @return The camp associated with the committee member.
     */
    public Camp getCamp(){
        return camp;
    }

    /**
     * Retrieves the points of the committee member.
     * @return The points of the committee member.
     */
    public int getPoints(){
        return points;
    }

    /**
     * Displays the profile information, including camp details.
     * This method extends the display by showing the camp name (CCM) associated with the profile.
     */
    @Override
    public void displayProfile() {
        super.displayProfile();
        System.out.println("Camp(CCM): " + camp.getCampName());
    }



}
