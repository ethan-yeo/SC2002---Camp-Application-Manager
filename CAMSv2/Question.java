package CAMSv2;
import java.util.*;
/**
 * The {@code Question} class represents a question associated with a camp in the CAMSv2 system.
 */

public class Question {
    // Attributes

    /**
     * Indicates whether the question has been processed.
     */
    // added camp name to identify which camp does question belongs to.
    boolean processed = false;
    /**
     * The unique identifier for the question.
     */
    int id;
    /**
     * The name of the camp to which the question belongs.
     */
    String campName;
    /**
     * The text of the question.
     */
    String question;
    /**
     * The list of replies associated with the question.
     */
    ArrayList<Reply> replies;
    // Constructor

    /**
     * Constructs a {@code Question} object with the question text, camp name, and a unique identifier.
     *
     * @param question      The text of the question.
     * @param campNameString     The name of the camp to which the question belongs.
     * @param id            The unique identifier for the question.
     */


    public Question(String question, String campNameString, int id){
        this.question = question;
        this.campName = campNameString;
        this.replies = new ArrayList<Reply>();
        this.id = id;
    }
    // Methods

    /**
     * Sets the text of the question.
     *
     * @param question The text of the question.
     */
    
    //methods
    public void setQuestion(String question) {
        if (processed) {
            System.out.println("Enquiry is processed, unable to edit enquiry!");
            return;
        }
        System.out.println("Successfully edited enquiry");
        this.question = question;
    }
    /**
     * Retrieves the text of the question.
     *
     * @return The text of the question.
     */
    public String getQuestion() {
        return question;
    }
    /**
     * Retrieves the name of the camp to which the question belongs.
     *
     * @return The name of the camp.
     */

    public String getCampName() {
        return campName;
    }
    /**
     * Retrieves the list of replies associated with the question.
     *
     * @return The list of replies.
     */
    public ArrayList<Reply> getReplies() {
        return replies;
    }
    /**
     * Retrieves the unique identifier for the question.
     *
     * @return The unique identifier.
     */
    public int getQuestionId() {
        return this.id;
    }
    /**
     * Adds a reply to the list of replies associated with the question.
     *
     * @param Reply The reply to be added.
     */

    public void setReply(Reply Reply) {
        this.replies.add(Reply);
        processed = true;

    }

    public boolean getStatus() {
        return processed;
    }

}
