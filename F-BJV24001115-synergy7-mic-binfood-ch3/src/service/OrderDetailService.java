package src.service;

import java.util.List;

import src.model.entity.OrderDetail;

public interface OrderDetailService {
    // CREATE
    void create(OrderDetail orderDetail);

    // READ
    OrderDetail get(int choice);
    List<OrderDetail> getList();
    boolean isEmpty();
    int getTotalPrice();
    int getTotalQty();
    String getListString(boolean withColor, List<OrderDetail> orderDetails);
    String getTotalListString(boolean withColor);
    String getReceipt(boolean withColor, int order);

    // UPDATE
    void update(int choice, String size, int qty);

    // DELETE
    void delete(int choice);
    void clearList();
}
