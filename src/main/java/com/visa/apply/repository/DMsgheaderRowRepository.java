package com.visa.apply.repository;

import com.visa.apply.domain.DMsgheaderRow;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DMsgheaderRow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DMsgheaderRowRepository extends JpaRepository<DMsgheaderRow, Long>, JpaSpecificationExecutor<DMsgheaderRow> {}
