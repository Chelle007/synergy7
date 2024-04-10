package src.exception;

public class OrderDetailNotFoundException extends IllegalArgumentException {
    public OrderDetailNotFoundException(String message) {
        super(message);
    }
}