package util;

import java.util.ArrayList;

import model.entity.MenuItem;
import model.entity.Order;
import model.entity.Promo;

import static util.InitializationUtils.*;

public class PromoUtils {
    private static final ArrayList<Order> orders = getOrders();
    private static final ArrayList<Promo> promos = getPromos();
    private static final ArrayList<Order> freebiesOrder = getFreebiesOrder();

    private PromoUtils() {
    }

    // Method untuk menghitung promo
    public static void countPromo() {
        for (Promo promo : promos) {
            if (promo.getType() == Promo.PromoType.FREEBIES) {
                ArrayList<MenuItem> eligibleMenu = promo.getEligibleMenu();

                int count = 0;
                for (Order order : orders) {
                    if (eligibleMenu.contains(order.getMenuItem())) {
                        count += order.getQty();
                    }
                }
                count /= promo.getRequiredMenuItemCount();

                if (count > 0) {
                    Order freebies = promo.getFreeMenuItem();
                    freebies.setQty(count);
                    freebiesOrder.add(freebies);
                }
            }
        }
    }

    // Method untuk ulang hitung promo
    public static void restartPromo() {
        for (Promo promo : promos) {
            if (promo.getType() == Promo.PromoType.FREEBIES) {
                freebiesOrder.clear();
            }
            countPromo();
        }
    }
}
