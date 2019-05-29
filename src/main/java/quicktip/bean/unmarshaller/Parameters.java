package quicktip.bean.unmarshaller;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Parameters {
    private Integer piecesOfSheets;
    private Integer algorithm;
    private Integer maxRandomNumber;
    private Integer piecesOfRandomNumbers;

    public Integer getPiecesOfSheets() {
        return piecesOfSheets;
    }

    @XmlElement
    public void setPiecesOfSheets(Integer piecesOfSheets) {
        this.piecesOfSheets = piecesOfSheets;
    }

    public Integer getAlgorithm() {
        return algorithm;
    }

    @XmlElement
    public void setAlgorithm(Integer algorithm) {
        this.algorithm = algorithm;
    }

    public Integer getMaxRandomNumber() {
        return maxRandomNumber;
    }

    @XmlElement
    public void setMaxRandomNumber(Integer maxRandomNumber) {
        this.maxRandomNumber = maxRandomNumber;
    }

    public Integer getPiecesOfRandomNumbers() {
        return piecesOfRandomNumbers;
    }

    @XmlElement
    public void setPiecesOfRandomNumbers(Integer piecesOfRandomNumbers) {
        this.piecesOfRandomNumbers = piecesOfRandomNumbers;
    }
}
