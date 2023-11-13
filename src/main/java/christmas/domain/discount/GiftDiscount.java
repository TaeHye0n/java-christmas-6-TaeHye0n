package christmas.domain.discount;

import christmas.domain.Menu;
import christmas.domain.User;

public class GiftDiscount implements DiscountPolicy {

    private static final int GIFT_PRICE = Menu.getGiftMenu().getMenuPrice();

    @Override
    public int discount(User user) {
        return GIFT_PRICE;
    }
}
