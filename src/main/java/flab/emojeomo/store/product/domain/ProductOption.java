package flab.emojeomo.store.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import flab.emojeomo.global.enums.Status;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
public class ProductOption {
    @Id
    @GeneratedValue
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_idx")
    private Product product;

    private String option; // 옷 색상, 화장품 색조 호/ 등의 옵션

    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonIgnore
    @OneToMany(mappedBy = "option")
    private List<ProductStock> stocks = new ArrayList<>();

    public void addProductStock(ProductStock stock) {
        stocks.add(stock);
        stock.setOption(this);
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}