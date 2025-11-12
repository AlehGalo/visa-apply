package com.visa.apply.repository.v1;

import com.visa.apply.entity.Lcuz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LcuzRepository extends JpaRepository<Lcuz, Long>, JpaSpecificationExecutor<Lcuz> {
}
