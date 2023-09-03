package io.vamani.application.domain;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class TrimTemplateDetailsBreakupId implements Serializable {
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "trim_template_details_id", nullable = false)
	private Long trimsTemplateDetailsId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTrimsTemplateDetailsId() {
		return trimsTemplateDetailsId;
	}

	public void setTrimsTemplateDetailsId(Long trimsTemplateDetailsId) {
		this.trimsTemplateDetailsId = trimsTemplateDetailsId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		TrimTemplateDetailsBreakupId that = (TrimTemplateDetailsBreakupId) o;
		return Objects.equals(id, that.id) && Objects.equals(trimsTemplateDetailsId, that.trimsTemplateDetailsId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, trimsTemplateDetailsId);
	}

	@Override
	public String toString() {
		return "TrimTemplateDetailsBreakupId [id=" + id + ", trimsTemplateDetailsId=" + trimsTemplateDetailsId + "]";
	}

	public TrimTemplateDetailsBreakupId() {
	}

	public TrimTemplateDetailsBreakupId(Long id, Long templateDetailsId) {
		this.id = id;
		this.trimsTemplateDetailsId = templateDetailsId;
	}
}
