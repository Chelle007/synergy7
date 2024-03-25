package model.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Promo {
    private String name;
    private PromoType type;
    private String description;
    private boolean availability;
    private LocalDate validStartDate;
    private LocalDate validEndDate;
    private LocalTime validStartTime;
    private LocalTime validEndTime;
    private ArrayList<MenuItem> eligibleMenu;

    // field for freebies type
    private Order freeMenuItem;
    private int requiredMenuItemCount;

    public enum PromoType {
        COMBO,
        DISCOUNT,
        FREEBIES
    }

    // CONSTRUCTOR
    public Promo(String name, PromoType type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
        availability = true;
    }
}
