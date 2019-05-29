package quicktip.configuration;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import quicktip.bean.unmarshaller.Parameters;
import quicktip.service.ConfiguredQuickTip;
import quicktip.service.FixedIntervalQuickTip;
import quicktip.service.OneSheetQuickTip;
import quicktip.service.QuickTip;
import quicktip.unmarshaller.ParametersUnmarshaller;

@RunWith(MockitoJUnitRunner.class)
public class QuickTipConfigurationTest {

    private static final int FIRST_ALGORITHM = 1;
    private static final int SECOND_ALGORITHM = 2;
    private static final int THIRD_ALGORITHM = 3;
    private static final int MAX_RANDOM_NUMBER = 80;
    private static final int PIECES_OF_RANDOM_NUMBERS = 5;
    private static final int PIECES_OF_SHEETS = 10;

    @Mock
    private ParametersUnmarshaller parametersUnmarshaller;

    private QuickTipConfiguration underTest;

    @Before
    public void setUp() {
        underTest = new QuickTipConfiguration();
    }

    @Test
    public void testGetParameters() {
        reset(parametersUnmarshaller);

        Parameters parameters = getParameters(FIRST_ALGORITHM,MAX_RANDOM_NUMBER, PIECES_OF_RANDOM_NUMBERS, PIECES_OF_SHEETS);
        when(parametersUnmarshaller.unmarshal(null)).thenReturn(parameters);

        Parameters result = underTest.getParameters(parametersUnmarshaller);

        verify(parametersUnmarshaller, times(1)).unmarshal(null);
        assertThat(result, is(not(nullValue())));
        assertThat(result.getPiecesOfSheets(), is(PIECES_OF_SHEETS));
        assertThat(result.getPiecesOfRandomNumbers(), is(PIECES_OF_RANDOM_NUMBERS));
        assertThat(result.getMaxRandomNumber(), is(MAX_RANDOM_NUMBER));
        assertThat(result.getAlgorithm(), is(FIRST_ALGORITHM));
    }

    @Test
    public void testGetQuickTip_oneSheetAlgorithm() {
        Parameters parameters = getParameters(FIRST_ALGORITHM,MAX_RANDOM_NUMBER, PIECES_OF_RANDOM_NUMBERS, 0);

        QuickTip quickTip = underTest.getQuickTip(parameters);

        assertThat(quickTip, instanceOf(OneSheetQuickTip.class));
    }

    @Test
    public void testGetQuickTip_fixedIntervalAlgorithm() {
        Parameters parameters = getParameters(SECOND_ALGORITHM, 0, PIECES_OF_RANDOM_NUMBERS, PIECES_OF_SHEETS);

        QuickTip quickTip = underTest.getQuickTip(parameters);

        assertThat(quickTip, instanceOf(FixedIntervalQuickTip.class));
    }

    @Test
    public void testGetQuickTip_configuredAlgorithm() {
        Parameters parameters = getParameters(THIRD_ALGORITHM, MAX_RANDOM_NUMBER, PIECES_OF_RANDOM_NUMBERS, PIECES_OF_SHEETS);

        QuickTip quickTip = underTest.getQuickTip(parameters);

        assertThat(quickTip, instanceOf(ConfiguredQuickTip.class));
    }

    private Parameters getParameters(int algorithm, int maxRandomNumber, int piecesOfRandomNumbers, int piecesOfSheets) {
        Parameters parameters = new Parameters();
        parameters.setAlgorithm(algorithm);
        parameters.setPiecesOfSheets(piecesOfSheets);
        parameters.setPiecesOfRandomNumbers(piecesOfRandomNumbers);
        parameters.setMaxRandomNumber(maxRandomNumber);
        return parameters;
    }
}