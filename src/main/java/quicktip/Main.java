package quicktip;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import quicktip.bean.response.BaseResponse;
import quicktip.bean.response.FilledSheet;
import quicktip.configuration.QuickTipConfiguration;
import quicktip.service.QuickTip;

public class Main
{

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(QuickTipConfiguration.class);

        QuickTip quickTip = (QuickTip) ctx.getBean("quickTip");
        BaseResponse filledSheets = quickTip.generateRandomNumbers();

        for (FilledSheet filledSheet : filledSheets.getFilledSheets()) {
            System.out.println(filledSheet.getNumbers());
        }

        ctx.close();
    }
}
