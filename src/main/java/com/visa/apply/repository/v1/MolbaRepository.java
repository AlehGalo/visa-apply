package com.visa.apply.repository.v1;

import com.visa.apply.entity.Molba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MolbaRepository extends JpaRepository<Molba, Long>, JpaSpecificationExecutor<Molba> {
}
