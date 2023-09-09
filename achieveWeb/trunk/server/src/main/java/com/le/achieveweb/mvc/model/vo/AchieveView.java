package com.le.achieveweb.mvc.model.vo;
import lombok.*;

import java.time.LocalDateTime;

@Data
public class AchieveView {
    private String itemName;
    private String categoryName;
    private String tagName;
    private int reachQuantity;
    private LocalDateTime reachDate;
    private LocalDateTime createAt;
}
