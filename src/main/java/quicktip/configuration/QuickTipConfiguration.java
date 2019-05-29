package quicktip.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import quicktip.bean.input.ConfiguredQuickTipInput;
import quicktip.bean.input.FixedIntervalQuickTipInput;
import quicktip.bean.input.OneSheetQuickTipInput;
import quicktip.bean.unmarshaller.Parameters;
import quicktip.service.ConfiguredQuickTip;
import quicktip.service.FixedIntervalQuickTip;
import quicktip.service.OneSheetQuickTip;
import quicktip.service.QuickTip;
import quicktip.unmarshaller.ParametersUnmarshaller;
import quicktip.unmarshaller.ParametersUnmarshallerImpl;

@Configuration
public class QuickTipConfiguration {

    private static final int ONE_SHEET_ALGORITHM = 1;
    private static final int FIXED_INTERVAL_ALGORITHM = 2;
    private static final int CONFIGURED_ALGORITHM = 3;

    @Value("#{systemProperties['path'] ?: 'parameters.xml'}")
    private String path;

    @Bean
    public ParametersUnmarshaller getParametersUnmarshaller() {
        return new ParametersUnmarshallerImpl();
    }

    @Bean
    public Parameters getParameters(ParametersUnmarshaller parametersUnmarshaller) {
        return parametersUnmarshaller.unmarshal(path);
    }

    @Bean(name = "quickTip")
    public QuickTip getQuickTip(Parameters parameters) {
        QuickTip quickTip = null;

        switch (parameters.getAlgorithm()) {
            case ONE_SHEET_ALGORITHM:
                quickTip = new OneSheetQuickTip(getOneSheetQuickTipInput(parameters));
                break;
            case FIXED_INTERVAL_ALGORITHM:
                quickTip = new FixedIntervalQuickTip(getFixedIntervalQuickTipInput(parameters));
                break;
            case CONFIGURED_ALGORITHM:
                quickTip = new ConfiguredQuickTip(getConfiguredQuickTipInput(parameters));
                break;
            default:
                break;
        }
        return quickTip;
    }

    private ConfiguredQuickTipInput getConfiguredQuickTipInput(Parameters parameters) {
        ConfiguredQuickTipInput configuredQuickTipInput = new ConfiguredQuickTipInput();
        configuredQuickTipInput.setMaxRandomNumber(parameters.getMaxRandomNumber());
        configuredQuickTipInput.setPiecesOfSheets(parameters.getPiecesOfSheets());
        configuredQuickTipInput.setPiecesOfRandomNumbers(parameters.getPiecesOfRandomNumbers());
        return configuredQuickTipInput;
    }

    private FixedIntervalQuickTipInput getFixedIntervalQuickTipInput(Parameters parameters) {
        FixedIntervalQuickTipInput fixedIntervalQuickTipInput = new FixedIntervalQuickTipInput();
        fixedIntervalQuickTipInput.setPiecesOfRandomNumbers(parameters.getPiecesOfRandomNumbers());
        fixedIntervalQuickTipInput.setPiecesOfSheets(parameters.getPiecesOfSheets());
        return fixedIntervalQuickTipInput;
    }

    private OneSheetQuickTipInput getOneSheetQuickTipInput(Parameters parameters) {
        OneSheetQuickTipInput oneSheetQuickTipInput = new OneSheetQuickTipInput();
        oneSheetQuickTipInput.setPiecesOfRandomNumbers(parameters.getPiecesOfRandomNumbers());
        oneSheetQuickTipInput.setMaxRandomNumber(parameters.getMaxRandomNumber());
        return oneSheetQuickTipInput;
    }
}
