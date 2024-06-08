package flab.emojeomo.store.product.dto;

import flab.emojeomo.global.enums.Status;
import flab.emojeomo.store.product.domain.Product;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ProductInfoUpdateDto {
    private Long idx;
    private String name;
    private int price;
    private Status status;

    public Product toProductEntity(Long mallIdx) {
        return Product.builder()
                      .mallIdx(mallIdx)
                      .name(this.name)
                      .price(this.price)
                      .status(this.status)
                      .modifiedDate(LocalDateTime.now())
                      .build();
    }
}

