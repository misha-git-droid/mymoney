package com.mymoney.wallet;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.UUID;

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
