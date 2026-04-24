package com.javaeasybank.account.enums;

public enum AccountStatus {
    PENDING,    // 未啟用，開戶預設值
    ACTIVE,     // 正常
    FROZEN,     // 凍結
    DORMANT,    // 靜止戶,暫停
    CLOSED      // 銷戶
}
