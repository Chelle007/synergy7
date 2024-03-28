package src.exception;

public class OrderNotFoundException extends IllegalArgumentException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}