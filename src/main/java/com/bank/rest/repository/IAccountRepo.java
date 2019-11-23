package com.bank.rest.repository;

import com.bank.rest.data.Account;
import com.bank.rest.data.Customer;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;

public interface IAccountRepo extends CrudRepository<Account, Long> {

    Optional<Account> findById(Long id);

    Account findByAccountNumber(String accountNumber);

    /**
     * Saves the given {@link Account}.
     */
    <S extends Account> S save(S account);

    /**
     * Sample method to derive a query from using JDK 8's {@link Optional} as return type.
     */
    @Transactional
    Optional<Account> findByAccountHolder(Customer accountHolderId);

    /**
     * Sample method to demonstrate support for {@link Stream} as a return type with a custom query. The query is
     * executed in a streaming fashion which means that the method returns as soon as the first results are ready.
     */
    @Query("select c from Account c")
    Stream<Account> streamAllAccounts();

    /**
     * Sample method to demonstrate support for {@link Stream} as a return type with a derived query. The query is
     * executed in a streaming fashion which means that the method returns as soon as the first results are ready.
     */
    Stream<Account> findAllByAccountBalanceNotNull();

    @Async
    CompletableFuture<List<Account>> readAllBy();
}