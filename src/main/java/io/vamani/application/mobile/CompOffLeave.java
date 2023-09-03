package io.vamani.application.mobile;

import java.time.Instant;

public class CompOffLeave {
    private Long id;
    private Instant compOffDate;
    private String compOffDateView;
    private Double balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCompOffDate() {
        return compOffDate;
    }

    public void setCompOffDate(Instant compOffDate) {
        this.compOffDate = compOffDate;
    }

    public String getCompOffDateView() {
        return compOffDateView;
    }

    public void setCompOffDateView(String compOffDateView) {
        this.compOffDateView = compOffDateView;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
