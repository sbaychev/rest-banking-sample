package com.bank.rest.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Data
@Entity
@Builder
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"transaction", "accounts"})
@Table(name = "customer")
public class Customer extends DeactivatableEntity<Long> {

    @Column
    private String username;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "customer")
    @JsonBackReference
    private Set<Transaction> transaction;

    @Fetch(FetchMode.JOIN)
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
        name = "customer_account",
        joinColumns = @JoinColumn(name = "customer_id"),
        inverseJoinColumns = @JoinColumn(name = "account_id"))
    @JsonManagedReference
    private Set<Account> accounts;

    public Customer() {
        this.transaction = null;
        this.username = null;
        this.firstName = null;
        this.lastName = null;
        this.accounts = null;
    }
}
