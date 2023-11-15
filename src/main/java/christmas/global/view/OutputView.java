package christmas.global.view;

import christmas.domain.Menu;
import christmas.domain.User;
import christmas.domain.discount.DiscountPolicy;
import christmas.domain.discount.enums.Policy;
import christmas.domain.event.EventResult;
import java.util.LinkedHashMap;
import java.util.Map;

public class OutputView {

    private static final String ENTER = System.lineSeparator();
    private static final String EMPTY_MESSAGE = "없음";
    private static final String GREETING_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String PREVIEW_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String ORDERED_MENUS_MESSAGE = "<주문 메뉴>";
    private static final String TOTAL_ORDER_PRICE_MESSAGE = "<할인 전 총주문 금액>";
    private static final String GIFT_MESSAGE = "<증정 메뉴>";
    private static final String BENEFIT_MESSAGE = "<혜택 내역>";
    private static final String TOTAL_BENEFIT_AMOUNT_MESSAGE = "<총혜택 금액>";
    private static final String AFTER_DISCOUNT_PRICE_MESSAGE = "<할인 후 예상 결제 금액>";
    private static final String EVENT_BADGE_MESSAGE = "<12월 이벤트 배지>";
    private static final String CHRISTMAS_EVENT_MESSAGE = "크리스마스 디데이 할인: -%,d원\n";
    private static final String WEEK_DAY_EVENT_MESSAGE = "평일 할인: -%,d원\n";
    private static final String WEEKEND_EVENT_MESSAGE = "주말 할인: -%,d원\n";
    private static final String SPECIAL_EVENT_MESSAGE = "특별 할인: -%,d원\n";
    private static final String GIFT_EVENT_MESSAGE = "증정 이벤트: -%,d원\n";

    public void printGreetingMessage() {
        System.out.println(GREETING_MESSAGE);
    }

    public void printEventPreviewMessage(User user, EventResult eventResult) {
        System.out.printf(PREVIEW_MESSAGE, user.getDay().getDay());
        printOrderedMenus(user);
        printOrderTotalPrice(user);
        printGift(eventResult);
        printBenefits(eventResult);
        printTotalBenefitAmount(eventResult);
        printAfterDiscountPrice(user, eventResult);
        printEventBadge(eventResult);
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

    private void printGift(EventResult eventResult) {
        System.out.println(ENTER + GIFT_MESSAGE);
        if (eventResult.hasGift()) {
            System.out.printf("%s %d개\n", Menu.getGiftMenu().getMenuName(), 1);
            return;
        }
        System.out.println(EMPTY_MESSAGE);
    }

    private void printBenefits(EventResult eventResult) {
        System.out.println(ENTER + BENEFIT_MESSAGE);
        Map<DiscountPolicy, Integer> discountBenefits = eventResult.getDiscountBenefits();
        Map<DiscountPolicy, String> messageForms = new LinkedHashMap<>();
        messageForms.put(Policy.CHRISTMAS_DISCOUNT.getDiscountPolicy(), CHRISTMAS_EVENT_MESSAGE);
        messageForms.put(Policy.WEEK_DAY_DISCOUNT.getDiscountPolicy(), WEEK_DAY_EVENT_MESSAGE);
        messageForms.put(Policy.WEEKEND_DISCOUNT.getDiscountPolicy(), WEEKEND_EVENT_MESSAGE);
        messageForms.put(Policy.SPECIAL_DISCOUNT.getDiscountPolicy(), SPECIAL_EVENT_MESSAGE);
        messageForms.put(Policy.GIFT_DISCOUNT.getDiscountPolicy(), GIFT_EVENT_MESSAGE);

        boolean hadBenefits = false;
        for (DiscountPolicy discountPolicy : messageForms.keySet()) {
            if (discountBenefits.containsKey(discountPolicy) && discountBenefits.get(discountPolicy) != 0) {
                System.out.printf(messageForms.get(discountPolicy), discountBenefits.get(discountPolicy));
                hadBenefits = true;
            }
        }

        if (!hadBenefits) {
            System.out.println(EMPTY_MESSAGE);
        }
    }

    private void printTotalBenefitAmount(EventResult eventResult) {
        System.out.println(ENTER + TOTAL_BENEFIT_AMOUNT_MESSAGE);
        System.out.printf("-%,d원\n", eventResult.getTotalBenefits());
    }

    private void printAfterDiscountPrice(User user, EventResult eventResult) {
        System.out.println(ENTER + AFTER_DISCOUNT_PRICE_MESSAGE);
        System.out.printf("%,d원\n", user.getTotalPrice() - eventResult.getTotalDiscount());
    }


    private void printEventBadge(EventResult eventResult) {
        System.out.println(ENTER + EVENT_BADGE_MESSAGE);
        System.out.print(eventResult.getEventBadge());
    }

}
