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

    private QuickTip underTest;

    @Test
    public void testOneSheetQuickTip() {
        int maxRandomNumber = 90;
        int piecesOfRandomNumbers = 5;

        OneSheetQuickTipInput input = getOneSheetQuickTipInput(maxRandomNumber, piecesOfRandomNumbers);
        underTest = new OneSheetQuickTip(input);

        OneSheetQuickTipResponse response = (OneSheetQuickTipResponse) underTest.generateRandomNumbers();
        assertThat(response, is(not(nullValue())));
        assertThat(response.getFilledSheets(), is(not(empty())));
        assertThat(response.getFilledSheets().size(), is(1));
        assertThat(response.getFilledSheets().get(0).getNumbers(), is(not(empty())));
        assertThat(response.getFilledSheets().get(0).getNumbers().size(), is(piecesOfRandomNumbers));
        for (Integer i : response.getFilledSheets().get(0).getNumbers()) {
            assertThat(i, is(not(greaterThan(maxRandomNumber))));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOneSheetQuickTip_NoMaxNumber() {
        OneSheetQuickTipInput input = getOneSheetQuickTipInput(2, -1);
        underTest = new OneSheetQuickTip(input);

        underTest.generateRandomNumbers();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOneSheetQuickTip_LowMaxNumber() {
        OneSheetQuickTipInput input = getOneSheetQuickTipInput(0, 5);
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
