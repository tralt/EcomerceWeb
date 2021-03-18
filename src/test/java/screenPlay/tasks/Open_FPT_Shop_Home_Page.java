package screenPlay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;
import screenPlay.ui.FPT_Shop_Home_Page;

public class Open_FPT_Shop_Home_Page implements Task {

    FPT_Shop_Home_Page fpt_shop_home_page;

    @Step("{0} opens FPT Shop Home page")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn().the(fpt_shop_home_page)
                //WaitUntil.the(FPT_Shop_Home_Page.PRODUCT_LIST, WebElementStateMatchers.isVisible()).forNoMoreThan(500).seconds()
        );
    }
}
