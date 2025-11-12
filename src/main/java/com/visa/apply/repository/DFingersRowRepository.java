package com.visa.apply.repository;

import com.visa.apply.domain.DFingersRow;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DFingersRow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DFingersRowRepository extends JpaRepository<DFingersRow, Long>, JpaSpecificationExecutor<DFingersRow> {}
