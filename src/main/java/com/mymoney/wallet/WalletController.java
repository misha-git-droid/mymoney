package com.mymoney.wallet;

import com.mymoney.dto.RequestData;
import com.mymoney.dto.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.ConditionalOnOAuth2ClientRegistrationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class WalletController {

    @Autowired
    private final WalletService walletService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Wallet wallet() {

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
    @ResponseStatus(HttpStatus.OK)
    public ResponseData updateBalance(
            @RequestBody RequestData request
    ) {
        return walletService.changeBalance(request);
    }

    @GetMapping("/{walletId}")
    public BigDecimal getBalance(
            @PathVariable Long walletId
    ) {
        return walletService.getBalanceById(walletId);
    }


}
