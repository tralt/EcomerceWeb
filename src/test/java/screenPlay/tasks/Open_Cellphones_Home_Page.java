package screenPlay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;
import screenPlay.ui.CellPhones_Home_Page;

public class Open_Cellphones_Home_Page implements Task {

    CellPhones_Home_Page cellPhones_home_page;

    @Step("{0} OPENS THE CELLPHONES HOME PAGE")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn().the(cellPhones_home_page),
                WaitUntil.the(CellPhones_Home_Page.SEARCH_BOX, WebElementStateMatchers.isVisible()).forNoMoreThan(500).seconds()
        );
    }
}
