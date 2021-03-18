package screenPlay.ui;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

@DefaultUrl("https://cellphones.com.vn/")
public class CellPhones_Home_Page extends PageObject {

    // Head
    public static Target SEARCH_BOX = Target.the("The Search box")
            .located(By.cssSelector("input#search"));

    public static Target LOCATION_DROPDOWN = Target.the("The Location Dropdown")
            .located(By.cssSelector("div.box-main__box-local >a"));

    public static Target local_HA_NOI = Target.the("Ha Noi")
            .located(By.cssSelector("div.box-main__box-local .dropdown-menu >li:first-child"));

    public static Target local_HO_CHI_MINH = Target.the("Ho Chi Minh")
            .located(By.cssSelector("div.box-main__box-local .dropdown-menu >li:nth-child(2)"));

    public static Target local_BINH_DUONG = Target.the("Binh Duong")
            .located(By.cssSelector("div.box-main__box-local .dropdown-menu >li:nth-child(3)"));

    // Result
    public static Target label_DIEN_THOAI = Target.the("Dien Thoai label")
            .located(By.xpath("//div[@data-id='dien-thoai']"));

    // footer
    public static Target FOOTER = Target.the("the Footer")
            .located(By.cssSelector("div.footer__box-information"));


}
