package src.test.java.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.java.Data;
import src.main.java.model.entity.*;
import src.main.java.service.OrderDetailService;
import src.main.java.service.OrderDetailServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderDetailServiceImplTest {
    OrderDetailService ods;
    MenuItem menuItem;
    Order order;
    OrderDetail orderDetail;

    @BeforeEach
    void init() {
        ods = new OrderDetailServiceImpl();

        Restaurant restaurant = Data.restaurant;
        User customer = Data.customer;

        menuItem = new MenuItem("Pizza", MenuItem.FoodType.FOOD, 6000, 7000, 8000, restaurant);
        order = new Order(restaurant, customer);
        orderDetail = new OrderDetail("M", 1, menuItem, order);

        Data.ORDER_DETAILS.clear();
        Data.ORDER_DETAILS.add(orderDetail);
    }

    @Test
    void create() {
        int previousSize = Data.ORDER_DETAILS.size();

        ods.create(orderDetail);

        assertEquals(previousSize, Data.ORDER_DETAILS.size());
    }

    @Test
    void create2() {
        int previousSize = Data.ORDER_DETAILS.size();
        OrderDetail orderDetail2 = new OrderDetail("S", 2, menuItem, order);

        ods.create(orderDetail2);

        assertEquals(previousSize+1, Data.ORDER_DETAILS.size());
    }

    @Test
    void getByChoice() {
        OrderDetail orderDetail2 = ods.getByChoice(order, 1);

        assertEquals("Pizza", orderDetail2.getMenuItem().getName());
    }

    @Test
    void getByChoice2() {
        Exception e = assertThrows(
                IndexOutOfBoundsException.class, () -> ods.getByChoice(order, 0));

        assertEquals("Pilihan invalid: -1", e.getMessage());
    }

    @Test
    void update() {
        ods.update(order, 1, "M", 2);

        assertEquals(2, Data.ORDER_DETAILS.getFirst().getQty());
    }

    @Test
    void update2() {
        ods.update(order, 1, "S", 2);

        assertEquals("S", Data.ORDER_DETAILS.getFirst().getSize());
    }

    @Test
    void deleteByChoice() {
        ods.deleteByChoice(order, 1);

        assertEquals(0, Data.ORDER_DETAILS.size());
    }

    @Test
    void clearList() {
        ods.clearList(order);

        assertEquals(0, order.getOrderDetailList().size());
    }
}
