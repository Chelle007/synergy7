package com.example.binarfud.model.dto.order;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderCompleteRequestDto {
    private String destinationAddress;
    private String notes;
}
