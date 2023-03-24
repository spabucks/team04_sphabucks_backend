package sphabucks.error;

public enum ErrorCode {
    INVALID_INPUT_VALUE(400, "COMMON-001", "유효성 검증에 실패한 경우"),
    INTERNAL_SERVER_ERROR(500, "COMMON-002", "서버에서 처리할 수 없는 경우"),

    DUPLICATE_LOGIN_ID(400, "ACCOUNT-001", "계정명이 중복된 경우"),
    UNAUTHORIZED(401, "ACCOUNT-002", "인증에 실패한 경우"),
    ACCOUNT_NOT_FOUND(204, "ACCOUNT-003", "계정을 찾을 수 없는 경우"),
    ROLE_NOT_EXISTS(403, "ACCOUNT-004", "권한이 부족한 경우"),
    TOKEN_NOT_EXISTS(403, "ACCOUNT-005", "해당 key의 인증 토큰이 존재하지 않는 경우"),
    DUPLICATE_EMAIL(400, "ACCOUNT-006", "이메일이 중복된 경우"),

    CART_NOT_EXISTS(404, "CART-001","해당 카트가 존재하지 않는 경우"),
    CARTS_NOT_EXISTS(404, "CART-002","카트가 하나도 존재하지 않는 경우"),

    NOTIFICATION_NOT_EXISTS(404, "NOTIFICATION-001", "해당 알림이 존재하지 않는 경우"),
    DUPLICATE_NOTIFICATION(404, "NOTIFICATION-002", "알림이 중복된 경우"),

    CARD_NOT_EXISTS(404,"CARD-001", "해당 카드가 존재하지 않는 경우"),
    CARDS_NOT_EXISTS(404,"CARD-002", "카드가 하나도 존재하지 않는 경우"),
    DUPLICATE_CARD(404,"CARD-003", "카드가 중복된 경우"),

    GIFTICON_NOT_EXISTS(404,"GIFTICON-001", "해당 기프티콘이 존재하지 않는 경우"),
    GIFTICONS_NOT_EXISTS(404,"GIFTICON-002", "기프티콘이 하나도 존재하지 않는 경우"),
    DUPLICATE_GIFTICON(404,"GIFTICON-003", "기프티콘이 중복된 경우"),

    COUPON_NOT_EXISTS(404,"COUPON-001", "해당 쿠폰이 존재하지 않는 경우"),
    COUPONS_NOT_EXISTS(404,"COUPON-002", "쿠폰이 하나도 존재하지 않는 경우"),
    DUPLICATE_COUPON(404,"COUPON-003", "쿠폰이 중복된 경우"),

    USER_NOT_EXISTS(404,"USER-001", "해당 유저가 없는 경우"),

    EVENT_NOT_EXISTS(404, "EVENT-001","해당 이벤트가 존재하지 않는 경우"),
    DUPLICATE_EVENT(404, "EVENT-002","이벤트가 하나도 존재하지 않는 경우"),

    CATEGORY_NOT_EXISTS(404, "CATEGORY-001","해당 카테고리가 존재하지 않는 경우"),
    DUPLICATE_CATEGORY(404, "CATEGORY-002","카테고리가 중복된 경우"),

    HISTORY_NOT_EXISTS(404, "HISTORY-001","해당 결제내역이 존재하지 않는 경우"),
    DUPLICATE_HISTORY(404, "HISTORY-002","주문번호가 중복된 경우"),
    IMAGE_NOT_EXISTS(404, "IMAGE-001","해당 이미지가 존재하지 않는 경우"),
    IMAGES_NOT_EXISTS(404, "IMAGE-002","이미지가 하나도 존재하지 않는 경우"),
    DESTINATION_NOT_EXISTS(404, "DESTINATION-001","해당 배송지가 존재하지 않는 경우"),
    DESTINATION_BASIC_NOT_EXISTS(404, "DESTINATION-002","기본 배송지가 존재하지 않는 경우"),


    TAG_NOT_EXISTS(404, "TAG-001","해당 태그가 존재하지 않는 경우"),
    DUPLICATE_TAG(404, "TAG-002","태그가 중복된 경우"),

    LIKE_NOT_EXISTS(404, "LIKE-001","좋아요가 존재하지 않는 경우"),
    WISHLIST_NOT_EXISTS(404, "WISHLIST-001","위시리스트가 존재하지 않는 경우"),

    PRODUCT_NOT_EXISTS(404, "PRODUCT-001", "해당 상품이 존재하지 않는 경우"),
    DUPLICATE_PRODUCT(404, "PRODUCT-002", "상품이 중복된 경우");

    private final int status;
    private final String code;
    private final String description;

    ErrorCode(int status, String code, String description) {
        this.status = status;
        this.code = code;
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
