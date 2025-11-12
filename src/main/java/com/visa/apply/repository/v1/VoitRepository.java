package com.visa.apply.repository.v1;

import com.visa.apply.entity.Voit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VoitRepository extends JpaRepository<Voit, Long>, JpaSpecificationExecutor<Voit> {
}
