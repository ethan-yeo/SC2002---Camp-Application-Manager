package CAMSv2;
import java.util.*;
/**
 * The {@code BaseController} is an Abstract class representing a base controller for managing user interactions.
 *
 * @param <U> The type of user associated with the controller.
 * @param <V> The type of view associated with the controller.
 */
public abstract class BaseController<U, V> {
    protected U user;
    protected V view;
    protected Scanner sc;

    /**
     * Constructor for the BaseController class.
     *
     * @param user The user associated with the controller.
     * @param view The view associated with the controller.
     */
    public BaseController(U user, V view) {
        this.user = user;
        this.view = view;
        sc = new Scanner(System.in);
    }

    /**
     * Abstract method to start the program or manage user interactions.
     */
    public abstract void startProgram();
}
