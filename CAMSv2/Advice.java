package CAMSv2;

//import java.util.ArrayList;

/**
 * The {@code Advice} class represents a piece of advice and its approval status.
 * Instances of this class can be used to store, retrieve, and manage advice.
 */

public class Advice {
    //attritbutes
    private String name;
    private String advice;
    private Boolean approved;
    private int id;
    
    /**
     * Constructs an {@code Advice} object with the given advice.
     *
     * @param advice The advice to be stored in the object.
     */

    //constructor
    public Advice(String advice, int id, String name){
        this.advice= advice;
        this.approved = false;
        this.id = id;
        this.name = name;
    }

    /**
     * Retrieves the name associated with this object.
     *
     * @return The name of the object.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the approval status of the advice.
     *
     * @param bool {@code true} if the advice is approved, {@code false} otherwise.
     */


    public void setApproval(boolean bool){
        this.approved = bool;
    }
    /**
     * Updates the advice content with new advice.
     *
     * @param newAdvice The new advice to replace the existing one.
     */

    public void setNewAdvice(String newAdvice){
        if (approved) {
            System.out.println("Suggestion is approved, cannot edit suggestion!");
            return;
        }
        System.out.println("Successfully set new suggestion!");
        advice = newAdvice;
    }
    /**
     * Retrieves the stored advice.
     *
     * @return The advice stored in this object.
     */

    public String getAdvice() {
        return advice;
    }

    /**
     * Retrieves the approval status of the advice.
     *
     * @return {@code true} if the advice is approved, {@code false} otherwise.
     */
    public Boolean getApproved() {
        return approved;
    }

    /**
     * Retrieves the ID associated with this object.
     *
     * @return The ID of the object.
     */
    public int getId() {
        return id;
    }


}  

