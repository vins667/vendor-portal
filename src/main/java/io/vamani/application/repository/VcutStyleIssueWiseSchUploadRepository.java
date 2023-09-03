package io.vamani.application.repository;

import io.vamani.application.domain.VcutStyleIssueWiseSchUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface VcutStyleIssueWiseSchUploadRepository extends JpaRepository<VcutStyleIssueWiseSchUpload, Long> {
}
