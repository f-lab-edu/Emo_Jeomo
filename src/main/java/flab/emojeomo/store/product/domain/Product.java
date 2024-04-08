package flab.emojeomo.store.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import flab.emojeomo.global.enums.Status;
import flab.emojeomo.store.mall.domain.Mall;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mall_idx")
    private Mall mall;

    private String name;

    private int price;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreatedDate
    private LocalDateTime createdDate;

    // JPA 연관관계 설정하기
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<ProductImage> images = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<ProductOption> options = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<ProductTag> tags = new ArrayList<>();

    // 연관관계 편의 메서드
    private void addProductImage(ProductImage productImage) {
        images.add(productImage);
        productImage.setProduct(this);
    }

    private void addProductTag(ProductTag productTag) {
        tags.add(productTag);
        productTag.setProduct(this);
    }

    private void addProductOption(ProductOption productOption) {
        options.add(productOption);
        productOption.setProduct(this);
    }

    // 생성 메서드
    public static Product createProduct(Mall mall,
                                        String name,
                                        int price,
                                        List<ProductImage> images,
                                        List<ProductTag> tags,
                                        List<ProductOption> options) {
        Product product = new Product();
        product.setMall(mall);
        product.setName(name);
        product.setPrice(price);
        product.setCreatedDate(LocalDateTime.now());
        product.setStatus(Status.AVAILABLE);

        // 컬렉션 넣기
        for (ProductImage image : images) {
            product.addProductImage(image);
        }

        for (ProductTag tag : tags) {
            product.addProductTag(tag);
        }

        for (ProductOption option : options) {
            product.addProductOption(option);
        }

        return product;
    }

    private void setMall(Mall mall) {
        this.mall = mall;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setPrice(int price) {
        this.price = price;
    }

    private void setStatus(Status status) {
        this.status = status;
    }

    private void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
