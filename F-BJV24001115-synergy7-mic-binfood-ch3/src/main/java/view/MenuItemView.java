package src.main.java.view;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import src.main.java.model.entity.MenuItem;
import static src.main.java.util.AdditionalUtils.formatPrice;
import static src.main.java.util.ColorUtils.COLOR_OF_PRICE;
import static src.main.java.util.ColorUtils.formatColor;

public class MenuItemView {
    public void displayMenuItemList(List<MenuItem> menu) {
        displayMenuItemHeader();
        AtomicInteger index = new AtomicInteger(1);
        menu.forEach(menuItem -> displayMenuItem(index.getAndIncrement(), menuItem));
    }

    public void displayMenuItemHeader() {
        System.out.println("      Menu       |    S    |    M    |    L    ");
    }

    public void displayMenuItem(int num, MenuItem menuItem) {
        System.out.printf(
                "%d. %-13s | %16s | %16s | %16s %n",
                num,
                menuItem.getName(),
                formatColor(formatPrice(menuItem.getSizePrice("S")), COLOR_OF_PRICE),
                formatColor(formatPrice(menuItem.getSizePrice("M")), COLOR_OF_PRICE),
                formatColor(formatPrice(menuItem.getSizePrice("L")), COLOR_OF_PRICE)
        );
    }
}
