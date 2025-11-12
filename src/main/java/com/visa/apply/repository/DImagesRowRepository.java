package com.visa.apply.repository;

import com.visa.apply.domain.DImagesRow;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DImagesRow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DImagesRowRepository extends JpaRepository<DImagesRow, Long>, JpaSpecificationExecutor<DImagesRow> {}
