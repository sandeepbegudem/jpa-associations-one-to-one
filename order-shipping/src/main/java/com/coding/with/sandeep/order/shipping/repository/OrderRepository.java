package com.coding.with.sandeep.order.shipping.repository;

import com.coding.with.sandeep.order.shipping.entity.Shipping;
import com.coding.with.sandeep.order.shipping.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.trackingNumber = :trackingNumber, o.description = :description, o.date = :date, o.quantity = :quantity, o.orderValue = :orderValue, o.shipping = :shipping " +
            "WHERE o.id = :id") //Todo child table Shipping needs to be also updated with the parent table Order
    void updateOrder(@Param("id") Integer id,
                     @Param("trackingNumber") String trackingNumber,
                     @Param("description") String description,
                     @Param("date") Date date,
                     @Param("quantity") int quantity,
                     @Param("orderValue") double orderValue,
                     @Param("shipping") Shipping shipping);
}
