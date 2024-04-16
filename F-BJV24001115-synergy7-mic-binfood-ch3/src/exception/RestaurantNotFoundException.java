package src.exception;

public class RestaurantNotFoundException extends IllegalArgumentException {
    public RestaurantNotFoundException(String message) {
        super(message);
    }
}
