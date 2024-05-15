//package com.example.binarfud.controller;
//
//import com.example.binarfud.model.entity.*;
//import com.example.binarfud.service.*;
//import com.example.binarfud.view.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
//import static com.example.binarfud.util.ColorUtils.COLOR_OF_ERROR;
//import static com.example.binarfud.view.ValidationView.checkInt;
//
//@Controller
//public class OrderDetailController {
//    @Autowired OrderDetailService orderDetailService;
//    @Autowired BasicView basicView;
//
//    public int askOrderChoice(Order order) {
//        do {
//            int choice = checkInt("Nomor order: ");
//            if (choice <= orderDetailService.getByOrder(order).size() && choice > 0) {
//                return choice;
//            } else {
//                basicView.printlnColor("Pilihan tidak valid.", COLOR_OF_ERROR);
//            }
//        } while (true);
//    }
//}
