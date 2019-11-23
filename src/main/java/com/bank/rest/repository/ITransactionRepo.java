package com.bank.rest.repository;

import com.bank.rest.data.Transaction;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ITransactionRepo extends JpaRepository<Transaction, Long> {
//    @Query("from #{#entityName} t where t.id = ?1 and t.isActive = 1")

    @Transactional(readOnly = true)
    @Query("from Transaction t inner join t.customer c where c.username=?1")
    Collection<Transaction> findByCustomerUsername(@Param("username") String username);

    <S extends Transaction> S save(S transaction);

}
