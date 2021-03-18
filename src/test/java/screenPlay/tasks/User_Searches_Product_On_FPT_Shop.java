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
import screenPlay.ui.FPT_Shop_Home_Page;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class User_Searches_Product_On_FPT_Shop implements Task {

    public enum Types{
        DIEN_THOAI, EMPTY;

        @Override
        public String toString(){
            return super.toString().toLowerCase();
        }
    }

    private Types types;
    private String term;

    public User_Searches_Product_On_FPT_Shop(Types types, String term){
        this.types = types;
        this.term = term;
    }

    public static SearchItemOnFPTShopBuilder withProductType(Types types) {
        return new SearchItemOnFPTShopBuilder(types);
    }

    @Step("{0} searches products on the FPT Shop with category as '#types' and term as '#term'")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(

                Check.whether(this.types == Types.DIEN_THOAI).andIfSo(
                        Click.on(FPT_Shop_Home_Page.category_DIEN_THOAI)
                ),

                Enter.theValue(this.term).into(FPT_Shop_Home_Page.SEARCH_BOX).thenHit(Keys.ENTER),
                WaitUntil.the(FPT_Shop_Home_Page.PRODUCT_LIST, WebElementStateMatchers.isVisible()).forNoMoreThan(500).seconds(),
                Scroll.to(FPT_Shop_Home_Page.FOOTER)
        );
    }

    public static class SearchItemOnFPTShopBuilder{
        private Types types;
        private String term;

        public SearchItemOnFPTShopBuilder(Types types){
            this.types = types;
        }

        public SearchItemOnFPTShopBuilder andTerm(String term) {
            this.term = term;
            return this;
        }

        public User_Searches_Product_On_FPT_Shop build() {
            return instrumented(User_Searches_Product_On_FPT_Shop.class, types, term);
        }
    }
}
