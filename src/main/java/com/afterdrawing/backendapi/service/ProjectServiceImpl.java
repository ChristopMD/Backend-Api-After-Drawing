package com.afterdrawing.backendapi.service;

import com.afterdrawing.backendapi.core.entity.Project;
import com.afterdrawing.backendapi.core.entity.User;
import com.afterdrawing.backendapi.core.repository.ProjectRepository;
import com.afterdrawing.backendapi.core.repository.UserRepository;
import com.afterdrawing.backendapi.core.service.ProjectService;
import com.afterdrawing.backendapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {


    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Project> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    public Page<Project> getAllProjectsByUserId(Long userId, Pageable pageable) {
        return projectRepository.findByUserId(userId, pageable);
    }

    @Override
    public Project getProjectByUserIdAndProjectId(Long userId, Long projectId) {
        return projectRepository.findByIdAndUserId(projectId,userId).
                orElseThrow(()-> new ResourceNotFoundException(
                        "Project not found with Id" +projectId +
                                "and  UserId" +userId)
                );
    }

    @Override
    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "Id", projectId));
    }

    @Override
    public Project updateProject(Long projectId, Project projectRequest,Long userId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(()-> new ResourceNotFoundException("Project","id",projectId));
        project.setTitle(projectRequest.getTitle());
        project.setDescription(projectRequest.getDescription());

        return projectRepository.save(project);
    }

    @Override
    public Project saveProject(Project project,Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
        project.setUser(user);
        return projectRepository.save(project);
    }

    @Override
    public ResponseEntity<?> deleteProject(Long projectId, Long userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(()-> new ResourceNotFoundException("Project","id",projectId));
        projectRepository.delete(project);
        return ResponseEntity.ok().build();
    }
}
