package io.vamani.application.repository;

import io.vamani.application.domain.FormsDownload;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FormsDownload entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormsDownloadRepository extends JpaRepository<FormsDownload, Long> {

}
