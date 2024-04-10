package src.view;

import java.util.List;

import src.model.entity.MenuItem;
import static src.util.AdditionalUtils.formatPrice;
import static src.util.ColorUtils.COLOR_OF_PRICE;
import static src.util.ColorUtils.formatColor;

public class MenuItemView {
    public void displayMenuItemList(List<MenuItem> menu) {
        displayMenuItemHeader();
        for (int i = 0; i<menu.size(); i++) {
            MenuItem menuItem = menu.get(i);
            displayMenuItem(i+1, menuItem);
        }
    }

    public void displayMenuItemHeader() {
        System.out.println("      Menu       |    S    |    M    |    L    ");
    }

    public void displayMenuItem(int num, MenuItem menuItem) {
        System.out.printf(
                "%d. %-13s | %16s | %16s | %16s %n",
                (num),
                menuItem.getName(),
                formatColor(formatPrice(menuItem.getSizePrice("S")), COLOR_OF_PRICE),
                formatColor(formatPrice(menuItem.getSizePrice("M")), COLOR_OF_PRICE),
                formatColor(formatPrice(menuItem.getSizePrice("L")), COLOR_OF_PRICE)
        );
    }
}
