package com.bank.rest.services.impl;

import com.bank.rest.services.IAccountWriteServices;
import org.springframework.stereotype.Service;

@Service
public class AccountWriteServiceImpl implements IAccountWriteServices {

    @Override
    public boolean performCreditOperation(Long customerId, Long... otherParty) {
        return false;
    }

    @Override
    public boolean performDebitOperation(Long customerId, Long... otherParty) {
        return false;
    }
}
