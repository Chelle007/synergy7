package src.controller;

import java.util.ArrayList;

public class PromoController {
    public void displayPromoList() {
        PromoService ps = new PromoServiceImpl();
        PromoView pv = new PromoView();

        ArrayList<Promo> availablePromo = ps.getAvailablePromoList();
        if (!availablePromo.isEmpty()) {
            pv.displayPromoList(availablePromo);
        }
    }
}
