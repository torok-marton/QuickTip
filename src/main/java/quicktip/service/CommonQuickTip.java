package quicktip.service;

import quicktip.bean.input.BaseInput;
import quicktip.bean.response.FilledSheet;

import java.util.Arrays;
import java.util.Random;

abstract class CommonQuickTip implements QuickTip {

    private static final int MIN_RANDOM_NUMBER = 1;

    BaseInput input;

    <T extends BaseInput> CommonQuickTip(T input) {
        this.input = input;
    }

    FilledSheet fillSheet(int piecesOfRandomNumber, int maxRandomNumber) {
        FilledSheet filledSheet = new FilledSheet();

        if (maxRandomNumber <= MIN_RANDOM_NUMBER) {
            throw new IllegalArgumentException("<maxRandomNumber> should be greater than 1");
        }
        if (piecesOfRandomNumber < 0) {
            throw new IllegalArgumentException("<piecesOfRandomNumber> should be equal to or greater than 0");
        }

        Integer[] numbers = new Random()
                .ints(piecesOfRandomNumber, MIN_RANDOM_NUMBER, maxRandomNumber)
                .boxed()
                .toArray(Integer[]::new);
        filledSheet.setNumbers(Arrays.asList(numbers));

        return filledSheet;
    }
}
