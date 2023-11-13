package christmas.domain;

public class WeekendDiscount implements DiscountPolicy {

    private static final int discountAmount = 2023;

    @Override
    public int discount(User user) {
        int count = user.getCountByMenuType(Menu::isMain);
        return count * discountAmount;
    }
}
