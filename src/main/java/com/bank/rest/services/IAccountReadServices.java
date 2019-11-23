package com.bank.rest.services;

import com.bank.rest.data.Customer;
import com.bank.rest.data.Transaction;
import java.math.BigDecimal;
import java.util.Collection;

public interface IAccountReadServices {

    BigDecimal getAccountBalance(String accountNumber);

    Customer getAccountHolder(String username);

    BigDecimal getAccountBalance(Long accountId);

    Collection getAllAccountsOfCustomer(Long customerID);

    Collection<Transaction> getAllTransactionsOfCustomer(String username);

}
