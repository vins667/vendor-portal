package io.vamani.application.model;

public class TdsDeclarationBreakupBean {
    private Long id;
    private Long employerId;
    private Long groupDetailId;
    private Double amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
    }

    public Long getGroupDetailId() {
        return groupDetailId;
    }

    public void setGroupDetailId(Long groupDetailId) {
        this.groupDetailId = groupDetailId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
