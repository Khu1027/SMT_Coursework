package onlineshopping;
import java.util.List;

public class DiscountService {

    // Apply promotional discounts (e.g., Black Friday, flat 25% off)
    public double applyPromotionDiscount(double total) {
        return total * 0.75; // 25% off
    }

    // Apply tiered, customer-specific, bundle, and coupon discounts
    public double applyDiscount(double total, CustomerType customerType, List<CartItem> cartItems, String couponCode) {
        double discount = 0.0;

        // Apply bundle discounts (e.g., buy laptop + mouse, 10% off mouse)
        for (CartItem item : cartItems) {
            if (!item.getProduct().getName().equalsIgnoreCase("Mouse")) {
                boolean hasLaptop = cartItems.stream()
                        .filter(i -> i.getProduct().getName().equalsIgnoreCase("Laptop"))
                        .count() >= 1;
                if (hasLaptop) {
                    total -= item.getProduct().getPrice() * 0.10;
                }
            }
        }

        // Apply multi-tier discount based on cart value
        if (total > 15000) {
            discount = 0.25; // 25% discount for carts over 15000
        } else if (total > 7000) {
            discount = 0.20; // 20% discount for carts over 7000
        } else if (total > 1000) {
            discount = 0.15; // 15% discount for carts over 1000
        }

        // Apply customer-specific discounts
        if (customerType == CustomerType.PREMIUM) {
            discount += 0.20; // Additional 20% for premium customers
        } else if (customerType == CustomerType.VIP) {
            discount += 0.15; // Additional 15% for VIP customers
        }

        // Apply coupon code discounts, only if a valid coupon code is provided
        if (couponCode != null && !couponCode.isEmpty()) {
            // Example: If coupon code is "DISCOUNT10", give 10% off
            if (couponCode.equals("DISCOUNT10")) {
                discount += 0.10;
            } else if (couponCode.equals("SAVE50")) {
                total -= 50; // Fixed amount discount of 50
            }
        }

        return total * (1 - discount);
    }
}
