package com.fintech.demo.dto;

public class HitCount {
    private Long accountId;
    private Long total;

    public HitCount(Long accountId, Long total) {
        this.accountId = accountId;
        this.total = total;
    }

    public HitCount() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}


