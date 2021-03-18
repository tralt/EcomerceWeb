package screenPlay.abilities;

public class ProductInfo {

    public String site;
    public String productName;
    public Double price;
    public String link;

    public ProductInfo(String site, String productName, Double price, String link){
        this.site = site;
        this.productName = productName;
        this.price = price;
        this.link = link;
    }

    public static class ProductInfoBuilder{
        public String site;
        public String productName;
        public Double price;
        public String link;

        public ProductInfoBuilder withSiteName(String site){
            this.site = site;
            return this;
        }

        public ProductInfoBuilder withProductName(String productName){
            this.productName = productName;
            return this;
        }

        public ProductInfoBuilder withPrice(Double price){
            this.price = price;
            return this;
        }

        public ProductInfo andLinkProduct(String link){
            return new ProductInfo(this.site, this.productName, this.price, this.link);
        }
    }
}
