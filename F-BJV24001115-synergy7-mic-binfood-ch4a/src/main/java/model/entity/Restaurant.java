package src.main.java.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import src.main.java.Data;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Restaurant {
    private int id;
    private String name;
    private String location;
    private boolean open;
    private User owner;

    public List<MenuItem> getMenuItemList() {
        return Data.MENU_ITEMS.stream()
                .filter(i -> i.getRestaurant().getId() == this.id)
                .toList();
    }
}
