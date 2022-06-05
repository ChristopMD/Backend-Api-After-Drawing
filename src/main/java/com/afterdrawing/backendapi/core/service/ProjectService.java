package com.afterdrawing.backendapi.core.service;

import com.afterdrawing.backendapi.core.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;



public interface ProjectService {
    Page<Project> getAllProjects(Pageable pageable);
    Page<Project> getAllProjectsByUserId(Long userId, Pageable pageable);
    Project getProjectByUserIdAndProjectId(Long userId, Long projectId);

    Project getProjectById(Long projectId);



    Project updateProject(Long projectId, Project projectRequest,Long userId);

    Project saveProject(Project project,Long userId);

    ResponseEntity<?> deleteProject(Long projectId,Long userId);
}

