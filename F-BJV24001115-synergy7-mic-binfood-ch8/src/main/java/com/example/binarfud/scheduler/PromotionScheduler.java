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

    @Scheduled(cron = "0 0 12 * * *")
    public void cronJob() throws ExecutionException, InterruptedException {
        NotificationRequest request = new NotificationRequest();
        request.setTitle("Afternoon Binarfud Promo");
        request.setBody("Order at 12.00-13.00 to get 20% discount");
        request.setToken("fheZoT6OtNIqjaoBDIqTGB:APA91bHeksg2XYFFQIpohRw9JGTJ2Tq5ssKzQzHjDY0eRkDg9CxXRqq8Mae-aA9yqq8YzEjZndddf84suFMvmmtIsrh_0QOkCsa7xuZmjmw_CG-mFgPLY8o2CtY2zuo8zvI4InibM_ns");
        System.out.println("Sending...");
        fcmService.sendMessageToToken(request);
    }
}