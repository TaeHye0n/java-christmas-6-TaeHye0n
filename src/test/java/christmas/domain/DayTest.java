package christmas.domain;

import static christmas.global.enums.ErrorMessage.DAY_INVALID_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class DayTest {

    @Test
    void 숫자_범위_예외_테스트() {
        assertThatThrownBy(() -> new Day(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DAY_INVALID_ERROR.getMessage());
    }

    @Test
    void 숫자_범위_예외_테스트_2() {
        assertThatThrownBy(() -> new Day(32))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DAY_INVALID_ERROR.getMessage());
    }

    @Test
    void 요일_생성_테스트() {
        //given
        Day day = new Day(1);
        String expectedDayOfWeek = "Friday";

        //when
        String dayOfWeek = day.getDayOfWeek();

        //then
        assertThat(dayOfWeek).isEqualTo(expectedDayOfWeek);
    }

}