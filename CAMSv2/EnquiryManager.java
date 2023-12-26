package CAMSv2;
import java.util.ArrayList;
import java.util.Scanner;
//import CAMSv2.CampManager;
/**
 * The {@code EnquiryManager} class Manages the creation and handling of inquiries related to camps.
 */
public class EnquiryManager {
    //attribute
    private static int enquiryCounter = 1;
    private static EnquiryManager instance;
    /**
     * Gets the instance of {@code EnquiryManager}, creating a new one if it doesn't exist.
     *
     * @return The instance of {@code EnquiryManager}.
     */
    private EnquiryManager() {}
    public static EnquiryManager getInstance() {
        if (instance == null) {
            instance = new EnquiryManager();
        }
        return instance;
    }
    /**
     * Creates a new inquiry with the given question for a specific camp and student.
     *
     * @param question     The question to be added to the inquiry.
     * @param camp         The camp related to the inquiry.
     * @param studentName  The name of the student creating the inquiry.
     */
    //methods
    public void createEnquiry(Question question, Camp camp, String studentName){
        // check if an Enquiries already exist for the student calling this.
        enquiryCounter++;
        // take existing
        // create a new enquiries object in the camp
        Enquiries newEnquiries = camp.addStudentEnquiriesInList(studentName);
        // add question to camp
        newEnquiries.addQuestion(question);

    }
    /**
     * Views inquiries for a specific camp and staff member.
     *
     * @param campName  The name of the camp.
     * @param staffName The name of the staff member.
     * @return {@code true} if there are no inquiries, {@code false} otherwise.
     */

    //staff fucntion
    public boolean viewEnquiryForStaff(String campName, String staffName){
        //printing and logic will occur in this methoed
        // staff->viewEn->thisviewEnq->getcamp->
        //for loop to iterate arraylist of enquiries
        //returns true if empty, return false if have enquiries
        for(int i=0;i<CampManager.getInstance().getCampList().size();i++){

            if(CampManager.getInstance().getStaffinCharge(campName,staffName)){
                Camp camp = CampManager.getInstance().getCamp(campName);
                System.out.println(campName + " enquiries:");

                //check if empty
                if(camp.getEnquiries().size()==0){
                    System.out.println("There are no enquiries");
                    return true; //it is empty
                }
                else{
                    for(int j=0;j<camp.getEnquiries().size();j++){

                        for(int k=0;k<camp.getEnquiries().get(j).getQuestions().size();k++){

                        System.out.println("Enquiry " + j+1 + " Question " + k+1 + ". " + camp.getEnquiries().get(j).getQuestions().get(k).getQuestion());
                        //enq 1 qns 2 will = 12

                        }//inner for
                    }
                }
            

            }//if

        }//for
        return false; //enquiries are not empty
    }//viewEnquiry


    /**
     * Replies to an inquiry for a specific camp and staff member.
     *
     * @param campName  The name of the camp.
     * @param staffName The name of the staff member.
     */
    public void replyEnquiryFromStaff(String campName, String staffName){
        Scanner sc = new Scanner(System.in);
        Camp curCamp = CampManager.getInstance().getCamp(campName);
        boolean empty;
        int enqIndex;
        int qnsIndex;
        String newReply;

        //call view enquiry
        empty = this.viewEnquiryForStaff(campName,staffName);//prints list of enq
        //should i make above a boolean
        if(empty==false){

        
            System.out.println("Which enquiry would you like to reply to?");
            //value taken is +1 of actual index
            enqIndex = sc.nextInt();
            enqIndex--; //now is correct index

            System.out.println("Which question would you like to reply to?");
            qnsIndex = sc.nextInt();
            qnsIndex--;


            //this is reply portion
            //replying to a specific enquiry from a specific camp
            // staff.replyEnq-> enqManager.replyEnq(campname) -> takes input of index of enq, takes input on reply itself-> camp.enqList[index] -> 
            System.out.println("Enter your reply");
            newReply = sc.nextLine();
            curCamp.getEnquiries().get(enqIndex).getQuestions().get(qnsIndex).setReply(new Reply(staffName, newReply));
            System.out.println("Reply uploaded");
        }

    }//replyEnquiry
    /**
     * Views inquiries for a specific camp as a camp committee member.
     *
     * @param campName The name of the camp.
     */

    //committee member function
    public void viewEnquiryForCampCommitteeMember(String campName){
        //for loop to iterate arraylist of enquiries
        for(int i=0;i<CampManager.getInstance().getCampList().size();i++) {
            Camp camp = CampManager.getInstance().getCamp(campName);
            System.out.println(campName + "enquiries");

            for (int j = 0; j < camp.getEnquiries().size(); j++) {
                for (int k = 0; k < camp.getEnquiries().get(j).getQuestions().size(); k++) {
                    System.out.println("Enquiry " + j + 1 + "Question " + k + 1 + ". " + camp.getEnquiries().get(j).getQuestions().get(k));
                    //enq 1 qns 2 will = 12
                }
            }
        }
    }
    /**
     * Replies to an inquiry for a specific camp as a camp committee member.
     *
     * @param campName The name of the camp.
     */

    public void replyEnquiryFromCampCommitteeMember(String campName){
        Scanner sc = new Scanner(System.in);
        Camp curCamp = CampManager.getInstance().getCamp(campName);

        int enqIndex;
        int qnsIndex;
        String newReply;

        //call view enquiry
        this.viewEnquiryForCampCommitteeMember(campName);//prints list of enq

        System.out.println("Which enquiry would you like to reply to?");
        //value taken is +1 of actual index
        enqIndex = sc.nextInt();
        enqIndex--; //now is correct index

        System.out.println("Which question would you like to reply to?");
        qnsIndex = sc.nextInt();
        qnsIndex--;


        //this is reply portion
        //replying to a specific enquiry from a specific camp
        // committeeMember.replyEnq-> enqManager.replyEnq(campname) -> takes input of index of enq, takes input on reply itself-> camp.enqList[index] ->
        System.out.println("Enter your reply");
        newReply = sc.nextLine();
        curCamp.getEnquiries().get(enqIndex).getQuestions().get(qnsIndex).setReply(new Reply(campName, newReply));
        System.out.println("Reply uploaded");
    }//replyEnquiry
    /**
     * Gets the current value of the inquiry counter.
     *
     * @return The current value of the inquiry counter.
     */

    public static int getEnquiryCounter() {
        return enquiryCounter;
    }
    /**
     * Prints the questions in the given inquiries.
     *
     * @param enquiries The inquiries to print questions for.
     */

    public static void printQuestions(Enquiries enquiries) {
        System.out.println(enquiries.getEnqurier());
        ArrayList<Question> questions = enquiries.getQuestions();
        for (Question question : questions) {
            System.out.println(question.getQuestionId());
            System.out.println(question.getQuestion());
            System.out.println(question.getReplies());
        }
    }

}
