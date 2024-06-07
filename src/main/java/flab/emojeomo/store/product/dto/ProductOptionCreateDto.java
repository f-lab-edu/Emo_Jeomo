package flab.emojeomo.store.product.dto;

import flab.emojeomo.global.enums.Status;
import flab.emojeomo.store.product.domain.Product;
import flab.emojeomo.store.product.domain.ProductOption;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductOptionCreateDto {
    private Long productIdx;
    private String option;
    private Status status;

    // 생성 메서드
    public ProductOption makeProductOption(Product product) {
        return ProductOption.builder()
                            .product(product)
                            .option(this.option)
                            .status(this.status)
                            .build();
    }
}
