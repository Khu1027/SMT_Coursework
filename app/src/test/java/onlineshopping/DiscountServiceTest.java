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

    /*
     * Test bundle discount
     * 1. one mouse laptop pair
     * 2. two mouse laptop pairs
     * 3. one laptop, two mice
     */

     /*
      * Test fixed amount coupons
      * 1. DISCOUNT10 coupon is correctly applied
      * 2. SAVE50 coupon is correctly applied
      * 3. No coupon applied
      * 5. invalid coupon code = error
      */

    /*
     * Test Tiered Discounts
     * 1. Cart value 1500 = no discount
     * 2. Cart value 2000 = no discount
     * 3. Cart value 2001 = 15% discount
     * 4. Cart value 2500 = 15% discount
     * 5. Cart value 7000 = 15% discount
     * 6. Cart value 7001 = 20% discount
     * 7. Cart value 10000 = 20% discount
     * 8. Cart value 15000 = 20% discount
     * 9. Cart value 15001 = 25% discount
     * 10. Cart value 20000 = 25% discount
     */

     /*
      * Test Customer-type Discounts
      * 1. REGULAR customer = no discount
      * 2. PREMIUM customer = 10% discount
      * 3. VIP customer = 15% discount
      */

      /*
       * Time-limited Coupons
       * System is not in promotional period
       * - discount not applied
       * System is in promotional period
       * - discount applied (25% off)
       */
    
}
