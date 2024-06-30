package com.example.binarfud.service;

import com.example.binarfud.model.dto.menuItem.MenuItemDto;
import com.example.binarfud.model.entity.MenuItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {
    public void sendFCM(String promoList){
        //todo: build message
        //todo: kirim notifikasi

        System.out.println("Promotion sent to FCM");
    }
    public void sendToMail(){

    }

}
