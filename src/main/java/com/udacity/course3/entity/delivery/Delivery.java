package com.udacity.course3.entity.delivery;

import com.udacity.course3.entity.inventory.Plant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NamedQuery(
        name = "Delivery.findByName",
        query = "select d from Delivery d " +
                "where d.recipientName = :recipientName"
)

@Entity
@Getter @Setter @NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue
    private Long id;
    @Nationalized
    @Column(name = "recipient_name")
    private String recipientName;
    @Column(name = "address_full", length = 500)
    private String address;
    private LocalDateTime deliveryTime;
    @Type(type = "yes_no")
    private Boolean isCompleted = false;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Plant> plants;
}
