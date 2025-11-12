package com.visa.apply.repository.v1;

import com.visa.apply.entity.Euroda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EurodaRepository extends JpaRepository<Euroda, Long>, JpaSpecificationExecutor<Euroda> {
}
