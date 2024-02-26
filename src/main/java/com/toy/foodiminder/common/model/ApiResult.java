package com.toy.foodiminder.common.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResult {
    // 성공 1 실패 0
    private int resultCode;
    private String resultMessage;
    private Object resultData;
}