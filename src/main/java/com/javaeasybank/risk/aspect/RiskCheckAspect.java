package com.javaeasybank.risk.aspect;

import com.javaeasybank.common.exception.BusinessException;
import com.javaeasybank.risk.annotation.RiskCheck;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect // 宣告這是一個切面 (攔截器)
@Component
public class RiskCheckAspect {

    // @Before 代表在目標方法執行「之前」觸發
    // "@annotation(riskCheck)" 代表攔截所有標有 @RiskCheck 的方法，並把標註物件傳進來
    @Before("@annotation(riskCheck)")
    public void doRiskCheck(JoinPoint join, RiskCheck riskCheck) {

        System.out.println("=====================================");
        System.out.println("🚨 風控攔截到請求了！");
        System.out.println("👉 觸發場景: " + riskCheck.scene());

        // --- 👇 開始模擬阻擋邏輯 👇 ---

        // 假設我們從某處取得了當前申請人的 ID (這裡先寫死模擬)
        String currentUserId = "user_999";

        // 模擬規則：如果帳號是 user_999，就是黑名單
        if ("user_999".equals(currentUserId)) {
            System.out.println("❌ 警告：觸發黑名單規則，準備中斷請求！");
            System.out.println("=====================================");

            // 🚀 關鍵這行！拋出例外，後面的程式碼全部不執行
            throw new BusinessException("風控拒絕：您是高風險黑名單用戶！");
        }

        // --- 👆 結束模擬阻擋邏輯 👆 ---

        System.out.println("✅ 風控檢查通過，放行！");
        System.out.println("=====================================");
    }
}
