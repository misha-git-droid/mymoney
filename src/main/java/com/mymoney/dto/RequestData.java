package com.mymoney.dto;

import com.mymoney.OperationType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestData {

    private Long walletId;
    private OperationType operationType;
    private BigDecimal amount;

}
