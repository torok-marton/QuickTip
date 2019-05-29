package quicktip.service;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import quicktip.bean.input.OneSheetQuickTipInput;
import quicktip.bean.response.OneSheetQuickTipResponse;

@RunWith(JUnit4.class)
public class OneSheetQuickTipTest {

    private static final int MAX_RANDOM_NUMBER = 90;
    private static final int PIECES_OF_RANDOM_NUMBERS = 5;
    private static final int LOW_PIECES_OF_RANDOM_NUMBERS = -1;
    private static final int LOW_MAX_RANDOM_NUMBER = 0;
    private QuickTip underTest;

    @Test
    public void testOneSheetQuickTip() {
        OneSheetQuickTipInput input = getOneSheetQuickTipInput(MAX_RANDOM_NUMBER, PIECES_OF_RANDOM_NUMBERS);
        underTest = new OneSheetQuickTip(input);

        OneSheetQuickTipResponse response = (OneSheetQuickTipResponse) underTest.generateRandomNumbers();
        assertThat(response, is(not(nullValue())));
        assertThat(response.getFilledSheets(), is(not(empty())));
        assertThat(response.getFilledSheets().size(), is(1));
        assertThat(response.getFilledSheets().get(0).getNumbers(), is(not(empty())));
        assertThat(response.getFilledSheets().get(0).getNumbers().size(), is(PIECES_OF_RANDOM_NUMBERS));
        for (Integer i : response.getFilledSheets().get(0).getNumbers()) {
            assertThat(i, is(not(greaterThan(MAX_RANDOM_NUMBER))));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOneSheetQuickTip_LowPiecesOfRandomNumber() {
        OneSheetQuickTipInput input = getOneSheetQuickTipInput(MAX_RANDOM_NUMBER, LOW_PIECES_OF_RANDOM_NUMBERS);
        underTest = new OneSheetQuickTip(input);

        underTest.generateRandomNumbers();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOneSheetQuickTip_LowMaxNumber() {
        OneSheetQuickTipInput input = getOneSheetQuickTipInput(LOW_MAX_RANDOM_NUMBER, PIECES_OF_RANDOM_NUMBERS);
        underTest = new OneSheetQuickTip(input);

        underTest.generateRandomNumbers();
    }

    private OneSheetQuickTipInput getOneSheetQuickTipInput(int maxRandomNumber, int piecesOfRandomNumbers) {
        OneSheetQuickTipInput input = new OneSheetQuickTipInput();
        input.setMaxRandomNumber(maxRandomNumber);
        input.setPiecesOfRandomNumbers(piecesOfRandomNumbers);
        return input;
    }
}
