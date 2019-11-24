package com.bank.rest.repository;

import com.bank.rest.data.Transaction;
import java.util.Collection;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ITransactionRepo extends JpaRepository<Transaction, Long> {
//    @Query("from #{#entityName} t where t.id = ?1 and t.isActive = 1")

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("from Transaction t inner join t.customer c where c.username=?1")
    Collection<Transaction> findByCustomerUsername(@Param("username") String username);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    <S extends Transaction> S save(S transaction);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    <S extends Transaction> S saveAndFlush(S transaction);


}
