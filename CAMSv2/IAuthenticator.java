package CAMSv2;

/**
 * The {@code IAuthenticator} is an Interface for implementing the specific types of Authentication
 */
public interface IAuthenticator<T> {
    /**
     * Authenticates the user based on the provided user ID and password.
     *
     * @param userId   The user ID or username for authentication.
     * @param password The password associated with the user ID.
     * @return A generic type representing the authenticated user if successful, otherwise null.
     */
    public T authenticate(String userId, String password);
}