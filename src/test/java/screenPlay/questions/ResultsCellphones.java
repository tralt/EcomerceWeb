package screenPlay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import screenPlay.abilities.ProductInfo;

import java.util.ArrayList;
import java.util.List;

public class ResultsCellphones {
    public static Question<List<ProductInfo>> get_Info_All_Product() {
        return new Get_Info_All_Product();
    }

    public static class Get_Info_All_Product implements Question<List<ProductInfo>>{
        @Override
        public List<ProductInfo> answeredBy(Actor actor) {
            List<ProductInfo> array = new ArrayList<>();
            WebDriver driver = BrowseTheWeb.as(actor).getDriver();

            List<WebElement> lstItem = driver.findElements(By.cssSelector("ul.dien-thoai li"));
            for (WebElement item : lstItem) {
                // price
                boolean isRegularPrice = item.findElements(By.cssSelector(".regular-price .price")).size() != 0;
                boolean isSpecialPrice = item.findElements(By.cssSelector(".special-price .price")).size() != 0;

                if (isRegularPrice)
                {
                    // name
                    String name = item.findElement(By.cssSelector(".lt-product-group-info h3")).getText();

                    // price
                    String priceStr = item.findElement(By.cssSelector(".regular-price .price")).getText().replace(" ₫", "");
                    String pricePlc = priceStr.replace(".", "");
                    Double price = Double.parseDouble(pricePlc);

                    // link
                    String link = item.findElement(By.cssSelector(".lt-product-group-info >a")).getAttribute("href");

                    // array
                    array.add((new ProductInfo.ProductInfoBuilder())
                            .withSiteName("Cellphones")
                            .withProductName(name)
                            .withPrice(price).andLinkProduct(link));

                }else if (isSpecialPrice)
                {
                    // name
                    String name = item.findElement(By.cssSelector(".lt-product-group-info h3")).getText();

                    // price
                    String priceStr = item.findElement(By.cssSelector(".special-price .price")).getText().replace(" ₫", "");
                    String pricePlc = priceStr.replace(".", "");
                    Double price = Double.parseDouble(pricePlc);

                    // link
                    String link = item.findElement(By.cssSelector(".lt-product-group-info >a")).getAttribute("href");

                    // array
                    array.add((new ProductInfo.ProductInfoBuilder())
                            .withSiteName("Cellphones")
                            .withProductName(name)
                            .withPrice(price).andLinkProduct(link));
                }
            }

            return array;
        }
    }
}
