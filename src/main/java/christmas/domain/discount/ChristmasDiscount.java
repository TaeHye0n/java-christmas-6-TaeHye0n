package christmas.domain.discount;

import christmas.domain.User;

public class ChristmasDiscount implements DiscountPolicy {

    private static final int initialAmount = 1000;

    private static final int discountAmount = 100;

    @Override
    public int discount(User user) {
        return initialAmount + user.getDay().getDaysUntilChristmas() * discountAmount;
    }
}
