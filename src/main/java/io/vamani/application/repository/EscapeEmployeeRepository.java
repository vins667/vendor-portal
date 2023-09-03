package io.vamani.application.repository;

import io.vamani.application.domain.EscapeEmployee;
import io.vamani.application.domain.EscapeEmployeeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EscapeEmployeeRepository extends JpaRepository<EscapeEmployee, EscapeEmployeeId> {
}
