package src.main.java.exception;

public class RestaurantNotFoundException extends IllegalArgumentException {
    public RestaurantNotFoundException(String message) {
        super(message);
    }
}
