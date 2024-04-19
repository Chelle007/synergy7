package src.main.java.exception;

public class MenuItemNotFoundException extends IllegalArgumentException {
    public MenuItemNotFoundException(String message) {
        super(message);
    }
}
