package christmas.domain;

import static christmas.global.enums.ErrorMessage.ORDER_COUNT_EXCEED_ERROR;
import static christmas.global.enums.ErrorMessage.ORDER_INVALID_ERROR;
import static christmas.global.enums.ErrorMessage.ORDER_ONLY_DRINK_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void 없는_메뉴_주문_예외_테스트() {
        //given
        Day day = new Day(1);
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("초밥", 1);

        //when and then
        assertThatThrownBy(() -> new User(day, map))
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
        assertThatThrownBy(() -> new User(day, map))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ORDER_ONLY_DRINK_ERROR.getMessage());
    }

    @Test
    void 초과_주문_예외_테스트() {
        //given
        Day day = new Day(1);
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("제로콜라", 1);
        map.put("해산물파스타", 20);

        //when and then
        assertThatThrownBy(() -> new User(day, map))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ORDER_COUNT_EXCEED_ERROR.getMessage());
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
        assertThat(totalPrice).isEqualTo(73_000);
    }

    @Test
    void 메뉴_타입별_주문_개수_구하기() {
        //given
        Day day = new Day(1);
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("제로콜라", 1);
        map.put("레드와인", 3);
        map.put("해산물파스타", 2);
        User user = new User(day, map);

        //when
        int drinkCount = user.getCountByMenuType(Menu::isDrink);
        int mainCount = user.getCountByMenuType(Menu::isMain);

        //then
        assertThat(drinkCount).isEqualTo(4);
        assertThat(mainCount).isEqualTo(2);
    }
}