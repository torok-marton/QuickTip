package quicktip.bean.input;

public class ConfiguredQuickTipInput extends BaseInput {
    private int piecesOfSheets;
    private int maxRandomNumber;

    public int getPiecesOfSheets() {
        return piecesOfSheets;
    }

    public void setPiecesOfSheets(int piecesOfSheets) {
        this.piecesOfSheets = piecesOfSheets;
    }

    public int getMaxRandomNumber() {
        return maxRandomNumber;
    }

    public void setMaxRandomNumber(int maxRandomNumber) {
        this.maxRandomNumber = maxRandomNumber;
    }
}
