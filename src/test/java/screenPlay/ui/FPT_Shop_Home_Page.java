package screenPlay.ui;


import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

@DefaultUrl("https://fptshop.com.vn/")
public class FPT_Shop_Home_Page extends PageObject {

    public static Target category_DIEN_THOAI = Target.the("Category: Dien Thoai")
            .located(By.cssSelector("nav.fs-menu ul.fs-mnul > li:first-child"));

    public static Target SEARCH_BOX = Target.the("Search Box")
            .located(By.cssSelector("input#key"));

    public static Target PRODUCT_LIST = Target.the("Product List")
            .located(By.cssSelector("div.card.cs-card"));

    public static Target FOOTER = Target.the("The Footer")
            .located(By.cssSelector("div.fs-footer"));
}
