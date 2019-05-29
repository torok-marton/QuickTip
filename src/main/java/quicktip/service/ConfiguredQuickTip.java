package quicktip.service;

import quicktip.bean.input.ConfiguredQuickTipInput;
import quicktip.bean.response.BaseResponse;
import quicktip.bean.response.ConfiguredQuickTipResponse;
import quicktip.bean.response.FilledSheet;

import java.util.ArrayList;
import java.util.List;

public class ConfiguredQuickTip extends CommonQuickTip {

    public ConfiguredQuickTip(ConfiguredQuickTipInput input) {
        super(input);
    }

    @Override
    public BaseResponse generateRandomNumbers() {
        if (!(input instanceof ConfiguredQuickTipInput)) {
            throw new IllegalArgumentException("Input type must be ConfiguredQuickTipInput");
        }
        ConfiguredQuickTipInput configuredQuickTipInput = (ConfiguredQuickTipInput) input;
        ConfiguredQuickTipResponse response = new ConfiguredQuickTipResponse();
        List<FilledSheet> filledSheets = new ArrayList<>();

        int piecesOfRandomNumbers = configuredQuickTipInput.getPiecesOfRandomNumbers();
        int piecesOfSheets = configuredQuickTipInput.getPiecesOfSheets();
        int maxRandomNumber = configuredQuickTipInput.getMaxRandomNumber();

        if (piecesOfSheets < 0) {
            throw new IllegalArgumentException("<piecesOfSheets> should be equal to or greater than 0");
        }

        for (long i = 0L; i < configuredQuickTipInput.getPiecesOfSheets(); i++) {
            FilledSheet filledSheet = fillSheet(piecesOfRandomNumbers, maxRandomNumber);
            filledSheets.add(filledSheet);
        }

        response.setFilledSheets(filledSheets);
        return response;
    }
}
