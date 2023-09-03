package io.vamani.application.repository;

import io.vamani.application.domain.VcutStyleImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the VcutStyleImage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VcutStyleImageRepository extends JpaRepository<VcutStyleImage, Long> {
    @Query("select vcutStyleImage from VcutStyleImage vcutStyleImage where vcutStyleImage.style = ?1")
    VcutStyleImage findByStyle(String style);

    @Query("select vcutStyleImage from VcutStyleImage vcutStyleImage where vcutStyleImage.style like ?1")
    Page<VcutStyleImage> findAllByStyle(String style, Pageable page);

}
