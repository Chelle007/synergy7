package src.main.java.exception;

public class MenuItemNameExistedException extends IllegalArgumentException {
    public MenuItemNameExistedException(String message) {
        super(message);
    }
}
