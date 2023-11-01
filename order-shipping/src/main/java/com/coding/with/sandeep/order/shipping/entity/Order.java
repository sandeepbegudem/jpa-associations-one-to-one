package com.coding.with.sandeep.order.shipping.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer"})
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String trackingNumber;
    private String description;
    private Date date;
    private int quantity;
    private double orderValue;
    @OneToOne(targetEntity = Shipping.class, cascade = CascadeType.ALL)
    private Shipping shipping;

}
