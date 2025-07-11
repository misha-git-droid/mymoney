package com.mymoney.wallet;

import com.mymoney.dto.WalletBalanceResponse;
import com.mymoney.dto.WalletBalanceUpdateRequest;
import com.mymoney.wallet.exception.InsufficientFundsException;
import com.mymoney.wallet.exception.WalletNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletBalanceResponse changeBalance(WalletBalanceUpdateRequest request) {

        Long walletId = request.getWalletId();

        Wallet wallet = getWallet(walletId);

        BigDecimal balance = switch (request.getOperationType()) {
            case DEPOSIT -> wallet.getBalance().add(request.getAmount());
            case WITHDRAW -> {
                if (wallet.getBalance().compareTo(request.getAmount()) < 0) {
                    throw new InsufficientFundsException("There are insufficient funds in the wallet");
                }
                yield wallet.getBalance().subtract(request.getAmount());
            }
        };

        wallet.setBalance(balance);

        walletRepository.save(wallet);

        return new WalletBalanceResponse(wallet.getId(), wallet.getBalance());
    }

    public BigDecimal getBalanceById(Long walletId) {

        Wallet wallet = getWallet(walletId);

        return wallet.getBalance();
    }

    public Wallet createWallet() {

        return walletRepository.save(new Wallet());

    }

    public void deleteWallet(Long walletId) {

        Wallet wallet = getWallet(walletId);

        walletRepository.delete(wallet);

    }

    public Wallet getWallet(Long id) {

        return walletRepository.findById(id).orElseThrow(
                () -> new WalletNotFoundException("Wallet not found")
        );

    }




}
