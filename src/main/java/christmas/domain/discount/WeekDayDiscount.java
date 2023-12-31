package christmas.domain.discount;

import christmas.domain.Menu;
import christmas.domain.User;

public class WeekDayDiscount implements DiscountPolicy {

    private static final int DISCOUNT_AMOUNT = 2023;

    @Override
    public int discount(User user) {
        int count = user.getCountByMenuType(Menu::isDessert);
        return count * DISCOUNT_AMOUNT;
    }
}
