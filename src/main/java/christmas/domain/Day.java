package christmas.domain;

import static christmas.global.enums.ErrorMessage.DAY_INVALID_ERROR;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

public class Day {

    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final List<Integer> SPECIAL_DAYS = List.of(3, 10, 17, 24, 25, 31);
    private final int day;
    private final String dayOfWeek;

    public Day(int day) {
        validateDay(day);
        this.day = day;
        this.dayOfWeek = initializeDay(day);
    }

    private void validateDay(int day) {
        if (day > 31 || day <= 0) {
            throw new IllegalArgumentException(DAY_INVALID_ERROR.getMessage());
        }
    }

    private String initializeDay(int day) {
        LocalDate date = LocalDate.of(YEAR, MONTH, day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    public int getDay() {
        return day;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public int getDaysUntilChristmas() {
        LocalDate date = LocalDate.of(YEAR, MONTH, day);
        LocalDate christmasDate = LocalDate.of(YEAR, 12, 25);
        return (int) ChronoUnit.DAYS.between(date, christmasDate);
    }

    public boolean isBeforeChristmas() {
        return day <= 25;
    }

    public boolean isWeekend() {
        if ((dayOfWeek.equals("Friday") || dayOfWeek.equals("Saturday"))) {
            return false;
        }
        return true;
    }

    public boolean isSpecialDay() {
        return SPECIAL_DAYS.contains(day);
    }
}
