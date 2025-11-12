package com.visa.apply.repository;

import com.visa.apply.domain.DEurodaRow;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DEurodaRow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DEurodaRowRepository extends JpaRepository<DEurodaRow, Long>, JpaSpecificationExecutor<DEurodaRow> {}
