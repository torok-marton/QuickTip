package quicktip.service;

import quicktip.bean.response.BaseResponse;

public interface QuickTip {

    /**
     * Generate random numbers based on the input parameters
     * @return generated random numbers
     */
    BaseResponse generateRandomNumbers();
}
