package CAMSv2;
/**
 * The {@code GoToMainMenu} class Represents a boolean value indicating whether to go to the main menu or not.
 */
public class GoToMainMenu {

    private boolean booleanValue = false;
    /**
     * Sets the boolean value to indicate whether to go to the main menu.
     *
     * @param goToMainMenu The boolean value to set.
     */

    public void setBooleanValue(boolean goToMainMenu) {
        this.booleanValue = goToMainMenu;
    }
    /**
     * Gets the boolean value indicating whether to go to the main menu.
     *
     * @return The boolean value.
     */

    public boolean getBooleanValue() {
        return booleanValue;
    }
}
