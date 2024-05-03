package com.example.binarfud.view;

import com.example.binarfud.model.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.example.binarfud.util.AdditionalUtils.formatPrice;
import static com.example.binarfud.util.ColorUtils.COLOR_OF_PRICE;
import static com.example.binarfud.util.ColorUtils.formatColor;

@Component
public class MenuItemView {
    public void displayMenuItemList(List<MenuItem> menu, int page, int menuItemCountPerPage, int totalPage) {
        if (menu.isEmpty()) System.out.println("Restaurant ini belum ada menu.\n");
        else {
            displayMenuItemHeader();
            AtomicInteger index = new AtomicInteger(1);
            menu.forEach(menuItem -> displayMenuItem(index.getAndIncrement() + (page-1)*menuItemCountPerPage, menuItem));

            int totalWidth = 47;
            String pageNavigation = page + "/" + totalPage;
            int textWidth = pageNavigation.length();
            int padding = (totalWidth - textWidth) / 2;
            System.out.println("-".repeat(padding) + pageNavigation + "-".repeat(padding));
        }
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
