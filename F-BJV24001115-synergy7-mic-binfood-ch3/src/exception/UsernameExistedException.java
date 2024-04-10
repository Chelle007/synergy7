package src.exception;

public class UsernameExistedException extends IllegalArgumentException {
    public UsernameExistedException(String message) {
        super(message);
    }
}
