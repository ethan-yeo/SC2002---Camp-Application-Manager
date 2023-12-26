package CAMSv2;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

/**

 * The {@code Camp} class represents a camp event with information such as camp details,
 * student lists, inquiries, suggestions, etc.
 * @author Zhu YuHao
 * @since 13-11-2023
 */
public class Camp {

    // Attributes
    /**
     * Indicates the visibility status of the camp.
     */

    boolean visibility;

    /**
     * Contains detailed information about the camp.
     */
    private CampInformation info;

    /**
     * Stores the list of students registered for the camp.
     */
    private HashSet<Student> studentList = new HashSet<Student>();
    /**
     * Stores the list of inquiries made by students for the camp.
     */

    private ArrayList<Enquiries> enquiriesList = new ArrayList<Enquiries>();

    /**
     * Stores the list of suggestions made by students for the camp.
     */
    private ArrayList<Suggestion> suggestionsList = new ArrayList<Suggestion>();

    /**
     * Stores the list of students who are blacklisted from the camp.
     */
    private HashSet<Student> blackList = new HashSet<Student>();

    // Constructors
    /**
     * Constructs an empty {@code Camp} object.
     * Used for creating an instance without initializing attributes.
     */
    public Camp(){ 
        //empty constructor
        this.visibility = false;
        this.info = new CampInformation();
    }


    // Methods

    /**
     * Checks if the registration deadline for the camp has passed.
     *
     * @return {@code true} if the registration is over the deadline, {@code false} otherwise.
     */
    public boolean isRegistrationOverDeadline() {
        // get registration deadline
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(info.getRegistrationClosingDate());
    }

    /**
     * Adds a student to the camp's blacklist.
     *
     * @param student The student to be added to the blacklist.
     */
    public void addToBlackList(Student student) {
        blackList.add(student);
    }
    /**
     * Adds a camp committee member to the camp.
     *
     * @param student The camp committee member to be added.
     */

    public void addCampCommitteeMember(CampCommitteeMember student) {
        info.getCampCommitteeSlots().add(student);
    }

    /**
     * Checks if a student is in the camp's blacklist.
     *
     * @param student The student to check.
     * @return {@code true} if the student is in the blacklist, {@code false} otherwise.
     */

    public boolean isStudentInBlackList(Student student) {
        for (Student s : blackList) {
            if (s.getName().equals(student.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a student is already registered for the camp.
     *
     * @param name The name of the student to check.
     * @return {@code true} if the student is registered, {@code false} otherwise.
     */

    public boolean isStudentRegistered(String name) {
        for (Student student : studentList) {
            if (student.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Adds a student to the camp's student list.
     *
     * @param student The student to be added.
     */

    public void addStudent(Student student){
        studentList.add(student);
    }

    /**
     * Removes the specified student from the student list.
     *
     * @param student The student to be removed.
     */
    public void removeStudent(Student student) {
        studentList.remove(student);
    }

    /**
     * Adds a student's inquiries to the camp's inquiry list or retrieves existing inquiries.
     *
     * @param studentName The name of the student.
     * @return The student's inquiries.
     */

    // check if student Enquiries is already in Camp
    // if not, create a new enquiry
    // add to camp
    public Enquiries addStudentEnquiriesInList(String studentName) {
        for (Enquiries enquiries : enquiriesList) {
            if (enquiries.getEnqurier().equals(studentName)) {
                return enquiries;
            }
        
        }

        Enquiries newEnquiry = new Enquiries(studentName);
        addEnquiry(newEnquiry);
        return newEnquiry;
    }
    /**
     * Checks if the camp is full (reached maximum capacity).
     *
     * @return {@code true} if the camp is full, {@code false} otherwise.
     */

    public boolean isCampFull() {
        int length = studentList.size();
        // System.out.println("StudentList: "  + length + "getTotalSlots: " + getTotalSlots());
        // System.out.println(info.getTotalSlots() == length);
        return info.getTotalSlots() == length;
    }


    /**
     * Edits a suggestion given by a student.
     *
     * @param studentName The name of the student.
     * @param newAdvice    The new advice to replace the existing one.
     * @param AdviceIndex  The index of the advice in the suggestion.
     */
    public void editSuggestion(String studentName, String newAdvice, int AdviceIndex){
        for (Suggestion suggestion : suggestionsList) {
            if (suggestion.getStudent().equals(studentName)) {
                suggestion.getAdviceList().get(AdviceIndex).setNewAdvice(newAdvice);
            }

        }
    }
    /**
     * Deletes a suggestion given by a student.
     *
     * @param studentName The name of the student.
     * @param AdviceIndex  The index of the advice in the suggestion to be deleted.
     */
    public void deleteSuggestion(String studentName, int AdviceIndex){
        for (Suggestion suggestion : suggestionsList) {
            if (suggestion.getStudent() == studentName) {
                suggestion.getAdviceList().remove(AdviceIndex);
            }

        }
    }

    /**
     * Retrieves the visibility status of this object.
     *
     * @return The visibility status (true if visible, false otherwise).
     */
    public boolean getVisibility() {
        return visibility;
    }

    /**
     * Sets the visibility status of the camp.
     *
     * @param choice {@code true} to make the camp visible, {@code false} to hide it.
     */
    public void setVisibility(boolean choice){
        this.visibility = choice;
    }
    /**
     * Adds an inquiry to the camp's inquiry list.
     *
     * @param enquiry The inquiry to be added.
     */

    public void addEnquiry(Enquiries enquiry){
        enquiriesList.add(enquiry);
    }
    /**
     * Adds a suggestion to the camp's suggestion list.
     *
     * @param suggestion The suggestion to be added.
     */

    public void addSuggestion(Suggestion suggestion){
        suggestionsList.add(suggestion);
    }
    /**
     * Retrieves the list of inquiries for the camp.
     *
     * @return The list of inquiries.
     */

    public ArrayList<Enquiries> getEnquiries(){
        return enquiriesList;
    }

    /**
     * Retrieves the list of suggestions for the camp.
     *
     * @return The list of suggestions.
     */

    public ArrayList<Suggestion> getSuggestions(){
        return suggestionsList;
    }

    /**
     * Retrieves the list of students registered for the camp.
     *
     * @return The list of students.
     */

    public HashSet<Student> getStudentList(){
            return studentList;
    }
    /**
     * Retrieves the name of the camp.
     *
     * @return The name of the camp.
     */

    public String getCampName(){
      return this.info.getCampName();
    }

    /**
     * Retrieves the name of the staff in charge of the camp.
     *
     * @return The name of the staff in charge.
     */

    public String getStaffName(){
       return this.info.getStaffName();
    }

    // Camp information functions - Get methods

    /**
     * Retrieves the dates of the camp.
     *
     * @return The array of dates representing the camp schedule.
     */


    public ArrayList<LocalDate> getDates(){
        return this.info.getDates();
    }
    /**
     * Retrieves the registration closing date for the camp.
     *
     * @return The registration closing date.
     */

    public LocalDateTime getRegistrationClosingDate(){
        return this.info.getRegistrationClosingDate();
    }
    /**
     * Retrieves the user group associated with the camp.
     *
     * @return The user group.
     */

    public UserGroup getUserGroup(){
        return this.info.getUserGroup();
    }

    /**
     * Retrieves the location of the camp.
     *
     * @return The location of the camp.
     */

    public String getLocation(){
        return this.info.getLocation();
    }
    /**
     * Retrieves the total slots for the camp.
     *
     * @return The total slots.
     */

    public int getTotalSlots(){
        return this.info.getTotalSlots();
    }

    public int getRemainingSlots() {
        return (this.info.getTotalSlots() - studentList.size());
    }

    /**
     * Retrieves the set of camp committee slots.
     *
     * @return The set of camp committee slots.
     */

    public HashSet<CampCommitteeMember> getCampCommitteeSlots(){
        return this.info.getCampCommitteeSlots();
    }

    /**
     * Retrieves the description of the camp.
     *
     * @return The description of the camp.
     */

    public String getDescription(){
        return this.info.getDescription();
    }

    // Camp information functions - Set methods

    /**
     * Sets the name of the camp.
     *
     * @param campName The new name of the camp.
     */
    


    //set methods
    public void setCampName(String campName){
        this.info.setCampName(campName);
    }

    /**
     * Sets the dates for the camp.
     *
     * @param Dates The array of dates representing the camp schedule.
     */

    public void setDates(ArrayList<LocalDate> Dates){
        this.info.setDates(Dates);
    }
    /**
     * Sets the registration closing date for the camp.
     *
     * @param closingDate The new registration closing date.
     */

    public void setRegistrationClosingDate(LocalDateTime closingDate){
        this.info.setRegistrationClosingDate(closingDate);
    }
    /**
     * Sets the user group associated with the camp.
     *
     * @param userGroup The new user group for the camp.
     */

    public void setUserGroup(String userGroup){
        this.info.setUserGroup(userGroup);
    }
    /**
     * Sets the location of the camp.
     *
     * @param location The new location of the camp.
     */

    public void setLocation(String location){
        this.info.setLocation(location);
    }
    /**
     * Sets the total available slots for the camp.
     *
     * @param totalSlots The new total available slots for the camp.
     */

    public void setTotalSlots(int totalSlots){
        this.info.setTotalSlots(totalSlots);
    }
    /**
     * Sets the description of the camp.
     *
     * @param description The new description of the camp.
     */

    public void setDescription(String description){
        this.info.setDescription(description);
    }

// Additional methods
    /**
     * Prints a table with information about the camp.
     */
    public void printCampInfoTable(){
        System.out.println("1. Camp Name = " + this.info.getCampName());

        System.out.println("2. Camp Dates = ");
        for (LocalDate date : this.info.getDates()) {
            System.out.println(date);
        }

        System.out.println("3. Registration Closing Date = " + this.info.getRegistrationClosingDate().toLocalDate());

        System.out.println("4. User Group = " + this.info.getUserGroup());

        System.out.println("5. Camp Location = " + this.info.getLocation());

        System.out.println("6. Camp Slots = " + (studentList.size()) + "/" + this.info.getTotalSlots());

        System.out.println("7. Camp Description = " + this.info.getDescription());
        System.out.println("8. Visibility = " + getVisibility());

    }

    /**
     * Prints camp information and the list of students.
     * This method displays camp information in a table format
     * and prints the student list.
     */
    public void printCampInfoAndList() {
        printCampInfoTable();
        System.out.println("9. Student List = ");
        printStudentList(studentList);
    }
    /**
     * Prints the list of students registered for the camp.
     */

    public void printStudentList(HashSet<Student> students) {
        System.out.println("Student Name" + " | " + "Role");
        for (Student student : students) {
            if (isCampCommitteeMember(student.getName())) {
                System.out.println(student.getName() + " | " + Role.CAMP_COMMITTEE_MEMBER.toString());
                continue;
                
            }
            System.out.println(student.getName() + " | " + "ATTENDEE");
            
        } 
    }
    /**
     * Prints the list of camp committee members.
     */

    public void printCCMList() {
        System.out.println("PRINTING CCMLIST");
        System.out.println("CCM Name" + " | " + "Points");
        for (CampCommitteeMember ccm : info.getCampCommitteeSlots()) {
            System.out.println(ccm.getName() + " | " + ccm.getPoints());
        }
    }
    /**
     * Prints the list of blacklisted students.
     */
    public void printBlackList() {
        System.out.println("PRINTING BLACKLIST");
        for (Student blackStudent : blackList) {
            System.out.println(blackStudent.getName());
        }
    }
    /**
     * Prints the list of inquiries made by students for the camp.
     */
    public void printEnquiriesList() {
        for (Enquiries enquiries : enquiriesList) {
            System.out.println("Enquirier: " + enquiries.getEnqurier());
            System.out.println("Enquiry Id" + " | " + "Enquiry" + " | " + "Processed");
            for (Question question : enquiries.getQuestions()) {
                System.out.println(question.getQuestionId() + " | " + question.getQuestion() + " | " + question.getStatus());
                for (Reply reply : question.getReplies()) {
                    System.out.println(reply.getName() + " Replied: " + reply.getReply());
                }
            }
        }
    }

    /**
     * Prints the suggestion list with details.
     * This method iterates through the suggestions list and displays
     * suggester information along with suggestions and their approvals.
     */
    public void printSuggestionList() {
    for (Suggestion suggestion : suggestionsList) {
        System.out.println("Suggester: " + suggestion.getStudent());
        System.out.println("Suggestion Id" + " | " + "Suggestion" + " | " + "Approved");
        for (Advice advice : suggestion.getAdviceList()) {
            System.out.println(advice.getId() + " | " + advice.getAdvice() + " | " + advice.getApproved());
        }
    }
}

    /**
     * Deletes an enquiry from the camp.
     *
     * @param question    The question to be deleted.
     * @param studentName The name of the student who made the enquiry.
     */

    public void deleteEnquiry(Question question, String studentName) {
        Enquiries enquiries = addStudentEnquiriesInList(studentName);
        ArrayList<Question> questions = enquiries.getQuestions();
        questions.remove(question);

    }
    /**
     * Retrieves an enquiry from the camp based on its ID.
     *
     * @param Id The ID of the enquiry.
     * @return The corresponding enquiry.
     */

    public Question getEnquiryFromCamp(int Id) {
        for (Enquiries enquiry : enquiriesList) {
            for (Question question : enquiry.getQuestions()) {
                if (question.getQuestionId() == Id) {
                    return question;
                }
            }
        }
        return null;
    }
    /**
     * Retrieves a suggestion made by a student.
     *
     * @param name The name of the student who made the suggestion.
     * @return The corresponding suggestion.
     */

    public Suggestion getSuggestionBySuggester(String name) {
        for (Suggestion suggestion : suggestionsList) {
            if (suggestion.getStudent().equals(name)) {
                return suggestion;
            }
        }
        // System.out.println("Cannot find the suggestion created by " + name);
        return null;
    }
    /**
     * Retrieves advice from the camp based on the advice ID.
     *
     * @param id The ID of the advice to retrieve.
     * @return The advice with the specified ID, or null if not found.
     */
    public Advice getAdviceFromCamp(int id) {
        for (Suggestion suggestion : suggestionsList) {
            for (Advice advice : suggestion.getAdviceList()) {
                if (advice.getId() == id) {
                    return advice;
                }
            }
        }
        return null;
    }
    /**
     * Generates a camp report based on the provided filter.
     *
     * @param filter The filter to apply to the student list.
     */
    public void generateCampReport(IReportFilter filter) {
        // list of students attending each camp
        // printCampInfoTable();
        // printStudentList(filter.getFilteredList(getStudentList()));
        // generate csv
        ReportDatabase.getInstance().generateCampReportCSV(this, filter);
    }
    /**
     * Generates a camp committee report.
     * Displays CCM name and points.
     */
    public void generateCampCommitteeReport() {
        // CCM name, points 
        // printCCMList();
        // generate csv
        ReportDatabase.getInstance().generateCCMReportCSV(this);
    }
    /**
     * Generates a students' enquiry report.
     * Displays Student Name and Enquiry.
     */
    public void generateStudentsEnquiryReport() {
        // Student Name, Enquiry
        // printEnquiriesList();
        // generate csv
        ReportDatabase.getInstance().generateStudentsEnquiryReport(this);
    }
    /**
     * Sets the staff in charge of the camp.
     *
     * @param staffName The name of the staff member to set as in charge.
     */
    public void setStaff_in_charge(String staffName) {
        this.info.setStaffInCharge(staffName);
    }
    /**
     * Checks if a student is a camp committee member.
     *
     * @param studentName The name of the student to check.
     * @return True if the student is a camp committee member, false otherwise.
     */

    public boolean isCampCommitteeMember(String studentName) {
        for (CampCommitteeMember ccm : info.getCampCommitteeSlots()) {
            if (studentName.equals(ccm.getName())) {
                return true;
            }
        }
        return false;
    }


}
