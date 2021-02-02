package com.udacity.course3.repo.projection;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter @Getter
public class RecipientAndPrice {
    String name;
    private BigDecimal price;

    public RecipientAndPrice(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}
