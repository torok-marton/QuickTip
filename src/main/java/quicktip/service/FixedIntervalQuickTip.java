package quicktip.service;

import quicktip.bean.input.FixedIntervalQuickTipInput;
import quicktip.bean.response.BaseResponse;
import quicktip.bean.response.FilledSheet;
import quicktip.bean.response.FixedIntervalQuickTipResponse;

import java.util.ArrayList;
import java.util.List;

public class FixedIntervalQuickTip extends CommonQuickTip {

    private static final int MAX_RANDOM_NUMBER = 90;

    public FixedIntervalQuickTip(FixedIntervalQuickTipInput input) {
        super(input);
    }

    @Override
    public BaseResponse generateRandomNumbers() {
        if (!(input instanceof FixedIntervalQuickTipInput)) {
            throw new IllegalArgumentException("Input type must be FixedIntervalQuickTipInput");
        }

        FixedIntervalQuickTipInput fixedIntervalQuickTipInput = (FixedIntervalQuickTipInput) input;
        FixedIntervalQuickTipResponse response = new FixedIntervalQuickTipResponse();
        List<FilledSheet> filledSheets = new ArrayList<>();

        int piecesOfRandomNumbers = fixedIntervalQuickTipInput.getPiecesOfRandomNumbers();
        int piecesOfSheets = fixedIntervalQuickTipInput.getPiecesOfSheets();

        if (piecesOfSheets < 0) {
            throw new IllegalArgumentException("<piecesOfSheets> should be equal to or greater than 0");
        }

        for (long i = 0L; i < piecesOfSheets; i++) {
            FilledSheet filledSheet = fillSheet(piecesOfRandomNumbers, MAX_RANDOM_NUMBER);
            filledSheets.add(filledSheet);
        }

        response.setFilledSheets(filledSheets);
        return response;
    }
}
