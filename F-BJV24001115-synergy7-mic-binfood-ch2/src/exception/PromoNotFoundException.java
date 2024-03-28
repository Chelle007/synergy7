package src.exception;

public class PromoNotFoundException extends IllegalArgumentException {
    public PromoNotFoundException(String message) {
        super(message);
    }
}
