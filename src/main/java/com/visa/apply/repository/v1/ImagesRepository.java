package com.visa.apply.repository.v1;

import com.visa.apply.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ImagesRepository extends JpaRepository<Images, Long>, JpaSpecificationExecutor<Images> {
}
