package src.service;

import src.model.entity.Promo;

import java.util.ArrayList;

public interface PromoService {
    // CREATE
    void create(Promo promo);

    // READ
    Promo get(int choice);
    ArrayList<Promo> getList();
    ArrayList<Promo> getAvailablePromo();

    // UPDATE
    void update(int choice, Promo promo);

    // DELETE
    void delete(int choice);
    void clearList();

    // ADDITIONAL
    void resetPromo();
}
