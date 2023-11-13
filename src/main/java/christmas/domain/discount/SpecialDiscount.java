package christmas.domain.discount;

import christmas.domain.User;

public class SpecialDiscount implements DiscountPolicy {

    private static final int discountAmount = 1000;

    @Override
    public int discount(User user) {
        return discountAmount;
    }
}
