package CAMSv2;

import java.util.Scanner;

/**
 * Singleton {@code SuggestionManager} class responsible for managing Suggestion utility methods.
 */

public class SuggestionManager {
    private static SuggestionManager instance;
    private static int counter = 1;

    public int getId() {
        return counter;
    }

    private SuggestionManager() {
    }

    /**
     * Retrieves the instance of the {@code SuggestionManager}.
     *
     * @return The instance of the {@code SuggestionManager}.
     */
    public static SuggestionManager getInstance() {
        if (instance == null) {
            instance = new SuggestionManager();
        }
        return instance;
    }
    //methods
        /**
     * Creates a new suggestion for a given camp, associating it with a student's name.
     *
     * @param advice    The Advice created in the CCM.
     * @param camp  The camp the advice is to be created in.
     * @param name The name of the student making the suggestion.
     */
    public void createSuggestion(Advice advice, Camp camp, String studentName){
        // increment advice id
        counter++;
        // check for existing suggestion wrapper in camp already.
        Suggestion suggestion = camp.getSuggestionBySuggester(studentName);
        // create new suggestion if doesn't exist in camp
        if (suggestion == null) {
            suggestion = new Suggestion(studentName);
            System.out.println("This is your first suggestion for this camp!");
            camp.addSuggestion(suggestion);

        }
        suggestion.addAdvice(advice);
    }
    /**
     * Allows staff members to view suggestions for a specific camp.
     *
     * @param campName  The name of the camp.
     * @param staffName The name of the staff member.
     */

    //staff function
    public void viewSuggestionForStaff(String campName, String staffName){
        //printing and logic will occur in this methoed
        // staff->viewEn->thisviewEnq->getcamp->
        //for loop to iterate arraylist of suggestion
        for(int i=0;i<CampManager.getInstance().getCampList().size();i++){

            if(CampManager.getInstance().getStaffinCharge(campName,staffName)){

                Camp camp = CampManager.getInstance().getCamp(campName);
                System.out.println(campName + " suggestion");

                for(int j=0;j<camp.getSuggestions().size();j++){

                    for(int k=0;k<camp.getSuggestions().get(j).getAdviceList().size();k++){

                    System.out.println("Suggestion " + j+1 + " Advice " + k+1 + "- " + camp.getSuggestions().get(j).getAdviceList().get(k));

                    }//inner for
                }//mid for
            }//if

           

        }//outer for
    }//viewSuggestion

    /**
     * Allows staff members to approve an advice associated with a suggestion.
     *
     * @param campName  The name of the camp.
     * @param staffName The name of the staff member.
     * @return The approved suggestion.
     */

    //approve suggestion
    //staff.approvesuggestion-> suggManager.approveAdvice->
    public Suggestion approveAdvice(String campName, String staffName){
        Scanner sc = new Scanner(System.in);

        Camp curCamp = CampManager.getInstance().getCamp(campName);

        int suggIndex;
        int adviceIndex;

        this.viewSuggestionForStaff(campName, staffName);

        System.out.println("Which suggestion would you like to attend to?");
        //value taken is +1 of actual index
        suggIndex = sc.nextInt();
        suggIndex--; //now is correct index

        System.out.println("Which advice would you like to approve?");
        adviceIndex = sc.nextInt();
        adviceIndex--;

        String sample = "approved";
        String approval;
        boolean approved;
        System.out.println("Input approved to approve advice");
        approval = sc.nextLine();
        approved = sample.equals(approval);

        curCamp.getSuggestions().get(suggIndex).getAdviceList().get(adviceIndex).setApproval(approved);

        if(approved){
            CampManager.getInstance().editCamp(campName, staffName);
            //edit camp
        }
        return curCamp.getSuggestions().get(suggIndex);
        //sc.close();
    }
    /**
     * Allows committee members to view suggestions for a specific camp.
     *
     * @param committeeMemberName The name of the committee member.
     * @param campName            The name of the camp.
     */
    //committee functions
    public void viewSuggestionForCommitteeMember(String committeeMemberName,String campName){
        //printing and logic will occur in this method
        // staff->viewEn->thisviewEnq->getcamp->
        //for loop to iterate arraylist of suggestion
        for(int i=0;i<CampManager.getInstance().getCampList().size();i++){
            Camp camp = CampManager.getInstance().getCamp(campName);
            System.out.println(campName + "suggestion");
                for(int j=0;j<camp.getSuggestions().size();j++){
                    if(camp.getSuggestions().get(i).getStudent() == committeeMemberName){
                      for(int k=0;k<camp.getSuggestions().get(j).getAdviceList().size();k++){
                        System.out.println("Advice " + k+1 + "- " + camp.getSuggestions().get(j).getAdviceList().get(k));

                    }//inner for
                }//if
            }//mid for
        }//outer for
    }//viewSuggestion
    /**
     * Allows committee members to edit an advice associated with a suggestion.
     *
     * @param studentName The name of the committee member.
     * @param campName    The name of the camp.
     */
    public void editSuggestionForCommitteeMember(String studentName, String campName){
         int advIndex;
         String newAdvice;
         Scanner sc = new Scanner(System.in);
         this.viewSuggestionForCommitteeMember(studentName,campName);
         System.out.println("which advice would you like to edit");
         advIndex = sc.nextInt();
         advIndex--; //now is correct index

         System.out.println("Enter your new advice");
         newAdvice = sc.nextLine();

         Camp camp = new Camp();
         camp.editSuggestion(studentName,newAdvice,advIndex);


    }
    /**
     * Allows committee members to delete an advice associated with a suggestion.
     *
     * @param studentName The name of the committee member.
     * @param campName    The name of the camp.
     */

    public void deleteSuggestionForCommitteeMember(String studentName, String campName){
        int advIndex;
        Scanner sc = new Scanner(System.in);
        this.viewSuggestionForCommitteeMember(studentName,campName);
        System.out.println("which advice would you like to delete");
        advIndex = sc.nextInt();
        advIndex--; //now is correct index

        Camp camp = new Camp();
        camp.deleteSuggestion(studentName,advIndex);
    }
   

    
}
