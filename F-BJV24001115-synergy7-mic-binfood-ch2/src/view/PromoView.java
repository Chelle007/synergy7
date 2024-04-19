package src.main.java.view;

import static src.main.java.util.ColorUtils.printlnColor;

import java.util.List;

public class PromoView {
    public void displayPromoList(List<Promo> availablePromo) {
        if (!availablePromo.isEmpty()) {
            printlnColor("PROMO :", "bold");
            for (Promo promo : availablePromo) {
                System.out.println(promo.getName() + " | " + promo.getDescription());
            }
            System.out.println();
        }
    }
}
