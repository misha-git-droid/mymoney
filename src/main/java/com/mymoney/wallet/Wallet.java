package com.mymoney.wallet;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;


}
