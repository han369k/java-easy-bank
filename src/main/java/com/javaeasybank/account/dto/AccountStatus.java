package com.javaeasybank.account.dto;

// todo: 要加表把這個拆開來設計
public enum AccountStatus {
    INACTIVE,           // 待啟用
    ACTIVE,             // 啟用
    FROZEN,             // 凍結、警示
    CLOSED,             // 結清
    DORMANT,            // 靜止戶
}
