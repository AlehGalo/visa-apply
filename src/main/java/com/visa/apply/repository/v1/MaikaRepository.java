package com.visa.apply.repository.v1;

import com.visa.apply.entity.Maika;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MaikaRepository extends JpaRepository<Maika, Long>, JpaSpecificationExecutor<Maika> {
}
