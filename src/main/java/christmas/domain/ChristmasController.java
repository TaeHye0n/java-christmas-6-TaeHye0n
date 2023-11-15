package christmas.domain;

import christmas.domain.event.EventResult;
import christmas.global.utils.ExceptionHandlingUtil;
import christmas.global.view.InputView;
import christmas.global.view.OutputView;

public class ChristmasController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printGreetingMessage();
        User user = generateUser();
        outputView.printEventPreviewMessage(user, new EventResult(user));
    }

    private User generateUser() {
        Day day = generateDay();
        return ExceptionHandlingUtil.handleException(
                () -> new User(day, inputView.readMenuAndCount()));
    }

    private Day generateDay() {
        return ExceptionHandlingUtil.handleException(() -> new Day(inputView.readDate()));
    }
}
