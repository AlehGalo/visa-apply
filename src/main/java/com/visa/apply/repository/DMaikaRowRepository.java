package com.visa.apply.repository;

import com.visa.apply.domain.DMaikaRow;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DMaikaRow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DMaikaRowRepository extends JpaRepository<DMaikaRow, Long>, JpaSpecificationExecutor<DMaikaRow> {}
