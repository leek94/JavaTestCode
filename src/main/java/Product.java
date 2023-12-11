public class Product {
    int prodId;
    String prodName;
    int prodPrice;
    int prodSaleRate;

    public int getProdId() {
        return this.prodId;
    }

    public String getProdName() {

        return this.prodName;
    }

    public int getProdPrice() {
        return this.prodPrice;
    }

    public void setProdPrice(int prodPrice){
        this.prodPrice = prodPrice;
    }

    public int getProdSaleRate() {
        return this.prodSaleRate;
    }

    public void setProdSaleRate(int prodSaleRate){
        if(prodSaleRate > 100) prodSaleRate = 100;
        if(prodSaleRate < 0) prodSaleRate = 0;
        this.prodSaleRate = prodSaleRate;
    }

    public int getProdSalePrice() {
        return (int)(this.prodPrice - this.prodPrice * (0.01 * this.prodSaleRate));
    }

}
