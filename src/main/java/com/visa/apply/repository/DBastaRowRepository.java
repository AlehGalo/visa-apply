package com.visa.apply.repository;

import com.visa.apply.domain.DBastaRow;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DBastaRow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DBastaRowRepository extends JpaRepository<DBastaRow, Long>, JpaSpecificationExecutor<DBastaRow> {}
