package screenPlay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.Keys;
import screenPlay.ui.CellPhones_Home_Page;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class User_Search_Mobile implements Task {

    public enum Locations{
        HA_NOI, HO_CHI_MINH, BINH_DUONG, EMPTY;

        @Override
        public String toString(){
            return super.toString().toLowerCase();
        }
    }

    private String name;
    private Locations locations;

    public User_Search_Mobile( Locations locations, String name){
        this.locations = locations;
        this.name = name;
    }

    public static SearchItemBuilder atLocation(Locations locations) {
        return new SearchItemBuilder(locations);
    }


    @Step("{0} searches mobile with name as '#name' at the 'locations' city")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Check.whether(this.locations == Locations.BINH_DUONG).andIfSo(
                        Click.on(CellPhones_Home_Page.LOCATION_DROPDOWN),
                        Click.on(CellPhones_Home_Page.local_BINH_DUONG)
                ).otherwise(
                        Check.whether(this.locations == Locations.HA_NOI).andIfSo(
                                Click.on(CellPhones_Home_Page.LOCATION_DROPDOWN),
                                Click.on(CellPhones_Home_Page.local_HA_NOI)
                        ).otherwise(
                                Check.whether(this.locations == Locations.HO_CHI_MINH).andIfSo(
                                        Click.on(CellPhones_Home_Page.LOCATION_DROPDOWN),
                                        Click.on(CellPhones_Home_Page.local_HO_CHI_MINH)
                                )
                        )
                ),

                Enter.theValue(this.name).into(CellPhones_Home_Page.SEARCH_BOX).thenHit(Keys.ENTER),
                WaitUntil.the(CellPhones_Home_Page.label_DIEN_THOAI, WebElementStateMatchers.isVisible()).forNoMoreThan(500).seconds(),
                Scroll.to(CellPhones_Home_Page.FOOTER)
        );
    }

    public static class SearchItemBuilder
    {
        private Locations locations;
        private String name;

        public SearchItemBuilder(Locations locations){
            this.locations = locations;
        }

        public SearchItemBuilder withProductName(String name) {
            this.name = name;
            return this;
        }

        public User_Search_Mobile build() {
            return instrumented(User_Search_Mobile.class, locations, name);
        }
    }
}
