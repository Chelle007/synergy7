package src.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Promo {
    private String name;
    private String description;
    private boolean availability;
    private LocalDate validStartDate;
    private LocalDate validEndDate;
    private LocalTime validStartTime;
    private LocalTime validEndTime;
    private ArrayList<MenuItem> eligibleMenu;

    // CONSTRUCTOR
    protected Promo(String name, String description) {
        this.name = name;
        this.description = description;
        availability = true;
    }
}
