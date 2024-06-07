package flab.emojeomo.store.product.dto;
import flab.emojeomo.global.enums.Status;
import flab.emojeomo.store.product.domain.Product;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ProductCreateDto {
    private Long mallIdx;

    private String name;

    private int price;

    private Status status;

    public Product toProductEntity() {
        return Product.builder()
                      .mallIdx(mallIdx)
                      .name(name)
                      .price(price)
                      .status(status)
                      .createdDate(LocalDateTime.now())
                      .build();
    }
}
