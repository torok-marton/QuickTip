package quicktip.bean.response;

import java.util.ArrayList;
import java.util.List;

public class BaseResponse {
    private List<FilledSheet> filledSheets;

    public List<FilledSheet> getFilledSheets() {
        if (filledSheets == null) {
            filledSheets = new ArrayList<>();
        }
        return filledSheets;
    }

    public void setFilledSheets(List<FilledSheet> filledSheets) {
        this.filledSheets = filledSheets;
    }
}
