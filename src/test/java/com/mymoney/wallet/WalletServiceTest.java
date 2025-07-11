package com.mymoney.wallet;

import static org.junit.jupiter.api.Assertions.*;
import com.mymoney.wallet.exception.CustomException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WalletServiceTest {

    @Mock
    private WalletRepository walletRepository;

    @InjectMocks
    private WalletService walletService;

    @Test
    void getBalanceById_ReturnBalance_IfWalletExists() {
        Long walletId = 1L;
        BigDecimal balance = new BigDecimal("500");
        Wallet wallet = new Wallet(walletId, balance);

        when(walletRepository.findById(walletId))
                .thenReturn(Optional.of(wallet));

        BigDecimal actualBalance = walletService.getBalanceById(walletId);

        assertTrue(balance.compareTo(actualBalance) == 0);
        verify(walletRepository).findById(walletId);
    }

    @Test
    void getBalanceById_ReturnException_IfWalletNotFound() {
        Long walletId = 15000L;

        when(walletRepository.findById(walletId))
                .thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> {
            walletService.getBalanceById(walletId);
        });
    }
}
