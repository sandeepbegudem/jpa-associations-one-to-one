package com.coding.with.sandeep.order.shipping.service;

import com.coding.with.sandeep.order.shipping.dto.OrderShippingDTO;
import com.coding.with.sandeep.order.shipping.entity.Order;
import com.coding.with.sandeep.order.shipping.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order saveOrder(OrderShippingDTO orderShippingDTO){
        Order order = dtoToEntity(orderShippingDTO);

       return orderRepository.save(order);
    }

    private OrderShippingDTO entityToDto(Order order){

        OrderShippingDTO orderShippingDTO = new OrderShippingDTO();
        return orderShippingDTO
                .builder()
                .id(order.getId())
                .trackingNumber(UUID.randomUUID().toString().replace("-", ""))
                .description(order.getDescription())
                .date(new Date())
                .quantity(order.getQuantity())
                .orderValue(order.getOrderValue())
                .shipping(order.getShipping())
                .build();
    }

    private Order dtoToEntity(OrderShippingDTO orderShippingDTO){

        Order order = new Order();

        return order.builder()
                .id(orderShippingDTO.getId())
                .trackingNumber(UUID.randomUUID().toString().replace("-", ""))
                .description(orderShippingDTO.getDescription())
                .date(new Date())
                .quantity(orderShippingDTO.getQuantity())
                .orderValue(orderShippingDTO.getOrderValue())
                .shipping(orderShippingDTO.getShipping())
                .build();

    }

    public List<OrderShippingDTO> getAllOrders(){

      return orderRepository.findAll()
                .stream()
              .map(this::entityToDto)
              .collect(Collectors.toList());
    }

    public OrderShippingDTO getOrderDetails(int id){

        Order order = orderRepository.findById(id).orElse(null);

        OrderShippingDTO orderShippingDTO = entityToDto(order);
        return orderShippingDTO;
    }

    public void updateOrderUsingCustomQuery(OrderShippingDTO orderShippingDTO){

        if (orderShippingDTO != null){
             Order order123 = orderRepository.findById(orderShippingDTO.getId()).orElse(null);
             if (order123.getId() != null){
                 Order order = dtoToEntity(orderShippingDTO);
                 orderRepository.updateOrder(
                         order.getId(),
                         order.getTrackingNumber(),
                         order.getDescription(),
                         order.getDate(),
                         order.getQuantity(),
                         order.getOrderValue(),
                         order.getShipping()
                 );
             }
        }
        else throw new RuntimeException("body cannot be null!...");
    }

    public Order updateOrder(OrderShippingDTO orderShippingDTO){

        if (orderShippingDTO != null) {

            Order order = dtoToEntity(orderShippingDTO);
            Order savedOrder = orderRepository.save(order);

            return savedOrder;
        }
            else throw new RuntimeException("body cannot be null!...");
    }

    public void deleteOrder(Integer id){
        if(id !=null){
            Order order = orderRepository.findById(id).orElse(null);
            orderRepository.deleteById(order.getId());
        }else throw new RuntimeException("id cannot be null");

    }
}
