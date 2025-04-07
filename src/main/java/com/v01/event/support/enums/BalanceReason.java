package com.v01.event.support.enums;

public enum BalanceReason {
    PURCHASE("상품 구매"),
    REFUND("환불"),
    EVENT_REWARD("이벤트 적립"),
    DEPOSIT("입급"),
    WITHDRAW("출금");


    private final String description;

    BalanceReason(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
