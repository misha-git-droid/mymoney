package com.mymoney.wallet;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@Table(name = "wallets")
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

}
