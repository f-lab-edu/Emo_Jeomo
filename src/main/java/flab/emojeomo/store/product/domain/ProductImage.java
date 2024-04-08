package flab.emojeomo.store.product.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class ProductImage {
    @Id
    @GeneratedValue
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_idx")
    private Product product;

    private String img;

    // 생성 메서드
    public void setProduct(Product product) {
        this.product = product;
    }
    public static ProductImage createProductImage(Product product, String image) {
        return ProductImage.builder().product(product).img(image).build();
    }


}
