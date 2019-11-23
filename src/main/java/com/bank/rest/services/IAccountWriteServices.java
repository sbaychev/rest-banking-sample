package com.bank.rest.services;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(isolation = Isolation.SERIALIZABLE)
public interface IAccountWriteServices {

    boolean performCreditOperation(String fromAccountNumber, String toAccountNumber, String amount,
        String customerID);

    boolean performDebitOperation(String fromAccountNumber, String toAccountNumber, String amount,
        String customerID);

}
