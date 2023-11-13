package christmas.domain;

import java.util.Arrays;

public enum Menu {

    MUSHROOM_SOUP("에피타이저", "양송이수프", 6_000, false),
    TAPAS("에피타이저", "타파스", 5_500, false),
    CAESAR_SALAD("에피타이저", "시저샐러드", 8_000, false),
    T_BONE_STEAK("메인", "티본스테이크", 55_000, false),
    BBQ_RIBS("메인", "바비큐립", 54_000, false),
    SEAFOOD_PASTA("메인", "해산물파스타", 35_000, false),
    CHRISTMAS_PASTA("메인", "크리스마스파스타", 25_000, false),
    CHOCOLATE_CAKE("디저트", "초코케이크", 15_000, false),
    ICE_CREAM("디저트", "아이스크림", 5_000, false),
    ZERO_COLA("음료", "제로콜라", 3_000, false),
    RED_WINE("음료", "레드와인", 60_000, false),
    CHAMPAGNE("음료", "샴페인", 25_000, true),
    EMPTY("없음", "없음", 0, false);

    private final String menuType;
    private final String menuName;
    private final int menuPrice;
    private boolean isGift;

    Menu(String menuType, String menuName, int menuPrice, boolean isGift) {
        this.menuType = menuType;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.isGift = isGift;
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

    public String getMenuName() {
        return menuName;
    }

    public static Menu getGiftMenu() {
        return Arrays.stream(values())
                .filter(menu -> menu.isGift)
                .findFirst()
                .orElse(EMPTY);
    }
}
