package christmas.domain.discount;

import christmas.domain.User;

public class ChristmasDiscount implements DiscountPolicy {

    private static final int INITIAL_AMOUNT = 1000;
    private static final int DISCOUNT_AMOUNT = 100;
    private static final int D_DAY = 25;


    @Override
    public int discount(User user) {
        return INITIAL_AMOUNT + (D_DAY - user.getDay().getDaysUntilChristmas() - 1) * DISCOUNT_AMOUNT;
    }
}
