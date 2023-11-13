package christmas.global.enums;

public enum ErrorMessage {

    DAY_INVALID_ERROR("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    ORDER_ONLY_DRINK_ERROR("[ERROR] 음료만 주문 하실 수 없습니다. 다시 입력해 주세요."),
    ORDER_INVALID_ERROR("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
