package onlineshopping;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import java.util.ArrayList;


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
  private DiscountService discountService;
  private ArrayList<CartItem> cartItems;
  private CustomerType customerType;
  private Product laptop;
  private Product mouse;
  private Product monitor;
  private CartItem laptopItem;
  private CartItem mouseItem;
  private CartItem monitorItem;

  @BeforeEach
  public void setUp() {
    discountService = new DiscountService();
    laptop = new Product("Laptop", 1000.0, 50);
    mouse = new Product("Mouse", 50.0, 50);
    monitor = new Product("Monitor", 500.0, 50);
    cartItems = new ArrayList<>();
  }

  @Test // No Discounts Applied
  public void testDiscountService_NoDiscounts(){
    monitorItem = new CartItem(monitor, 1);
    cartItems.add(monitorItem);
    customerType = CustomerType.REGULAR;

    assertEquals(500.0, discountService.applyDiscount(500.0, customerType, cartItems, null));
  }

  @Test // 50Coupon + VIP
  public void testDiscountService_50CouponVIP(){
    monitorItem = new CartItem(monitor, 1);
    cartItems.add(monitorItem);
    customerType = CustomerType.VIP;

    double initialTotal = 500.0;
    double afterCoupon = 450.0; // $50 off with SAVE50
    double discount = 0.15; // 15% off for VIP
    double expectedTotal = afterCoupon * (1 - discount); 
    assertEquals(expectedTotal, discountService.applyDiscount(initialTotal, customerType, cartItems, "SAVE50"));
  }

  @Disabled("Failing test: Bundle discount applied to wrong item.")
  @Test // Bundle Discount + 50Coupon + Premium
  public void testDiscountService_Bundle50CouponPremium(){
    laptopItem = new CartItem(laptop, 1);
    mouseItem = new CartItem(mouse, 1);
    cartItems.add(laptopItem);
    cartItems.add(mouseItem);
    customerType = CustomerType.PREMIUM;

    double initialTotal = 1050.0; // 1000 + 50
    double afterBundle = 1045.0; // 10% off mouse = $5.0 discount
    double afterCoupon = 995.0; // $50 off with SAVE50
    double discount = 0.1; // 10% off for PREMIUM
    double expectedTotal = afterCoupon * (1 - discount); 
    assertEquals(expectedTotal, discountService.applyDiscount(initialTotal, customerType, cartItems, "SAVE50"));
  }

  @Disabled("Failing test: Bundle discount applied to wrong item.")
  @Test // Bundle Discount + 50Coupon + VIP + Tiered
  public void testDiscountService_Bundle50CouponVIPTiered(){
    monitorItem = new CartItem(monitor, 8); // 4000
    laptopItem = new CartItem(laptop, 4); // 4000
    mouseItem = new CartItem(mouse, 4);   // 200
    cartItems.add(laptopItem);
    cartItems.add(mouseItem);
    cartItems.add(monitorItem);
    customerType = CustomerType.VIP;

    double initialTotal = 8200.0; // 4000 + 4000 + 200
    double afterBundle = 8180.0; // 10% off 4 mice = $20.0 discount
    double afterCoupon = 8130.0; // $50 off with SAVE50
    double discount = 0.20 + 0.15; // 35% off for Tiered 20% + VIP 15%
    double expectedTotal = afterCoupon * (1 - discount);
    assertEquals(expectedTotal, discountService.applyDiscount(initialTotal, customerType, cartItems, "SAVE50"));
  }

  // ===== Percentage Based Discounts are added together then applied on the reduced total =====

  // @Disabled("Failing test: Bundle discount applied to wrong item.")
  // @Test // Bundle Discount + 10% Coupon + VIP + Tiered + Time-limited (Max percentage)
  // public void testDiscountService_maxPercentageDiscount(){
  //   monitorItem = new CartItem(monitor, 16); // 8000
  //   laptopItem = new CartItem(laptop, 8); // 8000
  //   mouseItem = new CartItem(mouse, 4);   // 200
  //   cartItems.add(laptopItem);
  //   cartItems.add(mouseItem);
  //   cartItems.add(monitorItem);
  //   customerType = CustomerType.VIP;

  //   double initialTotal = 16200.00; // 8000 + 8000 + 200
  //   double afterBundle = 16180.0 ; // 10% off 4 mice = $20.0 discount
  //   double discount = 0.25 + 0.15 + 0.10 + 0.25; // 70% off, 0.25 tiered + 0.15 VIP + 0.10 coupon + 0.25 time-limited
  //   double expectedTotal = afterBundle * (1 - discount);
  //   double totalBeforeTimeLimited = discountService.applyDiscount(initialTotal, customerType, cartItems, "DISCOUNT10");
  //   assertEquals(expectedTotal, discountService.applyPromotionDiscount(totalBeforeTimeLimited));
  // }

  // @Disabled("Failing test: Promotion discount not added correctly.")
  // @Test // 10% Coupon + VIP + Tiered + Time-limited (Max percentage)
  // public void testDiscountService_MaxPercentageDiscount_NoBundle(){
  //   monitorItem = new CartItem(monitor, 50); // 25000
  //   cartItems.add(monitorItem);
  //   customerType = CustomerType.VIP;

  //   double initialTotal = 25000.00; // 25000
  //   double discount = 0.25 + 0.15 + 0.10 + 0.25; // 70% off, 0.25 tiered + 0.15 VIP + 0.10 coupon + 0.25 time-limited
  //   double expectedTotal = initialTotal * (1 - discount);
  //   double totalBeforeTimeLimited = discountService.applyDiscount(initialTotal, customerType, cartItems, "DISCOUNT10");
  //   assertEquals(expectedTotal, discountService.applyPromotionDiscount(totalBeforeTimeLimited));
  // }

  @Test // Max percentage, no bundle, no time limited
  public void testDiscountService_MaxPercentageDiscount_NoBundleNoPromo(){
    monitorItem = new CartItem(monitor, 50); // 25000
    cartItems.add(monitorItem);
    customerType = CustomerType.VIP;

    double initialTotal = 25000.00; // 25000
    double discount = 0.25 + 0.15 + 0.10; // 50% off, 0.25 tiered + 0.15 VIP + 0.10 coupon
    double expectedTotal = initialTotal * (1 - discount);
    assertEquals(expectedTotal, discountService.applyDiscount(initialTotal, customerType, cartItems, "DISCOUNT10"));
  }

}
