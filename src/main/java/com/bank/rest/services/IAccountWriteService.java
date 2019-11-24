package com.bank.rest.services;

import com.bank.rest.data.Customer;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(isolation = Isolation.SERIALIZABLE)
public interface IAccountWriteService {

    boolean performCreditOperation(String fromAccountNumber, String toAccountNumber, String amount,
        String customerID);

    boolean performDebitOperation(String fromAccountNumber, String toAccountNumber, String amount,
        String customerID);

    Customer save(Customer customer);
}
