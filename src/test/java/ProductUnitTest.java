import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ProductUnitTest {

    @Mock
    Product product; // mock 객체 생성

    @Test
    public void mockTest(){
        MockitoAnnotations.initMocks(this);
        //OR Product product = mock(product.class);
        //위와 같은 내용으로 IoC 가 적용되어 mockito 에서 객체를 생성한다.

        assertTrue(product != null);

        System.out.println(product.getProdId()+""); // 0으로 초기화
        System.out.println(product.getProdName()+""); // null로 초기화
    }

    @Test
    public void mockDataTest() {
        MockitoAnnotations.initMocks(this);

        assertTrue(product != null);

        // when을 사용해서 메소드 호출시 특정값을 반환하도록 지정9
        when(product.getProdId()).thenReturn(3);
        when(product.getProdName()).thenReturn("Black M2M T-Shirt");
        when(product.getProdPrice()).thenReturn(13000);
        when(product.getProdSaleRate()).thenReturn(20);

        System.out.println(product.getProdId()+"");
        System.out.println(product.getProdName()+"");
        System.out.println(product.getProdPrice()+""); // 13000
        System.out.println(product.getProdSaleRate()+""); // 20
        System.out.println(product.getProdSalePrice()+""); // 0

        // assertEquals(기대하는 값, 실제값, 실패시 출력되는 문장)
        assertEquals(product.getProdId(), 3, "Product ID");
        assertEquals(product.getProdName(), "Black M2M T-Shirt", "Product Name");
        assertEquals(product.getProdPrice(), 13000, "Origin Price");
//        assertEquals(product.getProdSalePrice(), 10400, "Sale Price");
        // 왜 값이 틀리다고 나오는건지 getProdSalePrice()는 가격과 할인율을 계산하는데, Mockito는 product 객체의 각 메서드
        // 리턴값만 변경하는 것이지 실제 값들을 변경하는게 아니므로 getProdSalePrice() 메서드를 사용해서 값을 받아올 수 없음
    }

    // doThrow와 thenThrow

    // void method에서만 doThrow 사용 가능
    @Test
    public void mockDoThrowTest() {
        MockitoAnnotations.initMocks(this);

        assertTrue(product != null);

        //doThrow
        doThrow(new Exception("Do Throw Exception")).when(product).setProdPrice(eq(10000));
        product.setProdPrice(100);
        product.setProdPrice(1000);
        product.setProdPrice(10000); // Exception!!
        product.setProdPrice(100000);
    }

    @Test
    public void mockThenThrowTest() {
        MockitoAnnotations.initMocks(this);

        assertTrue(product != null);

        when(product.getProdPrice())
                .thenThrow(new RuntimeException("Then Throw Exception"))
                .thenReturn(10000);
            // try catch로 에러 잡고 밑에 정상 동작하므로 성공으로 뜸
        try {
            product.getProdPrice(); // 이미 에러 발생생
        } catch(Exception e){}

        assertEquals(product.getProdPrice(), 10000, "Origin Price"); // 정상 동작
    }

    @Test
    public void mockListThenThrowTest() {
        MockitoAnnotations.initMocks(this);

        assertTrue(product != null);
        when(product.getProdName()).thenReturn("Normal Obj");
        ArrayList<Product> prodList = mock(ArrayList.class);

        when(prodList.get(0))
                .thenReturn(product);
        when(prodList.get(1))
                .thenThrow(new RuntimeException("Then Throw Exception"));

        System.out.println(prodList.get(0).getProdName()+"");
        System.out.println(prodList.get(1).getProdName()+""); // Error!

    }
}

