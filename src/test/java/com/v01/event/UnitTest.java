package com.v01.event;

import com.v01.event.domain.balance.Balance;
import com.v01.event.domain.balance.BalanceService;
import com.v01.event.domain.product.Product;
import com.v01.event.domain.product.ProductService;
import com.v01.event.infra.balance.BalanceLocalDatabase;
import com.v01.event.infra.product.ProductLocalDatabase;
import com.v01.event.interfaces.model.dto.req.ReqChargeBalanceDto;
import com.v01.event.interfaces.model.dto.req.ReqDecreaseStockDto;
import com.v01.event.interfaces.model.dto.req.ReqUseBalanceDto;
import com.v01.event.interfaces.model.param.ReqValidateNoDuplicateItemsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UnitTest {

    @Autowired
    ProductService productService;

    @Autowired
    BalanceService balanceService;

    @Autowired
    BalanceLocalDatabase balanceLocalDatabase;

    @Autowired
    ProductLocalDatabase productLocalDatabase;

    /*
      todo
        product(ProductId):1L,2L / Balance(BalanceId,UserId):1L,2L
        각 케이스마다 해당 ID값 Default 저장
    */

    @BeforeEach
    void setUp() {
        Product defaultProduct = new Product("컴퓨터", 1_000_000L, 10L);
        Product defaultProduct2 = new Product("마우스", 10000L, 10L);
        productLocalDatabase.save(defaultProduct);
        productLocalDatabase.save(defaultProduct2);

        Balance defaultUser = new Balance(1L,100_000_000L);
        Balance defaultUser2 = new Balance(2L,0L);

        balanceLocalDatabase.save(defaultUser);
        balanceLocalDatabase.save(defaultUser2);

    }

    @DisplayName("요청 상품을 찾지 못하면 예외를 발생시킨다")
    @Test
    void notFountProduct(){
        ReqDecreaseStockDto dto  = new ReqDecreaseStockDto();
            dto.setProductId(3L);
            dto.setQuantity(3L);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.decreaseAbleStock(dto);
        });

        assertEquals("Product not found : "+ dto.getProductId(),exception.getMessage());
    }

    @DisplayName("요청 상품 개수보다 재고가 부족하면 예외를 발생시킨다")
    @Test
    void notEnoughStockAvailable() {
        ReqDecreaseStockDto dto = new ReqDecreaseStockDto();
        dto.setProductId(1L);
        dto.setQuantity(11L);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.decreaseAbleStock(dto);
        });

        assertEquals("재고가 부족합니다.", exception.getMessage());
    }

    @DisplayName("결제 파라미터에 중복된 상품 ID가 존재하면 예외를 발생시킨다")
    @Test
    void duplicateProductIdsExistInPaymentParam() {
        List<ReqValidateNoDuplicateItemsDto> listProduct = List.of(
                new ReqValidateNoDuplicateItemsDto(1L, 1L),
                new ReqValidateNoDuplicateItemsDto(1L, 2L)
        );

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.validateNoDuplicateProducts(listProduct);
        });

        assertEquals("중복된 상품 ID가 포함되어 있습니다: 1", exception.getMessage());
    }

    @DisplayName("중복 상품 ID가 없으면 예외가 발생하지 않는다")
    @Test
    void noDuplicateProductIdsInPaymentParam() {
        List<ReqValidateNoDuplicateItemsDto> listProduct = List.of(
                new ReqValidateNoDuplicateItemsDto(1L, 1L),
                new ReqValidateNoDuplicateItemsDto(2L, 2L)
        );

        assertDoesNotThrow(() -> {
            productService.validateNoDuplicateProducts(listProduct);
        });
    }

    @DisplayName("잔액 테이블에 요청유저가 존재하지 않으면 예외를 발생시킨다")
    @Test
    void notFoundInBalanceTable() {
        ReqUseBalanceDto dto = new ReqUseBalanceDto(5L,10000L);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            balanceService.usableBalance(dto);
        });

        assertEquals(dto.getUserId().toString() + " : 해당 유저가 존재하지 않습니다",exception.getMessage());
    }

    @DisplayName("요청상품가격 보다 유저의 잔액이 부족하면 예외를 발생시킨다")
    @Test
    void notEnoughBalanceThenProductPrice() {
        ReqUseBalanceDto dto = new ReqUseBalanceDto(1L,100_000_001L);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            balanceService.usableBalance(dto);
        });

        assertEquals("잔액이 부족합니다.",exception.getMessage());
    }

    @DisplayName("잔액이 정상적으로 충전된다.")
    @Test
    void chargeBalance() {
        Long userId = 2L;
        ReqChargeBalanceDto dto = new ReqChargeBalanceDto(userId, 5000L);
        Balance updated = balanceService.chargeableBalance(dto);

        assertEquals(5000L, updated.getAmount());
    }

    @DisplayName("잔액이 정상적으로 사용된다.")
    @Test
    void useBalance() {
        Long userId = 3L;
        balanceLocalDatabase.save(new Balance(userId,5000L));

        ReqUseBalanceDto dto = new ReqUseBalanceDto(userId, 5000L);
        Balance updated = balanceService.usableBalance(dto);

        assertEquals(0, updated.getAmount());
    }

    // todo 상품조회
    @DisplayName("productId에 해당하는 상품이 정상적으로 조회된다.")
    @Test
    void fetchProductById(){
        Long productId = 2L;
        String productName = "마우스";
        Long productPrice = 10000L;

        Product product = productService.findByProductId(productId);

        assertEquals(productId, product.getProductId());
        assertEquals(productName, product.getProductName());
        assertEquals(productPrice, product.getProductPrice());
    }
}
