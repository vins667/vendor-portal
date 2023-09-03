package io.vamani.application.domain;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class PaymentRequestInvoiceId implements Serializable {
    @Column(name = "invoice_no", length = 50)
    private String invoiceNo;

    @Column(name = "payment_request_form_id")
    private Long paymentRequestFormId;

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Long getPaymentRequestFormId() {
        return paymentRequestFormId;
    }

    public void setPaymentRequestFormId(Long paymentRequestFormId) {
        this.paymentRequestFormId = paymentRequestFormId;
    }

    public PaymentRequestInvoiceId() {
    }

    public PaymentRequestInvoiceId(String invoiceNo, Long paymentRequestFormId) {
        this.invoiceNo = invoiceNo;
        this.paymentRequestFormId = paymentRequestFormId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentRequestInvoiceId that = (PaymentRequestInvoiceId) o;
        return Objects.equals(invoiceNo, that.invoiceNo) && Objects.equals(paymentRequestFormId, that.paymentRequestFormId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceNo, paymentRequestFormId);
    }
}
