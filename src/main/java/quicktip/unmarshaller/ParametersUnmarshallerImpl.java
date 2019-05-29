package quicktip.unmarshaller;

import quicktip.bean.unmarshaller.Parameters;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ParametersUnmarshallerImpl implements ParametersUnmarshaller {

    public Parameters unmarshal(String path) {
        Parameters parameters = null;

        try {
            File file = new File(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(Parameters.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            parameters = (Parameters) jaxbUnmarshaller.unmarshal(file);

            validate(parameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("An error occurred during unmarshalling the file in the specified path: " + path, e);
        }

        return parameters;
    }

    private void validate(Parameters parameters) {
        if (parameters.getAlgorithm() == null) {
            throw new IllegalArgumentException("Parameter <algorithm> must be set");
        }
        if (parameters.getPiecesOfRandomNumbers() == null) {
            throw new IllegalArgumentException("Parameter <piecesOfRandomNumbers> must be set");
        }

        switch (parameters.getAlgorithm()) {
            case 1:
                if (parameters.getMaxRandomNumber() == null) {
                    throw new IllegalArgumentException("Parameter <maxRandomNumber> must be set");
                }
                break;
            case 2:
                if (parameters.getPiecesOfSheets() == null) {
                    throw new IllegalArgumentException("Parameter <piecesOfSheets> must be set");
                }
                break;
            case 3:
                if (parameters.getPiecesOfSheets() == null) {
                    throw new IllegalArgumentException("Parameter <piecesOfSheets> must be set");
                }
                if (parameters.getMaxRandomNumber() == null) {
                    throw new IllegalArgumentException("Parameter <maxRandomNumber> must be set");
                }
                break;
            default:
                throw new IllegalArgumentException("Parameter <algorithm> must be 1, 2, or 3");
        }
    }
}
