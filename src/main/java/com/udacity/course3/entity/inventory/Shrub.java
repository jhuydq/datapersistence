package com.udacity.course3.entity.inventory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Setter @Getter @NoArgsConstructor
public class Shrub extends Plant{
    private long height;
    private long width;
}
