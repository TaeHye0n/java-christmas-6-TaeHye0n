package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Day;
import christmas.domain.User;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class ChristmasDiscountTest {

    @Test
    void 크리스마스_디데이_할인_테스트() {
        //given
        ChristmasDiscount christmasDiscount = new ChristmasDiscount();
        Day day = new Day(1);
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("해산물파스타", 1);
        User user = new User(day, map);

        //when
        int discount = christmasDiscount.discount(user);

        //then
        assertThat(discount).isEqualTo(1000);
    }

}