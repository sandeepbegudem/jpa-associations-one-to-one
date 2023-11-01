package com.coding.with.sandeep.order.shipping.dto;

import com.coding.with.sandeep.order.shipping.entity.Shipping;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class OrderShippingDTO {

    private Integer id;
    private String trackingNumber;
    private String description;
    private Date date;
    private int quantity;
    private double orderValue;
    private Shipping shipping;
}
