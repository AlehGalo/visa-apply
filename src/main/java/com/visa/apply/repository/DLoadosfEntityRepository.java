package com.visa.apply.repository;

import com.visa.apply.domain.DLoadosfEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DLoadosfEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DLoadosfEntityRepository extends JpaRepository<DLoadosfEntity, Long>, JpaSpecificationExecutor<DLoadosfEntity> {}
