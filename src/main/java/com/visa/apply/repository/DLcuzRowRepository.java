package com.visa.apply.repository;

import com.visa.apply.domain.DLcuzRow;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DLcuzRow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DLcuzRowRepository extends JpaRepository<DLcuzRow, Long>, JpaSpecificationExecutor<DLcuzRow> {}
