package com.bank.rest.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import com.bank.rest.data.Account;
import com.bank.rest.repository.IAccountRepo;
import com.bank.rest.services.impl.AccountReadServiceImpl;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class IAccountReadServiceTest {

    @InjectMocks
    private AccountReadServiceImpl iAccountReadService;

    @Mock
    private IAccountRepo iAccountRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAll_WhenRecordPresent_ReturnList() {

        //Given
        when(iAccountRepo.findByAccountNumber("123456A")).thenReturn(new Account());
        //When
        BigDecimal balance = iAccountReadService.getAccountBalance("123456A");

        //Then
        assert (balance != null);
        verify(iAccountRepo, times(1)).findByAccountNumber("123456A");
    }
}
