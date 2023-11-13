package christmas.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Policy {

    WEEK_DAY_DISCOUNT(new WeekDayDiscount(), true, false),
    WEEKEND_DISCOUNT(new WeekendDiscount(), false, false),
    SPECIAL_DISCOUNT(new SpecialDiscount(), false, true);

    private final DiscountPolicy discountPolicy;
    private final boolean isWeekDay;
    private final boolean isSpecialDay;

    Policy(DiscountPolicy discountPolicy, boolean isWeekDay, boolean isSpecialDay) {
        this.discountPolicy = discountPolicy;
        this.isWeekDay = isWeekDay;
        this.isSpecialDay = isSpecialDay;
    }

    public static List<DiscountPolicy> of(boolean isWeekDay, boolean isSpecialDay) {
        return Arrays.stream(values())
                .filter(policy -> policy.isMatch(isWeekDay, isSpecialDay))
                .map(Policy::getDiscountPolicy)
                .collect(Collectors.toList());
    }

    public DiscountPolicy getDiscountPolicy() {
        return discountPolicy;
    }

    private boolean isMatch(boolean isWeekDay, boolean isSpecialDay) {
        if (this.isSpecialDay) {
            return isSpecialDay;
        }
        return this.isWeekDay == isWeekDay;
    }
}
