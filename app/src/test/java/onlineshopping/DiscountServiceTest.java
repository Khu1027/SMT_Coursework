package onlineshopping;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

/*
 * Unit tests for the DiscountService class.
 * Disounts are applied in the following order
 * 1. Bundle Discounts
 * 2. Fixed Amount Coupons
 * 3. Percentage Based Discounts (Tiered, Customer-type, Percentage Coupons, Time-limited Coupons)
 * 
 * Bundle Discounts: 10% off mouse, for each mouse-laptop pair in the cart
 * Fixed Amount Coupons: DISCOUNT10 for 10% off, SAVE50 for $50 off (only 1 coupon per order)
 * Percentage Based Discounts:
 *  Tiered Discounts: 15% off for carts > 2000, 20% off for cards > 7000, 25% off for carts > 15000
 *  Customer-type Discounts: 10% off for PREMIUM customers, 15% off for VIP customers
 *  Time-limited Coupons: 25% off during promotional periods
 * 
 * How to calculate final price:
 * 1. Start with the total cart value
 * 2. Apply bundle discounts first, reducing the total cart value
 * 3. Apply fixed amount coupons next, reducing the total cart value
 * 4. Finally, apply all percentage-based discounts on the reduced total cart value
 * (Percentage based discounts are added together e.g. 15% tiered + 10% premium = 25% total)
 *  
 */
public class DiscountServiceTest {
    
}
