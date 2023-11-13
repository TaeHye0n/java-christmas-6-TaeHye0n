package christmas.domain;

import static christmas.global.enums.ErrorMessage.DAY_INVALID_ERROR;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Day {

    private static final int YEAR = 2023;
    private static final int MONTH = 12;
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
    
}
