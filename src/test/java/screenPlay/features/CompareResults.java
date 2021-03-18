package screenPlay.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import screenPlay.abilities.ProductInfo;
import screenPlay.questions.ResultsCellphones;
import screenPlay.questions.ResultsFPTShop;
import screenPlay.tasks.*;

import java.util.*;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag("compareResult")
})
public class CompareResults {

    Actor tra = Actor.named("Tra");

    @Managed
    WebDriver traDriver;

    @Before
    public void Get_The_Stage(){
        tra.can(BrowseTheWeb.with(traDriver));
    }

    @Steps
    Open_Cellphones_Home_Page open_cellphones_home_page;

    @Steps
    Open_FPT_Shop_Home_Page open_fpt_shop_home_page;

    @Test
    public void Compare_Results_Between_Cellphones_And_FPT_Shop(){
        tra.wasAbleTo(
                open_cellphones_home_page,
                User_Search_Mobile
                        .atLocation(User_Search_Mobile.Locations.HA_NOI)
                        .withProductName("iphone 11")
                        .build()
        );

        List<ProductInfo> lst_1 = tra.asksFor(ResultsCellphones.get_Info_All_Product());

        tra.attemptsTo(
                open_fpt_shop_home_page,
                User_Searches_Product_On_FPT_Shop
                        .withProductType(User_Searches_Product_On_FPT_Shop.Types.DIEN_THOAI)
                        .andTerm("iphone 11")
                        .build());

        List<ProductInfo> lst_2 = tra.asksFor(ResultsFPTShop.get_Info_All_Items());

        List<ProductInfo> CellphonesLst = new ArrayList<>();
        for (ProductInfo item : lst_1) {
            String site = item.site;
            String product_name = item.productName;
            Double price = item.price;
            String link = item.link;

            CellphonesLst.add((new ProductInfo.ProductInfoBuilder())
                    .withSiteName(site)
                    .withProductName(product_name)
                    .withPrice(price)
                    .andLinkProduct(link));
        }

        List<ProductInfo> FPTLst = new ArrayList<>();
        for (ProductInfo item : lst_2) {
            String site = item.site;
            String product_name = item.productName;
            Double price = item.price;
            String link = item.link;

            FPTLst.add((new ProductInfo.ProductInfoBuilder())
                    .withSiteName(site)
                    .withProductName(product_name)
                    .withPrice(price)
                    .andLinkProduct(link));
        }

        // combine 2 arrays
         CellphonesLst.addAll(FPTLst);

        List<ProductInfo> lstProductDetailAfter = new ArrayList<>();
        Collections.sort(CellphonesLst, new Comparator<ProductInfo>() {
            @Override
            public int compare(ProductInfo o2, ProductInfo o1) { // By swapping the order of o1 and o2 in the comparator, we induce a sort order.
                int comparison = 0;
                comparison = o1.price.compareTo(o1.price);
                return  comparison;
            }
        });

        lstProductDetailAfter.addAll(CellphonesLst);

        for (ProductInfo item : lstProductDetailAfter) {
            System.out.println(item.site + " ,"+ item.productName + " ,"+ item.price+" ,"+ item.link);
        }

    }
}
