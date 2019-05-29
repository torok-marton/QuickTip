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

    private QuickTip underTest;

    @Test
    public void testConfiguredQuickTip() {
        int maxRandomNumber = 80;
        int piecesOfSheets = 4;
        int piecesOfRandomNumbers = 10;

        ConfiguredQuickTipInput input = getConfiguredQuickTipInput(maxRandomNumber, piecesOfSheets, piecesOfRandomNumbers);
        underTest = new ConfiguredQuickTip(input);

        ConfiguredQuickTipResponse response = (ConfiguredQuickTipResponse) underTest.generateRandomNumbers();

        assertThat(response, is(not(nullValue())));
        assertThat(response.getFilledSheets(), is(not(empty())));
        assertThat(response.getFilledSheets().size(), is(piecesOfSheets));
        assertThat(response.getFilledSheets().get(0).getNumbers(), is(not(empty())));
        assertThat(response.getFilledSheets().get(0).getNumbers().size(), is(piecesOfRandomNumbers));
        for (Integer i : response.getFilledSheets().get(0).getNumbers()) {
            assertThat(i, is(not(greaterThan(maxRandomNumber))));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConfiguredQuickTip__LowSheetNumber() {
        ConfiguredQuickTipInput input = getConfiguredQuickTipInput(80, -1, 10);
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