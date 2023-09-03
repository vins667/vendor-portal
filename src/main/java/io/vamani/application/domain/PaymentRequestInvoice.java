package io.vamani.application.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "payment_request_invoice")
public class PaymentRequestInvoice implements Serializable {
    @EmbeddedId
    private PaymentRequestInvoiceId id;

    @Column(name = "invoice_date")
    private Instant invoiceDate;

    public PaymentRequestInvoiceId getId() {
        return id;
    }

    public void setId(PaymentRequestInvoiceId id) {
        this.id = id;
    }

    public Instant getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Instant invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
}
