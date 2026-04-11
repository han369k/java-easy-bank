package com.javaeasybank.common.dto.response;

import lombok.Getter;
import java.util.List;

/**
 * 分頁查詢的統一回傳格式。
 *
 * 查詢結果筆數多時（交易明細、貸款清單）不能一次全部回傳，
 * 用這個包裝分頁資訊一起給前端。
 *
 * 使用範例：
 * PageResponse<AccountResponse> page = PageResponse.of(list, 0, 10, 100L);
 * return ResponseEntity.ok(ApiResponse.success(page));
 */
@Getter
public class PageResponse<T> {

    // 這頁實際的資料清單，型別由外部呼叫時決定
    // 例如 PageResponse<TransactionResponse> 的 content 就是 List<TransactionResponse>
    private final List<T> content;


    private final int page;// 目前第幾頁，從 0 開始算（第一頁是 0，第二頁是 1）
    private final int size;// 每頁幾筆資料
    private final long totalElements;// 資料庫裡總共幾筆（不是這頁，是全部）
    private final int totalPages;// 總共幾頁，由 totalElements / size 無條件進位算出


    // private：禁止外部直接 new PageResponse()
    // 強迫所有人用下面的 of() 靜態方法來建立，讓呼叫的程式碼更易讀
    private PageResponse(List<T> content, int page, int size, long totalElements) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;

        // Math.ceil = 無條件進位
        // 先把 totalElements 轉成 double，避免整數除法截掉小數點
        // 例如 101 / 10 = 10（整數除法會丟掉 0.1），但 101.0 / 10 = 10.1，ceil 後才會得到正確的 11
        this.totalPages = (int) Math.ceil((double) totalElements / size);
    }

    // Static Factory Method：統一建立 PageResponse 的入口
    // 方法名 of() 是 Java 慣例，List.of()、Map.of() 都是同一個模式
    // <T> 在方法前面宣告這個方法自己的泛型，讓 Java 能自動推斷回傳型別
    public static <T> PageResponse<T> of(List<T> content, int page, int size, long totalElements) {
        return new PageResponse<>(content, page, size, totalElements);
    }
}