package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Day;
import christmas.domain.User;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class DiscountResultTest {

    @Test
    void 총_혜택_금액_테스트() {
        //given
        Day day = new Day(2);
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("티본스테이크", 10);
        map.put("아이스크림", 2);
        User user = new User(day, map);
        DiscountResult discountResult = new DiscountResult(user);

        //when
        int totalBenefits = discountResult.getTotalBenefits();

        //then
        assertThat(totalBenefits).isEqualTo(48_530);
    }

    @Test
    void 만원_미만_할인_없음_테스트() {
        //given
        Day day = new Day(2);
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("아이스크림", 1);
        User user = new User(day, map);
        DiscountResult discountResult = new DiscountResult(user);

        //when
        int totalBenefits = discountResult.getTotalBenefits();

        //then
        assertThat(totalBenefits).isEqualTo(0);
    }

    @Test
    void 총_할인_금액_테스트() {
        //given
        Day day = new Day(2);
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("티본스테이크", 10);
        map.put("아이스크림", 2);
        User user = new User(day, map);
        DiscountResult discountResult = new DiscountResult(user);

        //when
        int totalBenefits = discountResult.getTotalDiscount();

        //then
        assertThat(totalBenefits).isEqualTo(23_530);
    }

}