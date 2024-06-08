package flab.emojeomo.store.product.dto;
import flab.emojeomo.global.enums.Status;
import flab.emojeomo.store.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDto {
    private Long mallIdx;

    private String name;

    private int price;

    @Builder.Default
    private Status status = Status.AVAILABLE;

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
