package com.bank.rest.services.impl;

import com.bank.rest.data.Account;
import com.bank.rest.data.Customer;
import com.bank.rest.data.Transaction;
import com.bank.rest.repository.IAccountRepo;
import com.bank.rest.repository.ICustomerRepo;
import com.bank.rest.repository.ITransactionRepo;
import com.bank.rest.services.IAccountReadServices;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountReadServiceImpl implements IAccountReadServices {

    @Autowired
    private
    IAccountRepo iAccountRepo;

    @Autowired
    private ICustomerRepo iCustomerRepo;

    @Autowired
    private ITransactionRepo iTransactionRepo;

    @Override
    public BigDecimal getAccountBalance(String accountNumber) {

        Account account = iAccountRepo.findByAccountNumber(accountNumber);

        if(null != account) {
            return account.getAccountBalance();
        }
        return null;
    }

    @Override
    public Customer getAccountHolder(String username) {
       return iCustomerRepo.findByUsername(username);
    }

    @Override
    public BigDecimal getAccountBalance(Long accountID) {
        return null;
    }

    @Override
    @Transactional
    public Collection<Account> getAllAccountsOfCustomer(Long customerID) {

        return iAccountRepo.streamAllAccounts().collect(Collectors.toList());
    }

    @Override
    public Collection<Transaction> getAllTransactionsOfCustomer(String username) {
        return iTransactionRepo.findByCustomerUsername(username);
    }
}