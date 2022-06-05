package com.afterdrawing.backendapi.core.repository;

import com.afterdrawing.backendapi.core.entity.Interface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

@Repository
public interface InterfaceRepository extends JpaRepository<Interface,Long> {

    Page<Interface> findByProjectId(Long projectId, Pageable pageable);

    Page<Interface> findByUserId(Long userId, Pageable pageable);

    Optional<Interface> findByUserIdAndProjectId(Long userId, Long projectId);

}
