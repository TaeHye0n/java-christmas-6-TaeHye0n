package christmas.domain.event;

import static christmas.domain.discount.enums.Policy.GIFT_DISCOUNT;
import static christmas.domain.discount.enums.Policy.of;

import christmas.domain.User;
import christmas.domain.discount.DiscountPolicy;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EventResult {

    private static final int DISCOUNT_THRESHOLD = 10_000;
    private final Map<DiscountPolicy, Integer> discountBenefits;

    public EventResult(User user) {
        this.discountBenefits = new LinkedHashMap<>();
        event(user);
    }


    private void event(User user) {
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

    public boolean hasGift() {
        return discountBenefits.containsKey(GIFT_DISCOUNT.getDiscountPolicy());
    }

    public Map<DiscountPolicy, Integer> getDiscountBenefits() {
        return Collections.unmodifiableMap(discountBenefits);
    }

    public String getEventBadge() {
        return EventBadge.from(getTotalBenefits()).getBadgeName();
    }
}
