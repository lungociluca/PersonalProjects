import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';
import { CartItem } from '../common/cart-item';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  cartItems: CartItem[] = [];
  totalPrice: Subject<number> = new BehaviorSubject<number>(0);
  totalQuantity: Subject<number> = new BehaviorSubject<number>(0);

  constructor() { }

  addToCart(theCartItem: CartItem) {
    //check if we already have it in the cart
    if(this.cartItems.length > 0) {
      //find the item in the cart
      for(let tempCartItem of this.cartItems) {
        if(tempCartItem.id == theCartItem.id) {
          tempCartItem.quantity++;
          this.computeCartTotals();
          return;
        }
      }
      
    }

    this.cartItems.push(theCartItem)
    this.computeCartTotals();
  }

  computeCartTotals() {
    let totalPriceValue: number = 0;
    let totalQuantityValue: number = 0;

    for(let currentCartItem of this.cartItems) {
      totalPriceValue += currentCartItem.quantity * currentCartItem.unitPrice;
      totalQuantityValue += currentCartItem.quantity;
    }

    //publish the new values for total price and quantity
    this.totalPrice.next(totalPriceValue);
    this.totalQuantity.next(totalQuantityValue);

    // log cart data
    this.logCartData(totalPriceValue, totalQuantityValue);
  }

  logCartData(totalPriceValue: number, totalQuantityValue: number) {
    console.log('Contents of the cart');
    for(let tempCartItem of this.cartItems) {
      const subtotalPrice = tempCartItem.quantity * tempCartItem.unitPrice;
      console.log('name: ' + tempCartItem.name + ', quantity: ' + tempCartItem.quantity + ', unitPrice= ' + tempCartItem.unitPrice);
    }
    console.log('total price ' +  totalPriceValue.toFixed(2) + " quantity " + totalQuantityValue);
  }
}
