package com.afterdrawing.backendapi.core.repository;

import com.afterdrawing.backendapi.core.entity.Element;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ElementRepository extends JpaRepository <Element,Long> {
    Page<Element> findByProjectId(Long projectId, Pageable pageable);

    Page<Element> findByUserId(Long userId, Pageable pageable);
    Page<Element> findByAnInterfaceId(Long interfaceId, Pageable pageable);


}
