package src.controller;

import src.model.entity.Promo;
import src.service.PromoService;
import src.service.PromoServiceImpl;
import src.view.PromoView;

import java.util.ArrayList;

public class PromoController {
    public void displayPromoList() {
        PromoService ps = new PromoServiceImpl();
        PromoView pv = new PromoView();

        ArrayList<Promo> availablePromo = ps.getAvailablePromo();
        if (!availablePromo.isEmpty()) {
            pv.displayPromoList(availablePromo);
        }
    }

    public void resetPromo() {
        PromoService ps = new PromoServiceImpl();

        ps.resetPromo();
    }
}
