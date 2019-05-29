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

    @Mock
    private ParametersUnmarshaller parametersUnmarshaller;

    private QuickTipConfiguration underTest;

    @Before
    public void setUp() {
        underTest = new QuickTipConfiguration();
    }

    @Test
    public void testGetParameters() {
        int algorithm = 1;
        int maxRandomNumber = 80;
        int piecesOfRandomNumbers = 5;
        int piecesOfSheets = 10;

        reset(parametersUnmarshaller);

        Parameters parameters = getParameters(algorithm, maxRandomNumber, piecesOfRandomNumbers, piecesOfSheets);
        when(parametersUnmarshaller.unmarshal(null)).thenReturn(parameters);

        Parameters result = underTest.getParameters(parametersUnmarshaller);

        verify(parametersUnmarshaller, times(1)).unmarshal(null);
        assertThat(result, is(not(nullValue())));
        assertThat(result.getPiecesOfSheets(), is(piecesOfSheets));
        assertThat(result.getPiecesOfRandomNumbers(), is(piecesOfRandomNumbers));
        assertThat(result.getMaxRandomNumber(), is(maxRandomNumber));
        assertThat(result.getAlgorithm(), is(algorithm));
    }

    @Test
    public void testGetQuickTip_oneSheetAlgorithm() {
        int algorithm = 1;
        int maxRandomNumber = 80;
        int piecesOfRandomNumbers = 5;

        Parameters parameters = getParameters(algorithm, maxRandomNumber, piecesOfRandomNumbers, 0);

        QuickTip quickTip = underTest.getQuickTip(parameters);

        assertThat(quickTip, instanceOf(OneSheetQuickTip.class));
    }

    @Test
    public void testGetQuickTip_fixedIntervalAlgorithm() {
        int algorithm = 2;
        int piecesOfRandomNumbers = 5;
        int piecesOfSheets = 10;

        Parameters parameters = getParameters(algorithm, 0, piecesOfRandomNumbers, piecesOfSheets);

        QuickTip quickTip = underTest.getQuickTip(parameters);

        assertThat(quickTip, instanceOf(FixedIntervalQuickTip.class));
    }

    @Test
    public void testGetQuickTip_configuredAlgorithm() {
        int algorithm = 3;
        int maxRandomNumber = 80;
        int piecesOfRandomNumbers = 5;
        int piecesOfSheets = 10;

        Parameters parameters = getParameters(algorithm, maxRandomNumber, piecesOfRandomNumbers, piecesOfSheets);

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