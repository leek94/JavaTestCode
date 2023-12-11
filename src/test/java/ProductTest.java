import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    Product prod = new Product();
    int price = 4500;
    int saleRate = 30;

    @BeforeEach
    public void setup() {
        prod.setProdPrice(price);
        prod.setProdSaleRate(saleRate);
    }

    @Test
    @DisplayName("Original product price")
    public void originalPrice() throws Exception {

        assertTrue(
                price == prod.getProdPrice(),"This is a original price");
    }
    @Test
    public void failPrice() throws Exception {
        assertEquals(
                price == prod.getProdSalePrice(), "It will be fail");
    }

    @Test
    public void salePrice() throws Exception {
        assertTrue(
                (int)price - price * (0.01 * saleRate)
                == prod.getProdSalePrice(), "This is a sale price");
    }


}