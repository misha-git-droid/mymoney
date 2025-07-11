package com.mymoney.dto;

import com.mymoney.OperationType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WalletBalanceUpdateRequest {

    private Long walletId;
    private OperationType operationType;
    @DecimalMin(value = "0", message = "The amount cannot be negative")
    private BigDecimal amount;

}
