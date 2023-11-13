package christmas.global.view;

import christmas.domain.Menu;
import christmas.domain.User;
import christmas.domain.discount.DiscountResult;
import java.util.Map;

public class OutputView {

    private static final String ENTER = System.lineSeparator();
    private static final String GREETING_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String PREVIEW_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String ORDERED_MENUS_MESSAGE = "<주문 메뉴>";
    private static final String TOTAL_ORDER_PRICE_MESSAGE = "<할인 전 총주문 금액>";
    private static final String GIFT_MESSAGE = "<증정 메뉴>";
    private static final String EMPTY_MESSAGE = "없음";

    public void printGreetingMessage() {
        System.out.println(GREETING_MESSAGE);
    }

    public void printEventPreviewMessage(User user, DiscountResult discountResult) {
        System.out.printf(PREVIEW_MESSAGE, user.getDay().getDay());
        printOrderedMenus(user);
        printOrderTotalPrice(user);
        printGift(discountResult);
    }

    private void printOrderedMenus(User user) {
        System.out.println(ENTER + ORDERED_MENUS_MESSAGE);
        for (Map.Entry<Menu, Integer> entry : user.getMenuAndCounts().entrySet()) {
            System.out.printf("%s %d개\n", entry.getKey().getMenuName(), entry.getValue());
        }
    }

    private void printOrderTotalPrice(User user) {
        System.out.println(ENTER + TOTAL_ORDER_PRICE_MESSAGE);
        System.out.printf("%,d원\n", user.getTotalPrice());
    }

    private void printGift(DiscountResult discountResult) {
        System.out.println(ENTER + GIFT_MESSAGE);
        if (discountResult.hasGift()) {
            System.out.printf("%s %d개\n", Menu.getGiftMenu().getMenuName(), 1);
            return;
        }
        System.out.println(EMPTY_MESSAGE);
    }
}
