package com.le.achieveweb.mvc.model.entity;
import lombok.*;
import java.time.LocalDateTime;
@Data
public class AchieveItemRecord {
    private String id;
    private String userId;
    private String ItemId;
    private int reachQuantity;
    private LocalDateTime reachDate;
    private LocalDateTime createAt;
}
