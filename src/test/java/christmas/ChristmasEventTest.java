package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static christmas.global.enums.ErrorMessage.DAY_INVALID_ERROR;
import static christmas.global.enums.ErrorMessage.ORDER_COUNT_EXCEED_ERROR;
import static christmas.global.enums.ErrorMessage.ORDER_INVALID_ERROR;
import static christmas.global.enums.ErrorMessage.ORDER_ONLY_DRINK_ERROR;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ChristmasEventTest extends NsTest {

    @Test
    void 날짜_입력_예외_테스트() {
        assertSimpleTest(() -> {
            runException(" ", "a", "-1", "0", "32");
            assertThat(output()).contains(
                    "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.",
                    DAY_INVALID_ERROR.getMessage(),
                    "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"
            );
        });
    }

    @Test
    void 주문_예외_테스트() {
        assertSimpleTest(() -> {
            runException("31", " ", "a", "초밥-1", "제로콜라-1",
                    "초코케이크-21", "초코케이크-15,제로콜라-6",
                    "초코케이크-1, 해산물파스타-1", "초코케이크-1,초코케이크-1",
                    "초코케이크-0"
            );
            assertThat(output()).contains(
                    ORDER_COUNT_EXCEED_ERROR.getMessage(),
                    ORDER_ONLY_DRINK_ERROR.getMessage(),
                    ORDER_INVALID_ERROR.getMessage(),
                    "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"
            );
        });
    }


    @Test
    void 주문_메뉴_출력() {
        assertSimpleTest(() -> {
            run("3", "초코케이크-1,해산물파스타-2");
            assertThat(output()).contains(
                    "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!",
                    "<주문 메뉴>", "초코케이크 1개", "해산물파스타 2개"
            );
        });
    }

    @Test
    void 할인_전_총주문_금액_출력() {
        assertSimpleTest(() -> {
            run("3", "초코케이크-1,해산물파스타-2");
            assertThat(output()).contains(
                    "<할인 전 총주문 금액>",
                    "85,000원"
            );
        });
    }

    @Test
    void 증정_메뉴_없음_테스트() {
        assertSimpleTest(() -> {
            run("3", "초코케이크-1,해산물파스타-2");
            assertThat(output()).contains(
                    "<증정 메뉴>",
                    "없음"
            );
        });
    }

    @Test
    void 증멍_메뉴_있음_테스트() {
        assertSimpleTest(() -> {
            run("3", "초코케이크-1,해산물파스타-3");
            assertThat(output()).contains(
                    "<증정 메뉴>",
                    "샴페인 1개"
            );
        });
    }

    @Test
    void 혜택_내역_없음() {
        assertSimpleTest(() -> {
            run("28", "해산물파스타-1");
            assertThat(output()).contains(
                    "<혜택 내역>",
                    "없음"
            );
        });
    }

    @Test
    void 주문_금액_미달_혜택_없음() {
        assertSimpleTest(() -> {
            run("1", "아이스크림-1");
            assertThat(output()).contains(
                    "<혜택 내역>",
                    "없음"
            );
        });
    }

    @Test
    void 혜택_내역_주중_특별_크리스마스_증정_있음() {
        assertSimpleTest(() -> {
            run("25", "초코케이크-1,해산물파스타-3");
            assertThat(output()).contains(
                    "<혜택 내역>",
                    "크리스마스 디데이 할인: -3,400원",
                    "특별 할인: -1,000원",
                    "평일 할인: -2,023원",
                    "증정 이벤트: -25,000원"
            );
        });
    }

    @Test
    void 혜택_내역_주말_크리스마스_증정_있음() {
        assertSimpleTest(() -> {
            run("23", "초코케이크-1,해산물파스타-3");
            assertThat(output()).contains(
                    "<혜택 내역>",
                    "크리스마스 디데이 할인: -3,200원",
                    "주말 할인: -6,069원",
                    "증정 이벤트: -25,000원"
            );
        });
    }

    @Test
    void 총_혜택_금액_없음() {
        assertSimpleTest(() -> {
            run("1", "아이스크림-1");
            assertThat(output()).contains(
                    "<총혜택 금액>",
                    "없음"
            );
        });
    }

    @Test
    void 총_혜택_금액_있음() {
        assertSimpleTest(() -> {
            run("23", "초코케이크-1,해산물파스타-3");
            assertThat(output()).contains(
                    "<총혜택 금액>",
                    "-34,269원"
            );
        });
    }

    @Test
    void 할인_후_예상_결제_금액() {
        assertSimpleTest(() -> {
            run("23", "초코케이크-1,해산물파스타-3");
            assertThat(output()).contains(
                    "<할인 후 예상 결제 금액>",
                    "110,731원"
            );
        });
    }

    @Test
    void 이벤트_뱃지_없음() {
        assertSimpleTest(() -> {
            run("23", "초코케이크-1");
            assertThat(output()).contains(
                    "<12월 이벤트 배지>",
                    "없음"
            );
        });
    }

    @Test
    void 이벤트_뱃지_별() {
        assertSimpleTest(() -> {
            run("24", "초코케이크-2");
            assertThat(output()).contains(
                    "<12월 이벤트 배지>",
                    "별"
            );
        });
    }

    @Test
    void 이벤트_뱃지_트리() {
        assertSimpleTest(() -> {
            run("24", "초코케이크-4");
            assertThat(output()).contains(
                    "<12월 이벤트 배지>",
                    "트리"
            );
        });
    }

    @Test
    void 이벤트_뱃지_산타() {
        assertSimpleTest(() -> {
            run("24", "초코케이크-8");
            assertThat(output()).contains(
                    "<12월 이벤트 배지>",
                    "산타"
            );
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
