package com.visa.apply.repository;

import com.visa.apply.domain.DSaprugaRow;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DSaprugaRow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DSaprugaRowRepository extends JpaRepository<DSaprugaRow, Long>, JpaSpecificationExecutor<DSaprugaRow> {}
