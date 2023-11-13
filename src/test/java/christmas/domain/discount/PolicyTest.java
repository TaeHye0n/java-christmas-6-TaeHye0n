package christmas.domain.discount;

import static christmas.domain.discount.Policy.CHRISTMAS_DISCOUNT;
import static christmas.domain.discount.Policy.GIFT_DISCOUNT;
import static christmas.domain.discount.Policy.SPECIAL_DISCOUNT;
import static christmas.domain.discount.Policy.WEEKEND_DISCOUNT;
import static christmas.domain.discount.Policy.WEEK_DAY_DISCOUNT;
import static christmas.domain.discount.Policy.of;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class PolicyTest {

    @Test
    void 주말_특별_크리스마스_선물_할인_테스트() {
        //when
        List<DiscountPolicy> discountPolicies = of(false, true, true, true);

        //then
        assertThat(discountPolicies).contains(
                WEEKEND_DISCOUNT.getDiscountPolicy(),
                SPECIAL_DISCOUNT.getDiscountPolicy(),
                CHRISTMAS_DISCOUNT.getDiscountPolicy(),
                GIFT_DISCOUNT.getDiscountPolicy()
        );
    }

    @Test
    void 주중_특별_할인_테스트() {
        //when
        List<DiscountPolicy> discountPolicies = of(true, true, false, false);

        //then
        assertThat(discountPolicies).contains(WEEK_DAY_DISCOUNT.getDiscountPolicy(),
                SPECIAL_DISCOUNT.getDiscountPolicy());
    }

    @Test
    void 주중_할인_테스트() {
        //when
        List<DiscountPolicy> discountPolicies = of(true, false, false, false);

        //then
        assertThat(discountPolicies).contains(WEEK_DAY_DISCOUNT.getDiscountPolicy());
    }

    @Test
    void 주말_할인_테스트() {
        //when
        List<DiscountPolicy> discountPolicies = of(false, false, false, false);

        //then
        assertThat(discountPolicies).contains(WEEKEND_DISCOUNT.getDiscountPolicy());
    }

    @Test
    void 크리스마스_할인_테스트() {
        //when
        List<DiscountPolicy> discountPolicies = of(false, false, true, false);

        //then
        assertThat(discountPolicies).contains(CHRISTMAS_DISCOUNT.getDiscountPolicy());
    }

    @Test
    void 선물_할인_테스트() {
        //when
        List<DiscountPolicy> discountPolicies = of(false, false, false, true);

        //then
        assertThat(discountPolicies).contains(GIFT_DISCOUNT.getDiscountPolicy());
    }

}