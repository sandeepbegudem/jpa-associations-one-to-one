package com.coding.with.sandeep.order.shipping.controller;

import com.coding.with.sandeep.order.shipping.dto.OrderShippingDTO;
import com.coding.with.sandeep.order.shipping.entity.Order;
import com.coding.with.sandeep.order.shipping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

//    public OrderController(OrderService orderService) {
//        this.orderService = orderService;
//    }

    @PostMapping("/insert")
    public ResponseEntity<Order> saveOrder(@RequestBody OrderShippingDTO orderShippingDTO){
        return new ResponseEntity<>(orderService.saveOrder(orderShippingDTO), HttpStatus.CREATED);
    }

    @GetMapping("/all-orders")
    public ResponseEntity<List<OrderShippingDTO>> getAllOrders(){
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderShippingDTO> getOrder(@PathVariable int id){
        return new ResponseEntity<>(orderService.getOrderDetails(id), HttpStatus.OK);
    }

    @PutMapping("/update-orders")
    public ResponseEntity<Order> getAllOrders(@RequestBody OrderShippingDTO orderShippingDTO){
      return new ResponseEntity<>(orderService.updateOrder(orderShippingDTO), HttpStatus.OK);
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update-orders-jpql")
    public void getAllOrdersJpql(@RequestBody OrderShippingDTO orderShippingDTO){
        orderService.updateOrderUsingCustomQuery(orderShippingDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteOrderById(@PathVariable int id){
        orderService.deleteOrder(id);
    }
}
