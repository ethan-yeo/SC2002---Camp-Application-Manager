package CAMSv2;

import java.util.*;
/**
 *The {@code Enquiries} class Represents a collection of inquiries made by a specific enquirer.
 */
public class Enquiries {
    //enquiry will hold a list of question and replies
    private String enqurier = "";
    private ArrayList<Question> questions = new ArrayList<Question>();


    /**
     * Constructs an instance of {@code Enquiries} with the specified enquirer.
     *
     * @param enqurier The enquirer making the inquiries.
     */
    public Enquiries(String enqurier){
        this.enqurier = enqurier;       
    }
    /**
     * Adds a question to the list of inquiries.
     *
     * @param question The question to be added.
     */
    public void addQuestion(Question question) {
        questions.add(question);
    }
    /**
     * Retrieves the list of questions in the inquiries.
     *
     * @return The list of questions.
     */
    public ArrayList<Question> getQuestions(){
        return questions;
    }
    /**
     * Retrieves a specific question based on its ID.
     *
     * @param Id The id of the question to retrieve.
     * @return The question with the specified ID, or {@code null} if not found.
     */
    public Question getQuestion(int Id) {
        for (Question question : questions) {
            if (question.getQuestionId() == Id) {
                return question;
            }
        }
        return null;
    }
    /**
     * Retrieves the enquirer's name.
     *
     * @return The name of the enquirer.
     */

    public String getEnqurier() {
        return enqurier;
    }

    

    
}
