package src.exception;

public class MenuItemNotFoundException extends IllegalArgumentException {
    public MenuItemNotFoundException(String message) {
        super(message);
    }
}
