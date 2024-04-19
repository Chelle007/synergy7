package src.test.java.service;

import src.main.java.Data;
import src.main.java.model.entity.*;
import src.main.java.service.OrderService;
import src.main.java.service.OrderServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServiceImplTest {
    OrderService os;
    Order order;

    @BeforeEach
    void init() {
        os = new OrderServiceImpl();
        Restaurant restaurant = Data.restaurant;
        User customer = Data.customer;
        order = new Order(restaurant, customer);
        Data.ORDERS.clear();
        Data.ORDERS.add(order);

        MenuItem menuItem = new MenuItem("Pizza", MenuItem.FoodType.FOOD, 7000, Data.restaurant);
        OrderDetail orderDetail = new OrderDetail("M", 1, menuItem, order);
        Data.ORDER_DETAILS.clear();
        Data.ORDER_DETAILS.add(orderDetail);
    }

    @Test
    void add() {
        int previousSize = Data.ORDERS.size();
        os.add(order);

        assertEquals(previousSize+1, Data.ORDERS.size());
    }

    @Test
    void createNotes() {
        String notes = "Hello";
        os.createNotes(order, notes);

        assertEquals("Hello", Data.ORDERS.getFirst().getNotes());
    }

    @Test
    void getTotalPrice() {
        int totalPrice = os.getTotalPrice(order);

        assertEquals(7000, totalPrice);
    }

    @Test
    void getTotalQty() {
        int totalQty = os.getTotalQty(order);

        assertEquals(1, totalQty);
    }

    @Test
    void getListString() {
        String expected = "1. Pizza (M)        1       7.000" + System.lineSeparator();
        String list = os.getListString(order, false);

        assertEquals(expected, list);
    }

    @Test
    void getTotalListString() {
        String expected = """
                1. Pizza (M)        1       7.000
                -----------------------------------+
                Total               1       7.000
                """;
        String totalList = os.getTotalListString(order, false);

        assertEquals(expected, totalList);
    }

    @Test
    void getReceipt() {
        String expected = String.format("""
                
                ===============================================
                                   BinarFud                  \s
                ===============================================
                Waktu : %s
                Order : 1
                
                Terima kasih sudah memesan di BinarFud
                
                Di bawah ini adalah pesanan anda
                
                1. Pizza (M)        1       7.000
                -----------------------------------+
                Total               1       7.000
                Pembayaran : BinarCash
                
                ** Simpan struk ini sebagai bukti pembayaran **""", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        String receipt = os.getReceipt(order, false, 1);

        assertEquals(expected, receipt);
    }

    @Test
    void clearList() {
        os.clearList();

        assertEquals(0, Data.ORDERS.size());
    }

    @Test
    void clearNotes() {
        order.setNotes("hello");
        os.clearNotes(order);

        assertEquals("", order.getNotes());
    }
}
