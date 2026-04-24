package com.javaeasybank.risk.controller;

import com.javaeasybank.risk.annotation.RiskCheck;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    // 掛上你的標註，並指定場景為貸款申請
    @RiskCheck(scene = "LOAN_APPLY")
    @PostMapping("/api/loans/{id}")
    public String applyLoan(@PathVariable String id) {
        System.out.println("=== 貸款模組原本的邏輯執行中 ===");
        return "貸款申請成功";}
    }
