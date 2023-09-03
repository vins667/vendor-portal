package io.vamani.application.model;

import java.io.Serializable;
import java.time.Instant;

public class DateSearch implements Serializable {
    private Instant date;

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }
}
