package com.bank.rest.services;

public interface IAccountWriteServices {

    boolean performCreditOperation(Long customerId, Long... otherParty);

    boolean performDebitOperation(Long customerId, Long... otherParty);

}
