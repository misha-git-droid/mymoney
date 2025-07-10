package com.mymoney.wallet;

import com.mymoney.dto.RequestData;
import com.mymoney.dto.ResponseData;
import com.mymoney.wallet.exception.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    public ResponseData changeBalance(RequestData request) {

        Long walletId = request.getWalletId();

        Wallet wallet = getWallet(walletId);

        BigDecimal balance = switch (request.getOperationType()) {
            case DEPOSIT -> wallet.getBalance().add(request.getAmount());
            case WITHDRAW -> {
                if (wallet.getBalance().compareTo(request.getAmount()) < 0) {
                    throw new CustomException("There are insufficient funds in the wallet");
                }
                yield wallet.getBalance().subtract(request.getAmount());
            }
        };

        wallet.setBalance(balance);

        walletRepository.save(wallet);

        return new ResponseData(wallet.getId(), wallet.getBalance());
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
                () -> new CustomException("Wallet not found")
        );

    }




}
