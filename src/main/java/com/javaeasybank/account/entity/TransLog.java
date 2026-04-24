package com.javaeasybank.account.entity;

import com.javaeasybank.account.enums.Currency;
import com.javaeasybank.account.enums.EntryType;
import com.javaeasybank.account.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
    name = "trans_log", 
    indexes = {
        @Index(name = "idx_tx_ref", columnList = "reference_id"),
        @Index(name = "idx_tx_account_time", columnList = "account_number, created_at")
    }
)
@Getter
@Setter
@NoArgsConstructor
public class TransLog {

    @Id
    @Column(name = "transaction_id", length = 36, nullable = false)
    private String transactionId;

    @Column(name = "reference_id", length = 30, nullable = false)
    private String referenceId;

    @Column(name = "account_number", length = 12, nullable = false)
    private String accountNumber;

    @Column(name = "counterpart_account", length = 12)
    private String counterpartAccount;

    @Enumerated(EnumType.STRING)
    @Column(name = "entry_type", nullable = false, length = 10)
    private EntryType entryType;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false, length = 25)
    private TransactionType transactionType;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(name = "balance_before", nullable = false, precision = 19, scale = 4)
    private BigDecimal balanceBefore;

    @Column(name = "balance_after", nullable = false, precision = 19, scale = 4)
    private BigDecimal balanceAfter;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 3)
    private Currency currency;

    @Column(columnDefinition = "NVARCHAR(200)")
    private String note;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    public void prePersist() {
        if (this.transactionId == null) {
            this.transactionId = UUID.randomUUID().toString();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransLog that)) return false;
        return this.getTransactionId() != null &&
                this.getTransactionId().equals(that.getTransactionId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
