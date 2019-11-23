package com.bank.rest.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Data
@Entity
@AllArgsConstructor
@Builder
@Setter
@EqualsAndHashCode(callSuper = true, exclude = {"customer", "account"})
@Table(name = "transaction")
@JsonIgnoreProperties("customer, account")
public class Transaction extends DeactivatableEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;

    @Column
    private Date date;

    @Column
    private BigDecimal beginningAccountBalance;

    @Column
    private BigDecimal endingAccountBalance;

    @Column
    private TransactionType transactionType;

    public Transaction() {
        this.customer = null;
        this.account = null;
        this.date = null;
        this.beginningAccountBalance = null;
        this.endingAccountBalance = null;
        this.transactionType = null;
    }
}
