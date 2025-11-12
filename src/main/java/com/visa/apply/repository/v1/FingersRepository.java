package com.visa.apply.repository.v1;

import com.visa.apply.entity.Fingers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FingersRepository extends JpaRepository<Fingers, Long>, JpaSpecificationExecutor<Fingers> {
}
