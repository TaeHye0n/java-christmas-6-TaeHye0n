package christmas.domain;

import static christmas.global.enums.ErrorMessage.ORDER_INVALID_ERROR;
import static christmas.global.enums.ErrorMessage.ORDER_ONLY_DRINK_ERROR;

import java.util.LinkedHashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void 없는_메뉴_주문_예외_테스트() {
        //given
        Day day = new Day(1);
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("초밥", 1);

        //when and then
        Assertions.assertThatThrownBy(() -> new User(day, map))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ORDER_INVALID_ERROR.getMessage());
    }

    @Test
    void 음료만_주문_예외_테스트() {
        //given
        Day day = new Day(1);
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("제로콜라", 1);

        //when and then
        Assertions.assertThatThrownBy(() -> new User(day, map))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ORDER_ONLY_DRINK_ERROR.getMessage());
    }

    @Test
    void 총_주문_금액_테스트() {
        //given
        Day day = new Day(1);
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("제로콜라", 1);
        map.put("해산물파스타", 2);
        User user = new User(day, map);

        //when
        int totalPrice = user.getTotalPrice();

        //then
        Assertions.assertThat(totalPrice).isEqualTo(73_000);

    }
}