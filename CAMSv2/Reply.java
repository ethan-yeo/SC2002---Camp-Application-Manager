package CAMSv2;
/**
 * The {@code Reply} class represents a reply associated with a question in the CAMSv2 system.
 */

public class Reply {
    // Attributes

    /**
     * The name of the responder.
     */
    private String name;
    /**
     * The reply provided by the responder.
     */
    private String reply;
    /**
     * Constructs a {@code Reply} object with a responder's name and the reply content.
     *
     * @param name  The name of the responder.
     * @param reply The reply content.
     */

    Reply(String name, String reply) {
        this.name = name;
        this.reply = reply;
    }
    // Methods

    /**
     * Retrieves the name of the responder.
     *
     * @return The name of the responder.
     */

    public String getName() {
        return name;
    };

    /**
     * Retrieves the reply content.
     *
     * @return The reply content.
     */
    
    public String getReply() {
        return reply;
    }
}
