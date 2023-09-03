package io.vamani.application.model;

import java.time.Instant;

public class PaymentRequestInvoiceBean {
    private String invoiceNo;
    private Instant invoiceDate;

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Instant getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Instant invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
}
