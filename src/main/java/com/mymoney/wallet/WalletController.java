package com.mymoney.wallet;

import com.mymoney.dto.WalletBalanceResponse;
import com.mymoney.dto.WalletBalanceUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1")
@Validated
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Wallet createWallet() {
        return walletService.createWallet();
    }

    @DeleteMapping("/{walletId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWallet(
            @PathVariable Long walletId
    ) {
        walletService.deleteWallet(walletId);
    }


    @PostMapping("/wallet")
    public WalletBalanceResponse updateBalance(
            @Valid @RequestBody WalletBalanceUpdateRequest request
    ) {
        return walletService.changeBalance(request);
    }

    @GetMapping("/wallets/{walletId}")
    public BigDecimal getWalletBalance(
            @PathVariable Long walletId
    ) {
        return walletService.getBalanceById(walletId);
    }
}
