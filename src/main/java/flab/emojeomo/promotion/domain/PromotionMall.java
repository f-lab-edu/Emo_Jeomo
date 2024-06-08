package flab.emojeomo.promotion.domain;

import flab.emojeomo.store.mall.domain.Mall;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class PromotionMall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mall_idx")
    private Mall mall;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_idx")
    private Promotion promotion;

    // 연관관계 편의 메서드
    public void setMall(Mall mall) {
        this.mall = mall;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    // 생성 메서드
    public static PromotionMall createPromotionMall(Mall mall, Promotion promotion) {
        PromotionMall promotionMall = new PromotionMall();
        promotionMall.setMall(mall);
        promotionMall.setPromotion(promotion);
        return promotionMall;
    }
}
