package com.udacity.course3.entity.inventory;


import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.course3.entity.delivery.Delivery;
import com.udacity.course3.view.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "plant")
@Getter @Setter @NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Plant {
    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    @JsonView(Views.Public.class)
    private String name;

    @Column(precision = 12, scale = 4)
    @JsonView(Views.Public.class)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
}
