package com.bank.rest;

import com.bank.rest.data.Account;
import com.bank.rest.data.Customer;
import com.bank.rest.data.Transaction;
import com.bank.rest.data.TransactionType;
import com.bank.rest.repository.IAccountRepo;
import com.bank.rest.repository.ICustomerRepo;
import com.bank.rest.repository.ITransactionRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.bank.rest.repository")
@EntityScan(basePackages = "com.bank.rest.data")
public class RestApplication {

    private static final Logger LOG = LoggerFactory.getLogger(RestApplication.class);

    public static void main(String... args) {
        SpringApplication.run(RestApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        return objectMapper;
    }

    @Bean
    @Transactional
    CommandLineRunner init(IAccountRepo iAccountRepo, ICustomerRepo iCustomerRepo, ITransactionRepo iTransactionRepo) {

        return (args) -> {

            Account account = iAccountRepo.save(Account.builder()
                .accountBalance(new BigDecimal(10000))
                .accountHolder(Collections.emptySet())
                .accountNumber("123456A")
                .build());

            Account account1 = iAccountRepo.save(Account.builder()
                .accountBalance(new BigDecimal(50000))
                .accountHolder(Collections.emptySet())
                .accountNumber("123456B")
                .build());

            BigDecimal balance = account.getAccountBalance();

            account.setAccountBalance(balance.add(BigDecimal.valueOf(10L)));
            Customer customer = Customer.builder()
                .accounts(new LinkedHashSet(Arrays.asList(account, account1)))
                .firstName("John")
                .lastName("Smith")
                .username("jon01").build();

            Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.CREDIT)
                .beginningAccountBalance(balance)
                .endingAccountBalance(balance.add(BigDecimal.valueOf(10L)))
                .customer(customer)
                .date(Date.valueOf(LocalDate.now()))
                .build();

            Set<Transaction> transactionList = account.getTransaction() == null ? new LinkedHashSet<>() :
                account.getTransaction();
            transactionList.add(transaction);
            account.setTransaction(transactionList);
            transaction.setAccount(account);

            Set<Transaction> transactionLis = customer.getTransaction() == null ? new LinkedHashSet<>() :
                account.getTransaction();
            transactionLis.add(transaction);
            customer.setTransaction(transactionLis);

            iCustomerRepo.save(customer);

//            iTransactionRepo.save(transaction);

//            Transaction transaction1 = Transaction.builder()
//                .transactionType(TransactionType.DEBIT)
//                .account(account)
//                .beginningAccountBalance(account.getAccountBalance())
//                .endingAccountBalance(account.getAccountBalance().subtract(BigDecimal.valueOf(50L)))
//                .customer(customer)
//                .date(Date.valueOf(LocalDate.now()))
//                .build();
//
//            iTransactionRepo.save(transaction1);

//
//            Customer otherCustomer = iCustomerRepo.save(new Customer("jon02", "John", "Smith",
//                Collections.singletonList(account)));
//
//            account = iAccountRepo.save(Account.builder()
//                .accountBalance(new BigDecimal(10000))
//                .accountHolder(Arrays.asList(customer, otherCustomer))
//                .accountNumber("123456A")
//                .build());
            LOG.info("Created some Default Data Templates to be used");
        };
    }
}
