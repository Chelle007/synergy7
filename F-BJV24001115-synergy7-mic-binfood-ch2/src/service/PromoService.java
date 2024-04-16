package src.service;

import java.util.ArrayList;
import java.util.List;

public interface PromoService {
    Promo get(int choice);
    List<Promo> getList();
    ArrayList<Promo> getAvailablePromoList();
    void resetPromo();
}
