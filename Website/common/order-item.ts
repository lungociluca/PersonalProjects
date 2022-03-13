import { CartItem } from "./cart-item";

export class OrderItem {
    imageUrl: string;
    unitPrice: number;
    qunatity: number;
    productId: number;

    constructor(cartItem: CartItem) {
        this.imageUrl = cartItem.imageUrl;
        this.unitPrice = cartItem.unitPrice;
        this.qunatity = cartItem.quantity;
        this.productId = cartItem.id;
    }
}
