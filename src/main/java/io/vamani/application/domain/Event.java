package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Event.
 */
@Entity
@Table(name = "event")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="eventSeq", sequenceName="event_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="eventSeq")
    private Long id;

    @NotNull
    @Column(name = "event_from", nullable = false)
    private ZonedDateTime eventFrom;

    @NotNull
    @Column(name = "event_to", nullable = false)
    private ZonedDateTime eventTo;

    @NotNull
    @Size(max = 500)
    @Column(name = "event_title", length = 500, nullable = false)
    private String eventTitle;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getEventFrom() {
        return eventFrom;
    }

    public Event eventFrom(ZonedDateTime eventFrom) {
        this.eventFrom = eventFrom;
        return this;
    }

    public void setEventFrom(ZonedDateTime eventFrom) {
        this.eventFrom = eventFrom;
    }

    public ZonedDateTime getEventTo() {
        return eventTo;
    }

    public Event eventTo(ZonedDateTime eventTo) {
        this.eventTo = eventTo;
        return this;
    }

    public void setEventTo(ZonedDateTime eventTo) {
        this.eventTo = eventTo;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public Event eventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
        return this;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Event createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Event createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        if (event.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), event.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Event{" +
            "id=" + getId() +
            ", eventFrom='" + getEventFrom() + "'" +
            ", eventTo='" + getEventTo() + "'" +
            ", eventTitle='" + getEventTitle() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
