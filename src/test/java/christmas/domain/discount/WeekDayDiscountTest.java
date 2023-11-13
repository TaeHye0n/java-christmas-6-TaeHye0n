package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Day;
import christmas.domain.User;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class WeekDayDiscountTest {

    @Test
    void 주중_할인_테스트() {
        //given
        WeekDayDiscount weekDayDiscount = new WeekDayDiscount();
        Day day = new Day(1);
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("초코케이크", 1);
        map.put("아이스크림", 2);
        User user = new User(day, map);

        //when
        int discount = weekDayDiscount.discount(user);

        //then
        assertThat(discount).isEqualTo(6069);
    }
}