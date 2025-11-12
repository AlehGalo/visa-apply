package com.visa.apply.repository;

import com.visa.apply.domain.DLcdopRow;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DLcdopRow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DLcdopRowRepository extends JpaRepository<DLcdopRow, Long>, JpaSpecificationExecutor<DLcdopRow> {}
