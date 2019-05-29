package quicktip.service;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import quicktip.bean.input.ConfiguredQuickTipInput;
import quicktip.bean.response.ConfiguredQuickTipResponse;


@RunWith(JUnit4.class)
public class ConfiguredQuickTipTest {

    private static final int MAX_RANDOM_NUMBER = 80;
    private static final int PIECES_OF_SHEETS = 4;
    private static final int PIECES_OF_RANDOM_NUMBERS = 10;
    private static final int LOW_PIECES_OF_SHEETS = -1;
    private QuickTip underTest;

    @Test
    public void testConfiguredQuickTip() {
        ConfiguredQuickTipInput input = getConfiguredQuickTipInput(MAX_RANDOM_NUMBER, PIECES_OF_SHEETS, PIECES_OF_RANDOM_NUMBERS);
        underTest = new ConfiguredQuickTip(input);

        ConfiguredQuickTipResponse response = (ConfiguredQuickTipResponse) underTest.generateRandomNumbers();

        assertThat(response, is(not(nullValue())));
        assertThat(response.getFilledSheets(), is(not(empty())));
        assertThat(response.getFilledSheets().size(), is(PIECES_OF_SHEETS));
        assertThat(response.getFilledSheets().get(0).getNumbers(), is(not(empty())));
        assertThat(response.getFilledSheets().get(0).getNumbers().size(), is(PIECES_OF_RANDOM_NUMBERS));
        for (Integer i : response.getFilledSheets().get(0).getNumbers()) {
            assertThat(i, is(not(greaterThan(MAX_RANDOM_NUMBER))));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConfiguredQuickTip__LowSheetNumber() {
        ConfiguredQuickTipInput input = getConfiguredQuickTipInput(MAX_RANDOM_NUMBER, LOW_PIECES_OF_SHEETS, PIECES_OF_RANDOM_NUMBERS);
        underTest = new ConfiguredQuickTip(input);

        underTest.generateRandomNumbers();
    }

    private ConfiguredQuickTipInput getConfiguredQuickTipInput(int maxRandomNumber, int piecesOfSheets, int piecesOfRandomNumbers) {
        ConfiguredQuickTipInput input = new ConfiguredQuickTipInput();
        input.setPiecesOfRandomNumbers(piecesOfRandomNumbers);
        input.setPiecesOfSheets(piecesOfSheets);
        input.setMaxRandomNumber(maxRandomNumber);
        return input;
    }
}