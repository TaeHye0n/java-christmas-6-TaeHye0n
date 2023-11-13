package christmas.domain;

import static christmas.domain.Policy.SPECIAL_DISCOUNT;
import static christmas.domain.Policy.WEEKEND_DISCOUNT;
import static christmas.domain.Policy.WEEK_DAY_DISCOUNT;
import static christmas.domain.Policy.of;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class PolicyTest {

    @Test
    void 주말_특별_할인_테스트() {
        //when
        List<DiscountPolicy> discountPolicies = of(false, true);

        //then
        assertThat(discountPolicies).
                contains(WEEKEND_DISCOUNT.getDiscountPolicy(), SPECIAL_DISCOUNT.getDiscountPolicy());
    }

    @Test
    void 주중_특별_할인_테스트() {
        //when
        List<DiscountPolicy> discountPolicies = of(true, true);

        //then
        assertThat(discountPolicies).
                contains(WEEK_DAY_DISCOUNT.getDiscountPolicy(), SPECIAL_DISCOUNT.getDiscountPolicy());
    }

    @Test
    void 주중_할인_테스트() {
        //when
        List<DiscountPolicy> discountPolicies = of(true, false);

        //then
        assertThat(discountPolicies).
                contains(WEEK_DAY_DISCOUNT.getDiscountPolicy());
    }

    @Test
    void 주말_할인_테스트() {
        //when
        List<DiscountPolicy> discountPolicies = of(false, false);

        //then
        assertThat(discountPolicies).
                contains(WEEKEND_DISCOUNT.getDiscountPolicy());
    }

}