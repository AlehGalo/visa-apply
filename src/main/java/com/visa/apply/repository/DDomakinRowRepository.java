package com.visa.apply.repository;

import com.visa.apply.domain.DDomakinRow;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DDomakinRow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DDomakinRowRepository extends JpaRepository<DDomakinRow, Long>, JpaSpecificationExecutor<DDomakinRow> {}
