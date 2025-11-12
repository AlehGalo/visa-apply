package com.visa.apply.repository.v1;

import com.visa.apply.entity.Domakin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DomakinRepository extends JpaRepository<Domakin, Long>, JpaSpecificationExecutor<Domakin> {
}
