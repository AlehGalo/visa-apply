package com.visa.apply.repository.v1;

import com.visa.apply.entity.Lcdop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LcdopRepository extends JpaRepository<Lcdop, Long>, JpaSpecificationExecutor<Lcdop> {
}
