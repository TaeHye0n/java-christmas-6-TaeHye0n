package christmas;

import christmas.domain.ChristmasController;
import christmas.global.view.InputView;
import christmas.global.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChristmasController christmasController = new ChristmasController(new InputView(), new OutputView());
        christmasController.run();
    }
}
