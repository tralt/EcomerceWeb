package screenPlay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import screenPlay.abilities.ProductInfo;

import java.util.ArrayList;
import java.util.List;

public class ResultsFPTShop {
    public static Question<List<ProductInfo>> get_Info_All_Items() {
        return new Get_Info_All_Items();
    }

    public static class Get_Info_All_Items implements Question<List<ProductInfo>>{
        @Override
        public List<ProductInfo> answeredBy(Actor actor) {
            List<ProductInfo> array = new ArrayList<>();
            WebDriver driver = BrowseTheWeb.as(actor).getDriver();

            List<WebElement> lstItems = driver.findElements(By.cssSelector("div.card.cs-card .cdt-product:not(.product-status)"));
            for (WebElement item :lstItems) {

                boolean isPromo = item.findElements(By.cssSelector(".cdt-product__show-promo .progress")).size() != 0;
                boolean isPrice = item.findElements(By.cssSelector(".cdt-product__price .price")).size() != 0;

                if (isPromo){
                    //name
                    String name = item.findElement(By.cssSelector(".cdt-product__info .cdt-product__name")).getText();

                    // price
                    String priceStr = item.findElement(By.cssSelector(".cdt-product__show-promo .progress")).getText().replace(" ₫", "");
                    String priceRpl = priceStr.replace(".", "");
                    Double price = Double.parseDouble(priceRpl);

                    // link
                    String link = item.findElement(By.cssSelector(".cdt-product__info .cdt-product__name")).getAttribute("href");

                    array.add((new ProductInfo.ProductInfoBuilder())
                            .withSiteName("FPT Shop")
                            .withProductName(name)
                            .withPrice(price)
                            .andLinkProduct(link));
                }
                else if (isPrice){
                    //name
                    String name = item.findElement(By.cssSelector(".cdt-product__info .cdt-product__name")).getText();

                    // price
                    String priceStr = item.findElement(By.cssSelector(".cdt-product__info .price")).getText().replace(" ₫", "");
                    String priceRpl = priceStr.replace(".", "");
                    Double price = Double.parseDouble(priceRpl);

                    // link
                    String link = item.findElement(By.cssSelector(".cdt-product__info .cdt-product__name")).getAttribute("href");

                    array.add((new ProductInfo.ProductInfoBuilder())
                            .withSiteName("FPT Shop")
                            .withProductName(name)
                            .withPrice(price)
                            .andLinkProduct(link));
                }

            }

            return array;
        }
    }
}
