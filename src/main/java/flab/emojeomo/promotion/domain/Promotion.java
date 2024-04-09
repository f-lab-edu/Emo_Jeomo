package flab.emojeomo.promotion.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import flab.emojeomo.global.enums.Status;
import flab.emojeomo.global.enums.MallType;
import flab.emojeomo.store.mall.domain.Mall;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Promotion {
    @Id
    @GeneratedValue
    private Long id;

    private Status status;

    @Enumerated(EnumType.STRING)
    private PromotionType type;

    private int discount; // 할인율 또는 할인 금액

    private int minAmount; // 최소 구매 금액

    private int maxAmount; // 최대 할인 한도 (정률할인인 경우에만)

    @Enumerated(EnumType.STRING)
    private MallType mallType; // 대상 쇼핑몰 유형

    private LocalDateTime startDate; // 프로모션 시작 일자

    private LocalDateTime finishDate; // 종료일자

    @OneToMany(mappedBy = "promotionMall")
    private List<PromotionMall> promotionMalls = new ArrayList<>();

    /**
     * TODO
     * 프로모션 자체애 대한 설명은 ..
     * 뭔가 이미지로만 들어가니까 생략?
     * 나중에 프로모션 탭에 들어갈 이미지들 저장하는 테이블을 따로 만드는 것도 좋을 듯
     */

    // 생성 메서드
    public Promotion createPromotion(PromotionType type,
                                int discount,
                                int minAmount,
                                int maxAmount,
                                MallType mallType,
                                LocalDateTime startDate,
                                LocalDateTime endDate,
                                PromotionMall... promotionMalls) {

        Promotion promotion = new Promotion();
        promotion.setType(type);
        promotion.setDiscount(discount);
        promotion.setMinAmount(minAmount);
        promotion.setMallType(mallType);
        promotion.setStartDate(startDate);
        promotion.setFinishDate(endDate);

        if (type == PromotionType.RATE) {
            // 정률할인이라면 최대 한도도 필요
            promotion.setMaxAmount(maxAmount);
        }

        for (PromotionMall mall : promotionMalls) {
            promotion.addPromotionMall(mall);
        }

        return promotion;
    }

    // 컬렉션 만들기
    private void addPromotionMall(PromotionMall promotionMall) {
        promotionMalls.add(promotionMall);
        promotionMall.setPromotion(this);
    }


    private void setType(PromotionType type) {
        this.type = type;
    }

    private void setDiscount(int discount) {
        this.discount = discount;
    }

    private void setMinAmount(int minAmount) {
        this.minAmount = minAmount;
    }

    private void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    private void setMallType(MallType mallType) {
        this.mallType = mallType;
    }

    private void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    private void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }
}
