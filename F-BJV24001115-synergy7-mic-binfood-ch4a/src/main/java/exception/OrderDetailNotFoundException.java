package src.main.java.exception;

public class OrderDetailNotFoundException extends IllegalArgumentException {
    public OrderDetailNotFoundException(String message) {
        super(message);
    }
}