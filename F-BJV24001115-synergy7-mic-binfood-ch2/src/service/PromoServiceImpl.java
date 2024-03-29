package src.service;

import src.Data;
import src.exception.PromoNotFoundException;
import src.model.entity.MenuItem;
import src.model.entity.Order;
import src.model.entity.Promo;
import src.model.entity.PromoFreebies;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PromoServiceImpl implements PromoService {
    @Override
    public Promo get(int choice) {
        if (choice < 0 || choice >= Data.promos.size()) {
            throw new IndexOutOfBoundsException("Pilihan invalid: " + choice);
        }

        Promo promo = Data.promos.get(choice);
        if (promo == null) {
            throw new PromoNotFoundException("Promo tidak ditemukan: " + choice);
        }

        return promo;
    }

    @Override
    public List<Promo> getList() {
        return Data.promos;
    }

    @Override
    public ArrayList<Promo> getAvailablePromoList() {
        ArrayList<Promo> availablePromo = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        for (Promo promo : getList()) {
            if (currentDate.isAfter(promo.getValidStartDate()) && currentDate.isBefore(promo.getValidEndDate()) &&
                    currentTime.isAfter(promo.getValidStartTime()) && currentTime.isBefore(promo.getValidEndTime())) {
                availablePromo.add(promo);
            }
        }

        return availablePromo;
    }
    @Override
    public void resetPromo() {
        for (Promo promo : getAvailablePromoList()) {
            if (promo instanceof PromoFreebies promoFreebies) {
                Data.freebiesOrder.clear();
                ArrayList<MenuItem> eligibleMenu = promo.getEligibleMenu();

                int count = 0;
                for (Order order : Data.orders) {
                    if (eligibleMenu.contains(order.getMenuItem())) {
                        count += order.getQty();
                    }
                }
                count /= promoFreebies.getRequiredMenuItemCount();

                if (count > 0) {
                    Order freebies = promoFreebies.getFreeMenuItem();
                    freebies.setQty(count);
                    Data.freebiesOrder.add(freebies);
                }
            }
        }
    }
}
