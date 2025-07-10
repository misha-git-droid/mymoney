package com.mymoney.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class WalletBalanceResponse {

    private Long walletId;
    private BigDecimal balance;

}
