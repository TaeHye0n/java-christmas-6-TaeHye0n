package christmas.domain.discount;

import static christmas.domain.discount.Policy.GIFT_DISCOUNT;
import static christmas.domain.discount.Policy.of;

import christmas.domain.User;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DiscountResult {

    private static final int DISCOUNT_THRESHOLD = 10_000;
    private final Map<DiscountPolicy, Integer> discountBenefits;

    public DiscountResult(User user) {
        this.discountBenefits = new LinkedHashMap<>();
        discountEvent(user);
    }


    private void discountEvent(User user) {
        if (user.getTotalPrice() < DISCOUNT_THRESHOLD) {
            return;
        }

        List<DiscountPolicy> discountPolicies =
                of(user.isWeekDay(), user.isSpecialDay(), user.isBeforeChristmas(), user.canTakeGift());
        discountPolicies.stream()
                .forEach(discountPolicy -> discountBenefits.put(discountPolicy, discountPolicy.discount(user)));
    }

    public int getTotalBenefits() {
        return discountBenefits.values().stream().mapToInt(Integer::intValue).sum();
    }

    public int getTotalDiscount() {
        return discountBenefits.entrySet().stream()
                .filter(entry -> !entry.getKey().equals(GIFT_DISCOUNT.getDiscountPolicy()))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

}
