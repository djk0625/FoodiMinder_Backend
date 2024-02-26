package com.toy.foodiminder.common.enums;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS(1, "SUCCESS"),
    FAIL(0, "FAIL");

    private int resultCode;
    private String resultMessage;

    ResultCode(int resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }
}