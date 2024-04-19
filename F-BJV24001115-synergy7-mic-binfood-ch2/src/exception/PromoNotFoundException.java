package src.main.java.exception;

public class PromoNotFoundException extends IllegalArgumentException {
    public PromoNotFoundException(String message) {
        super(message);
    }
}
