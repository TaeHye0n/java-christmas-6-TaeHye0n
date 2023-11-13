package christmas.domain.discount;

import christmas.domain.User;

public class GifDiscount implements DiscountPolicy {

    private static final int GIFT_PRICE = 25_000;

    @Override
    public int discount(User user) {
        return GIFT_PRICE;
    }
}
