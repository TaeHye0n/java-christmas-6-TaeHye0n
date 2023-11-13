package christmas.domain;

public class SpecialDiscount implements DiscountPolicy {

    private static final int discountAmount = 1000;

    @Override
    public int discount(User user) {
        return discountAmount;
    }
}
