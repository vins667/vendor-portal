package io.vamani.application.repository;

import io.vamani.application.domain.VcutStyleOperationWiseSchUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface VcutStyleOperationWiseSchUploadRepository extends JpaRepository<VcutStyleOperationWiseSchUpload, Long> {
}
