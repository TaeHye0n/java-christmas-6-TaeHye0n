package christmas.domain.discount.enums;

import christmas.domain.discount.ChristmasDiscount;
import christmas.domain.discount.DiscountPolicy;
import christmas.domain.discount.GiftDiscount;
import christmas.domain.discount.SpecialDiscount;
import christmas.domain.discount.WeekDayDiscount;
import christmas.domain.discount.WeekendDiscount;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Policy {

    WEEK_DAY_DISCOUNT(new WeekDayDiscount(), true, false, false, false),
    WEEKEND_DISCOUNT(new WeekendDiscount(), false, false, false, false),
    SPECIAL_DISCOUNT(new SpecialDiscount(), false, true, false, false),
    CHRISTMAS_DISCOUNT(new ChristmasDiscount(), false, false, true, false),
    GIFT_DISCOUNT(new GiftDiscount(), false, false, false, true);

    private final DiscountPolicy discountPolicy;
    private final boolean isWeekDay;
    private final boolean isSpecialDay;
    private final boolean isBeforeChristmas;
    private final boolean canTakeGift;

    Policy(
            DiscountPolicy discountPolicy,
            boolean isWeekDay,
            boolean isSpecialDay,
            boolean isBeforeChristmas,
            boolean canTakeGift
    ) {
        this.discountPolicy = discountPolicy;
        this.isWeekDay = isWeekDay;
        this.isSpecialDay = isSpecialDay;
        this.isBeforeChristmas = isBeforeChristmas;
        this.canTakeGift = canTakeGift;
    }

    public static List<DiscountPolicy> of(
            boolean isWeekDay,
            boolean isSpecialDay,
            boolean isBeforeChristmas,
            boolean canTakeGift
    ) {
        return Arrays.stream(values())
                .filter(policy -> policy.isMatch(isWeekDay, isSpecialDay, isBeforeChristmas, canTakeGift))
                .map(Policy::getDiscountPolicy)
                .collect(Collectors.toList());
    }

    public DiscountPolicy getDiscountPolicy() {
        return discountPolicy;
    }

    private boolean isMatch(boolean isWeekDay, boolean isSpecialDay, boolean isBeforeChristmas, boolean canTakeGift) {
        if (this.isSpecialDay) {
            return isSpecialDay;
        }

        if (this.isBeforeChristmas) {
            return isBeforeChristmas;
        }

        if (this.canTakeGift) {
            return canTakeGift;
        }

        return this.isWeekDay == isWeekDay;
    }
}
