package CAMSv2;

import java.util.ArrayList;

/**
 * The {@code Suggestion} class represents a suggestion made by a student,
 * containing the student's name and a list of advice associated with the suggestion.
 */

public class Suggestion {
    // Attributes
    /**
     * The name of the student who made the suggestion.
     */
    private String studentName;
    /**
     * The list of advice associated with the suggestion.
     */
    private ArrayList<Advice> adviceList = new ArrayList<Advice>();
    /**
     * Constructs a {@code Suggestion} object with an initial advice and the student's name.
     *
     * @param student The name of the student who made the suggestion.
     */    
    //methods
    public Suggestion(String student){
        this.studentName = student;
    }


    // Methods

    /**
     * Adds an advice to the list of advice associated with the suggestion.
     *
     * @param advice The advice to be added.
     */

    public void addAdvice(Advice advice) {
        adviceList.add(advice);
    }

    /**
     * Retrieves the name of the student who made the suggestion.
     *
     * @return The name of the student.
     */

    public String getStudent() {
        return studentName;
    }

    /**
     * Retrieves the list of advice associated with the suggestion.
     *
     * @return The list of advice.
     */
    public ArrayList<Advice> getAdviceList(){
        return adviceList;
    }


}
