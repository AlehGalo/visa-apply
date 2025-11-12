package com.visa.apply.repository.v1;

import com.visa.apply.entity.Msgheader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MsgheaderRepository extends JpaRepository<Msgheader, Long>, JpaSpecificationExecutor<Msgheader> {
}
