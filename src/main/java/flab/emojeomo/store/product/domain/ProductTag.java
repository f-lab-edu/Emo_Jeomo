package flab.emojeomo.store.product.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class ProductTag {
    @Id
    @GeneratedValue
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_idx")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_idx")
    private Tag tag;

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
