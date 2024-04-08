package flab.emojeomo.store.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import flab.emojeomo.global.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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
    @OneToMany(mappedBy = "product_option")
    private List<ProductStock> stocks = new ArrayList<>();

    // private setter
    private void setStatus(Status status) {
        this.status = status;
    }

    private void setOption(String option) {
        this.option = option;
    }

    // 연관관계 메서드
    public void setProduct(Product product) {
        this.product = product;
    }

    public void addProductStock(ProductStock stock) {
        stocks.add(stock);
        stock.setOption(this);
    }

    // 생성 메서드
    public ProductOption createProductOption(String option, ProductStock... productStock) {
        ProductOption productOption = new ProductOption();
        productOption.setOption(option);
        productOption.setStatus(Status.AVAILABLE);

        for (ProductStock stock : productStock) {
            productOption.addProductStock(stock);
        }

        // TODO : Product product는 뭔가 createProduct할 때 세팅 ?
        return productOption;
    }
}