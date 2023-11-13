package christmas.global.view;

import static christmas.global.enums.ErrorMessage.DAY_INVALID_ERROR;
import static christmas.global.enums.ErrorMessage.ORDER_INVALID_ERROR;

import camp.nextstep.edu.missionutils.Console;
import christmas.global.utils.ExceptionHandlingUtil;
import java.util.LinkedHashMap;
import java.util.Map;

public class InputView {

    private static final String MENU_DELIMITER = ",";
    private static final String COUNT_DELIMITER = "-";
    private static final String INPUT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String INPUT_MENU_AND_COUNT_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    private int getDate() {
        try {
            String input = Console.readLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(DAY_INVALID_ERROR.getMessage());
        }
    }

    private Map<String, Integer> getMenuAndCount() {
        String input = Console.readLine();
        Map<String, Integer> menuAndCounts = new LinkedHashMap<>();
        String[] menuPairs = input.split(MENU_DELIMITER);

        try {
            parseMenuPairs(menuPairs, menuAndCounts);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ORDER_INVALID_ERROR.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(ORDER_INVALID_ERROR.getMessage());
        }

        return menuAndCounts;
    }

    private void parseMenuPairs(String[] menuPairs, Map<String, Integer> menuAndCounts) {
        for (String menuPair : menuPairs) {
            String[] parts = menuPair.split(COUNT_DELIMITER);
            if (menuAndCounts.containsKey(parts[0])) {
                throw new IllegalArgumentException(ORDER_INVALID_ERROR.getMessage());
            }
            if (Integer.parseInt(parts[1]) <= 0) {
                throw new IllegalArgumentException(ORDER_INVALID_ERROR.getMessage());
            }
            menuAndCounts.put(parts[0], Integer.parseInt(parts[1]));
        }
    }


    public int readDate() {
        return ExceptionHandlingUtil.handleExceptionWithMessage(
                message -> System.out.println(INPUT_DATE_MESSAGE),
                this::getDate
        );
    }

    public Map<String, Integer> readMenuAndCount() {
        return ExceptionHandlingUtil.handleExceptionWithMessage(
                message -> System.out.println(INPUT_MENU_AND_COUNT_MESSAGE),
                this::getMenuAndCount
        );
    }

}
