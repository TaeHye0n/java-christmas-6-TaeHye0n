package christmas.domain.discount;

import java.util.Arrays;

public enum EventBadge {

    EVENT_SANTA_BADGE("산타", 20_000),
    EVENT_TREE_BADGE("트리", 10_000),
    EVENT_STAR_BADGE("별", 5_000),
    EMPTY("없음", Integer.MAX_VALUE);

    private final String badgeName;
    private final int eventThreshold;

    EventBadge(String badgeName, int eventThreshold) {
        this.badgeName = badgeName;
        this.eventThreshold = eventThreshold;
    }

    public static EventBadge from(int amount) {
        return Arrays.stream(values())
                .filter(eventBadge -> eventBadge.isExceed(amount))
                .findFirst()
                .orElse(EMPTY);
    }

    private boolean isExceed(int amount) {
        return amount >= this.eventThreshold;
    }

    public String getBadgeName() {
        return badgeName;
    }

}
