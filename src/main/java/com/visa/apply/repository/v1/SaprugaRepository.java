package com.visa.apply.repository.v1;

import com.visa.apply.entity.Sapruga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SaprugaRepository extends JpaRepository<Sapruga, Long>, JpaSpecificationExecutor<Sapruga> {
}
