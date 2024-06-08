package flab.emojeomo.global.enums;

// 상품, 옵션, 쇼핑몰등의 상태를 제공(이용가능, 불가능 등)

/**
 * AVAILABLE : 구매 가능 / 쇼핑몰 영업중
 * UNAVAILABLE : 품절 / 쇼핑몰 내부 리뉴얼 등
 * DELETED : 삭제된 상품(품절 -> 단종) 되거나 계약 해지한 쇼핑몰 등
 * 정책 상 soft-delete로 구현하기
 */
public enum Status {
    AVAILABLE,
    UNAVAILABLE,
    DELETED
}
