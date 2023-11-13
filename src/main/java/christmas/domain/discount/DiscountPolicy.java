package christmas.domain.discount;

import christmas.domain.User;

public interface DiscountPolicy {

    int discount(User user);
}
