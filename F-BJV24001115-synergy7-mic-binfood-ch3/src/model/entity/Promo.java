package src.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class Promo {
    private int id;
    private Restaurant restaurant;
    private String name;
    private String description;
    private boolean availability;
    private LocalDate validStartDate;
    private LocalDate validEndDate;
    private LocalTime validStartTime;
    private LocalTime validEndTime;
    private List<MenuItem> eligibleMenu;

    // CONSTRUCTOR
    protected Promo(int id, Restaurant restaurant, String name, String description) {
        this.id = id;
        this.restaurant = restaurant;
        this.name = name;
        this.description = description;
        availability = true;
    }
}
