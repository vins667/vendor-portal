package io.vamani.application.model;

import io.vamani.application.domain.TdsDeclaration;
import io.vamani.application.domain.TdsDeclarationBreakup;

import java.util.List;

public class PreviousEmploymentDetailBean {
    private Long id;
    private String employerName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }
}
