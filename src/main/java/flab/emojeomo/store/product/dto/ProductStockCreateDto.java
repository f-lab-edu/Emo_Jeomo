package flab.emojeomo.store.product.dto;

import flab.emojeomo.store.product.domain.ProductOption;
import flab.emojeomo.store.product.domain.ProductStock;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductStockCreateDto {
    private Long optionIdx;
    private String size;
    private int count;

    public ProductStock createProductStock(ProductOption productOption) {
        return ProductStock.builder()
                           .option(productOption)
                           .size(this.size)
                           .count(this.count)
                           .build();
    }
}
