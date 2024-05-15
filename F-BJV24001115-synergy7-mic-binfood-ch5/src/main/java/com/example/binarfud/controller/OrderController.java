//package com.example.binarfud.controller;
//
//import com.example.binarfud.model.entity.*;
//import com.example.binarfud.service.*;
//import com.example.binarfud.view.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.time.LocalDateTime;
//
//import static com.example.binarfud.util.AdditionalUtils.formatTime;
//import static com.example.binarfud.util.ColorUtils.COLOR_OF_ERROR;
//
//@Controller
//public class OrderController {
//    @Autowired BasicView basicView;
//    @Autowired OrderService orderService;
//
//    public String displayTotalOrderList(Order order) {
//        return orderService.getTotalListString(order, true);
//    }
//
//    public String displayReceipt(Order order) {
//        int count = 1;
//        try {
//            File receiptFile = new File(String.format("receipt(%s-%04d).txt", formatTime("yyyy-MMdd", LocalDateTime.now()), count));
//            while (receiptFile.exists()) {
//                count++;
//                receiptFile = new File(String.format("receipt(%s-%04d).txt", formatTime("yyyy-MMdd", LocalDateTime.now()), count));
//            }
//
//            PrintWriter receipt = new PrintWriter(receiptFile);
//            receipt.println(orderService.getReceipt(order, false, count));
//            receipt.close();
//        } catch (IOException e) {
//            basicView.printlnColor("Eror saat menghasilkan struk dalam file text.", COLOR_OF_ERROR);
//        }
//
//        return orderService.getReceipt(order, true, count);
//    }
//}
