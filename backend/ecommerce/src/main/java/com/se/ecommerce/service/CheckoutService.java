package com.se.ecommerce.service;

import com.se.ecommerce.dto.Purchase;
import com.se.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
