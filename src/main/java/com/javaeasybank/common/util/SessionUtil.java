package com.javaeasybank.common.util;

import jakarta.servlet.http.HttpSession;

/**
 * 登入驗證工具。
 *
 * 統一管理 Session 的 key 名稱與驗證邏輯。
 * 所有需要驗證登入的 Controller 都呼叫這裡的方法，
 * 不要各自從 session 取 attribute 自己判斷。
 *
 * 目前為階段一做法：Security 全部放行，手動用 SessionUtil 驗證。
 * 階段二（Week 3）會切換到 Spring Security 統一管控，屆時由組長處理。
 *
 */
public class SessionUtil {

    // Session 裡存放登入資訊的 key，統一在這裡定義
    // 避免各地字串打錯或不一致
    private static final String ADMIN_KEY = "loginAdmin";
    private static final String CLIENT_KEY = "loginCustomer";

    // 工具類別不需要被實例化，constructor 設為 private
    private SessionUtil() {}

    /**
     * 確認行員是否已登入
     * 回傳 true = 已登入，false = 未登入或 session 過期
     */
    public static boolean isAdminLoggedIn(HttpSession session) {
        return session.getAttribute(ADMIN_KEY) != null;
    }

    /**
     * 確認客戶是否已登入
     */
    public static boolean isClientLoggedIn(HttpSession session) {
        return session.getAttribute(CLIENT_KEY) != null;
    }

    /**
     * 確認是否為經理角色
     * 需要先確認已登入再呼叫這個方法
     */
    public static boolean isManager(HttpSession session) {
        Object admin = session.getAttribute(ADMIN_KEY);
        if (admin == null) return false;
        // 這裡之後要換成你的 Administrator entity
        // 目前先用 Object 佔位，等 auth 模組的 Entity 建好再補
        return false; // TODO: 等 Administrator entity 建好後改成 admin.getRole().equals("MANAGER")
    }

    /**
     * 取得目前登入的行員物件
     * 回傳 null 代表未登入
     */
    public static Object getLoginAdmin(HttpSession session) {
        return session.getAttribute(ADMIN_KEY);
    }

    /**
     * 取得目前登入的客戶物件
     * 回傳 null 代表未登入
     */
    public static Object getLoginCustomer(HttpSession session) {
        return session.getAttribute(CLIENT_KEY);
    }

    /**
     * 登出，銷毀整個 session
     */
    public static void logout(HttpSession session) {
        session.invalidate();
    }
}