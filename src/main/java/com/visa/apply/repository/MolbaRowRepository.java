package com.visa.apply.repository;

import com.visa.apply.domain.MolbaRow;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MolbaRow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MolbaRowRepository extends JpaRepository<MolbaRow, Long>, JpaSpecificationExecutor<MolbaRow> {}
