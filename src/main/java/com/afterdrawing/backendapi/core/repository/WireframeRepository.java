package com.afterdrawing.backendapi.core.repository;

import com.afterdrawing.backendapi.core.entity.Wireframe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WireframeRepository extends JpaRepository<Wireframe,Long> {
    //Optional<Wireframe> findByName(String name); //String name
    Optional<Wireframe> findById(Long wireframeId);
}
