package com.se.ecommerce.dto;

import com.se.ecommerce.entity.Address;
import com.se.ecommerce.entity.Customer;
import com.se.ecommerce.entity.Order;
import com.se.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
