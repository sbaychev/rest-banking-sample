package com.bank.rest.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.bank.rest.data.Account;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class IAccountRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IAccountRepo iAccountRepo;

    @Test
    public void whenFindByAccountNumber_thenReturnCustomer() {

        Account _123456Z = Account.builder()
            .accountBalance(new BigDecimal(1))
            .accountHolder(null)
            .accountNumber("123456Z")
            .transaction(null)
            .build();
        entityManager.persistAndFlush(_123456Z);

        Account found = iAccountRepo.findByAccountNumber(_123456Z.getAccountNumber());
        assertThat(found.getAccountNumber()).isEqualTo(_123456Z.getAccountNumber());
    }

    @Test
    public void whenInvalidUserName_thenReturnNull() {
        Account fromDb = iAccountRepo.findByAccountNumber("doesNotExist");
        assertThat(fromDb).isNull();
    }
}
