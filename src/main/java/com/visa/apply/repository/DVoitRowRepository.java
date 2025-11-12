package com.visa.apply.repository;

import com.visa.apply.domain.DVoitRow;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DVoitRow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DVoitRowRepository extends JpaRepository<DVoitRow, Long>, JpaSpecificationExecutor<DVoitRow> {}
