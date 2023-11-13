package christmas.domain;

public class WeekDayDiscount implements DiscountPolicy {

    private static final int discountAmount = 2023;

    @Override
    public int discount(User user) {
        int count = user.getCountByMenuType(Menu::isDessert);
        return count * discountAmount;
    }
}
