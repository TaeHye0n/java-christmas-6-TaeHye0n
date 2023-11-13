package christmas.domain;

import java.util.Arrays;

public enum Menu {

    MUSHROOM_SOUP("에피타이저", "양송이수프", 6_000),
    TAPAS("에피타이저", "타파스", 5_500),
    CAESAR_SALAD("에피타이저", "시저샐러드", 8_000),
    T_BONE_STEAK("메인", "티본스테이크", 55_000),
    BBQ_RIBS("메인", "바비큐립", 54_000),
    SEAFOOD_PASTA("메인", "해산물파스타", 35_000),
    CHRISTMAS_PASTA("메인", "크리스마스파스타", 25_000),
    CHOCOLATE_CAKE("디저트", "초코케이크", 15_000),
    ICE_CREAM("디저트", "아이스크림", 5_000),
    ZERO_COLA("음료", "제로콜라", 3_000),
    RED_WINE("음료", "레드와인", 60_000),
    CHAMPAGNE("음료", "샴페인", 25_000),
    EMPTY("없음", "없음", 0);

    private final String menuType;
    private final String menuName;
    private final int menuPrice;

    Menu(String menuType, String menuName, int menuPrice) {
        this.menuType = menuType;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
    }

    public static Menu of(String menuName) {
        return Arrays.stream(values())
                .filter(menu -> menu.isMatch(menuName))
                .findFirst()
                .orElse(EMPTY);
    }

    private boolean isMatch(String menuName) {
        return this.menuName.equals(menuName);
    }

    public boolean isDrink() {
        return this.menuType.equals("음료");
    }

    public boolean isMain() {
        return this.menuType.equals("메인");
    }

    public boolean isDessert() {
        return this.menuType.equals("디저트");
    }

    public boolean isAppetizer() {
        return this.menuType.equals("에피타이저");
    }


    public int getMenuPrice() {
        return menuPrice;
    }
}
