package christmas.domain.discount;

import christmas.domain.Menu;
import christmas.domain.User;

public class WeekendDiscount implements DiscountPolicy {

    private static final int DISCOUNT_AMOUNT = 2023;

    @Override
    public int discount(User user) {
        int count = user.getCountByMenuType(Menu::isMain);
        return count * DISCOUNT_AMOUNT;
    }
}
