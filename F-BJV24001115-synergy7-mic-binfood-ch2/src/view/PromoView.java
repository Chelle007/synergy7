package src.view;

import src.model.entity.Promo;

import java.util.ArrayList;

import static src.util.ColorUtils.printlnColor;

public class PromoView {
    public void displayPromoList(ArrayList<Promo> availablePromo) {
        if (!availablePromo.isEmpty()) {
            printlnColor("PROMO :", "bold");
            for (Promo promo : availablePromo) {
                System.out.println(promo.getName() + " | " + promo.getDescription());
            }
            System.out.println();
        }
    }
}
