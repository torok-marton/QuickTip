package quicktip.unmarshaller;

import quicktip.bean.unmarshaller.Parameters;

public interface ParametersUnmarshaller {

    /**
     * Unmarshal and validate parameters from XML file
     * @param path path to input XML file
     * @return program parameters
     */
    Parameters unmarshal(String path);
}
