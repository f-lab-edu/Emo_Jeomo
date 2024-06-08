package flab.emojeomo.store.product.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class ProductStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="option_idx")
    private ProductOption option;

    private String size;

    private int count;

    // 연관관계 정의 메서드
    public void setOption(ProductOption productOption) {
        this.option = productOption;
    }

    // 생성 메서드
    public void createProductStock(String size, int count) {
        ProductStock.builder().size(size).count(count).build();
    }
}
