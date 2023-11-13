package christmas.domain;

import static christmas.global.enums.ErrorMessage.ORDER_COUNT_EXCEED_ERROR;
import static christmas.global.enums.ErrorMessage.ORDER_INVALID_ERROR;
import static christmas.global.enums.ErrorMessage.ORDER_ONLY_DRINK_ERROR;

import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;

public class User {

    private static final int MAX_MENU_COUNT = 20;
    private final Day day;
    private final Map<Menu, Integer> menuAndCounts;

    public User(Day day, Map<String, Integer> menuAndCounts) {
        this.day = day;
        this.menuAndCounts = new EnumMap<>(Menu.class);
        initializeMenuAndCounts(menuAndCounts);
        validateHasNotMenu(this.menuAndCounts);
        validateOrderOnlyDrinks(this.menuAndCounts);
        validateExceedCount(this.menuAndCounts);
    }

    private void validateExceedCount(Map<Menu, Integer> menuAndCounts) {
        if (menuAndCounts.values().stream().mapToInt(Integer::intValue).sum() > MAX_MENU_COUNT) {
            throw new IllegalArgumentException(ORDER_COUNT_EXCEED_ERROR.getMessage());
        }
    }

    private void validateHasNotMenu(Map<Menu, Integer> menuAndCounts) {
        if (menuAndCounts.keySet().stream().anyMatch(menu -> menu.equals(Menu.EMPTY))) {
            throw new IllegalArgumentException(ORDER_INVALID_ERROR.getMessage());
        }
    }

    private void validateOrderOnlyDrinks(Map<Menu, Integer> menuAndCounts) {
        if (menuAndCounts.keySet().stream().allMatch(Menu::isDrink)) {
            throw new IllegalArgumentException(ORDER_ONLY_DRINK_ERROR.getMessage());
        }
    }

    private void initializeMenuAndCounts(Map<String, Integer> menuAndCounts) {
        for (Entry<String, Integer> entry : menuAndCounts.entrySet()) {
            Menu menu = Menu.of(entry.getKey());
            this.menuAndCounts.put(menu, entry.getValue());
        }
    }

    public int getTotalPrice() {
        return menuAndCounts.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getMenuPrice() * entry.getValue())
                .sum();
    }

}
