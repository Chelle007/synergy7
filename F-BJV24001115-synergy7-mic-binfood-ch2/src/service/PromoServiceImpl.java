package src.service;

import src.Data;
import src.exception.DuplicateMenuItemException;
import src.exception.DuplicatePromoException;
import src.exception.PromoNotFoundException;
import src.model.entity.MenuItem;
import src.model.entity.Order;
import src.model.entity.Promo;
import src.model.entity.PromoFreebies;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class PromoServiceImpl implements PromoService {
    @Override
    public void create(Promo promo) {
        for (Promo p : Data.promos) {
            if (p.getName().equals(promo.getName())) {
                throw new DuplicatePromoException("Ditemukan promo dengan nama yang sama: " + promo.getName());
            }
        }

        Data.promos.add(promo);
    }

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
    public ArrayList<Promo> getList() {
        return Data.promos;
    }

    @Override
    public ArrayList<Promo> getAvailablePromo() {
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
    public void update(int choice, Promo promo) {
        Data.promos.set(choice, promo);
    }

    @Override
    public void delete(int choice) {
        if (choice < 0 || choice >= Data.promos.size()) {
            throw new IndexOutOfBoundsException("Pilihan invalid: " + choice);
        }

        Data.promos.remove(choice);
    }

    @Override
    public void clearList() {
        Data.promos.clear();
    }

    @Override
    public void resetPromo() {
        for (Promo promo : Data.promos) {
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
