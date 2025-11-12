package com.visa.apply.repository.v1;

import com.visa.apply.entity.Basta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BastaEntityRepository extends JpaRepository<Basta, Long>, JpaSpecificationExecutor<Basta> {
}
