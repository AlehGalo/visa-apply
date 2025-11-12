package com.visa.apply.repository.v1;

import com.visa.apply.entity.Loadosf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;

public interface LoadosfRepository extends JpaRepository<Loadosf, Long>, JpaSpecificationExecutor<Loadosf> {
    void removeAllByIdIn(Collection<Long> ids);
}
