package quicktip.service;

import quicktip.bean.input.OneSheetQuickTipInput;
import quicktip.bean.response.BaseResponse;
import quicktip.bean.response.FilledSheet;
import quicktip.bean.response.OneSheetQuickTipResponse;

public class OneSheetQuickTip extends CommonQuickTip {

    public OneSheetQuickTip(OneSheetQuickTipInput input) {
        super(input);
    }

    @Override
    public BaseResponse generateRandomNumbers() {
        if (!(input instanceof OneSheetQuickTipInput)) {
            throw new IllegalArgumentException("Input type must be OneSheetQuickTipInput");
        }
        OneSheetQuickTipInput oneSheetQuickTipInput = (OneSheetQuickTipInput) input;
        OneSheetQuickTipResponse response = new OneSheetQuickTipResponse();

        FilledSheet filledSheet = fillSheet(oneSheetQuickTipInput.getPiecesOfRandomNumbers(), oneSheetQuickTipInput.getMaxRandomNumber());
        response.getFilledSheets().add(filledSheet);

        return response;
    }
}
