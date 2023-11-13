package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Day;
import christmas.domain.User;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class WeekendDiscountTest {

    @Test
    void 주말_할인_테스트() {
        //given
        WeekendDiscount weekendDiscount = new WeekendDiscount();
        Day day = new Day(1);
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("티본스테이크", 2);
        map.put("아이스크림", 2);
        User user = new User(day, map);

        //when
        int discount = weekendDiscount.discount(user);

        //then
        assertThat(discount).isEqualTo(4046);
    }

}