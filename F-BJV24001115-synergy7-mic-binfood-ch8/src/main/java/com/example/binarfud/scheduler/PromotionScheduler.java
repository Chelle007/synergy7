package com.example.binarfud.scheduler;

import com.example.binarfud.model.dto.notification.NotificationRequest;
import com.example.binarfud.service.FCMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class PromotionScheduler {
    @Autowired FCMService fcmService;

    @Scheduled(cron = "0 42 23 * * *")
    public void cronJob() throws ExecutionException, InterruptedException {
        NotificationRequest request = new NotificationRequest();
        request.setTitle("Afternoon Binarfud Promo");
        request.setBody("Order at 12.00-13.00 to get 20% discount");
        request.setToken("esIvv0Fs8PLImqrzqH5PMd:APA91bHjEfHeU8E0NpIo_D1FOt-5S9Xvt8xtkMwONlmWY3npqKTO5iglcTTBEK1I3-d5ezPCMBiqyb--I9yMg7cCangaUvCXI2tBk50D_pk7rGWGM6mt5InsYFzeERG48PJBhDZRZWcz");
        System.out.println("Sending...");
        fcmService.sendMessageToToken(request);
    }
}