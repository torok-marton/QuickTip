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
import quicktip.bean.input.FixedIntervalQuickTipInput;
import quicktip.bean.response.FixedIntervalQuickTipResponse;

@RunWith(JUnit4.class)
public class FixedIntervalQuickTipTest {

    private static final int MAX_RANDOM_NUMBER = 90;
    private static final int PIECES_OF_RANDOM_NUMBERS = 5;
    private static final int PIECES_OF_SHEETS = 10;
    private static final int LOW_PIECES_OF_SHEETS = -1;
    private QuickTip underTest;

    @Test
    public void testFixedIntervalQuickTip() {
        FixedIntervalQuickTipInput input = getFixedIntervalQuickTipInput(PIECES_OF_RANDOM_NUMBERS, PIECES_OF_SHEETS);
        underTest = new FixedIntervalQuickTip(input);

        FixedIntervalQuickTipResponse response = (FixedIntervalQuickTipResponse) underTest.generateRandomNumbers();

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
    public void testFixedIntervalQuickTip_LowSheetNumber() {
        FixedIntervalQuickTipInput input = getFixedIntervalQuickTipInput(PIECES_OF_RANDOM_NUMBERS, LOW_PIECES_OF_SHEETS);
        underTest = new FixedIntervalQuickTip(input);

        underTest.generateRandomNumbers();
    }

    private FixedIntervalQuickTipInput getFixedIntervalQuickTipInput(int piecesOfRandomNumbers, int piecesOfSheets) {
        FixedIntervalQuickTipInput input = new FixedIntervalQuickTipInput();
        input.setPiecesOfSheets(piecesOfSheets);
        input.setPiecesOfRandomNumbers(piecesOfRandomNumbers);
        return input;
    }

}