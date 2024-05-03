package src.main.java.exception;

public class UsernameExistedException extends IllegalArgumentException {
    public UsernameExistedException(String message) {
        super(message);
    }
}
