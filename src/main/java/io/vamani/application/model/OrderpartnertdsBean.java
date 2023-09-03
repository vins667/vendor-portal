package io.vamani.application.model;

import io.vamani.application.db2.domain.OrderpartnertdsId;
import io.vamani.application.domain.DirectBookingTdsDetails;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class OrderpartnertdsBean implements Serializable {
    private OrderpartnertdsId id;
    private String longdescription;
    private BigDecimal value;
    private Boolean tdsApplicable;
    private Long parentId;

    public OrderpartnertdsBean() {
    }

    public OrderpartnertdsBean(DirectBookingTdsDetails directBookingTdsDetails) {
        OrderpartnertdsId orderpartnertdsId = new OrderpartnertdsId();
        orderpartnertdsId.setTdstypecode(directBookingTdsDetails.getTdsTypeCode());
        orderpartnertdsId.setTdscode(directBookingTdsDetails.getTdsCode());
        orderpartnertdsId.setTdsitaxcode(directBookingTdsDetails.getTdsTaxCode());
        this.id = orderpartnertdsId;
        this.longdescription = directBookingTdsDetails.getTdsDesc();
        this.value = new BigDecimal(directBookingTdsDetails.getTdsPerc().doubleValue());
        this.tdsApplicable = directBookingTdsDetails.getTdsApplicable();
        this.parentId = directBookingTdsDetails.getId();
    }

    public OrderpartnertdsId getId() {
        return id;
    }

    public void setId(OrderpartnertdsId id) {
        this.id = id;
    }

    public String getLongdescription() {
        return longdescription;
    }

    public void setLongdescription(String longdescription) {
        this.longdescription = longdescription;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Boolean getTdsApplicable() {
        return tdsApplicable;
    }

    public void setTdsApplicable(Boolean tdsApplicable) {
        this.tdsApplicable = tdsApplicable;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderpartnertdsBean that = (OrderpartnertdsBean) o;
        return Objects.equals(id, that.id) && Objects.equals(longdescription, that.longdescription) && Objects.equals(value, that.value) && Objects.equals(tdsApplicable, that.tdsApplicable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, longdescription, value, tdsApplicable);
    }
}
