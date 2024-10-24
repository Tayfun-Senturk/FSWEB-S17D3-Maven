package com.workintech.zoo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Kangaroo {
    private long id;
    private String name;
    private double height;
    private double weight;
    private String gender;
    private boolean isAggressive;
    public boolean getIsAggressive() {
        return isAggressive;
    }
    public void setIsAggressive(boolean isAggressive) {
        this.isAggressive = isAggressive;
    }
}
