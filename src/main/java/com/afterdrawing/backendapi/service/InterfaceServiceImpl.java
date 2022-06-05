package com.afterdrawing.backendapi.service;

import com.afterdrawing.backendapi.core.entity.Interface;
import com.afterdrawing.backendapi.core.entity.Project;
import com.afterdrawing.backendapi.core.entity.User;
import com.afterdrawing.backendapi.core.entity.Wireframe;
import com.afterdrawing.backendapi.core.repository.InterfaceRepository;
import com.afterdrawing.backendapi.core.repository.ProjectRepository;
import com.afterdrawing.backendapi.core.repository.UserRepository;
import com.afterdrawing.backendapi.core.repository.WireframeRepository;
import com.afterdrawing.backendapi.core.service.InterfaceService;
import com.afterdrawing.backendapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class InterfaceServiceImpl implements InterfaceService {

    @Autowired
    private InterfaceRepository interfaceRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WireframeRepository wireframeRepository;



    @Override
    public Interface getInterfaceById(Long interfaceId) {
        return interfaceRepository.findById(interfaceId)
                .orElseThrow(() -> new ResourceNotFoundException("Interface", "Id", interfaceId));
    }

    @Override
    public Page<Interface> getAllInterfacesByProjectId(Long projectId, Pageable pageable) {
        return interfaceRepository.findByProjectId(projectId,pageable);
    }

    @Override
    public Page<Interface> getAllInterfacesByUserId(Long userId, Pageable pageable) {
        return interfaceRepository.findByUserId(userId,pageable);
    }

    @Override
    public Interface getInterfaceByUserIdAndProjectId(Long userId, Long projectId) {
        return interfaceRepository.findByUserIdAndProjectId(userId, projectId).
                orElseThrow(()->new ResourceNotFoundException("Interface not found with Id" + projectId +  "and UserId" + userId));
    }

    @Override
    public Interface updateInterface(Long interfaceId, Interface interfaceRequest) {
        return interfaceRepository.findById(interfaceId).map(anInterface -> {
            anInterface.setInterfaceName(interfaceRequest.getInterfaceName());
            return interfaceRepository.save(anInterface);
        } ).orElseThrow(()-> new ResourceNotFoundException("Interface","id",interfaceId));

    }

    @Override
    public Interface saveInterface(Interface newInterface, Long userId, Long projectId, Long wireframeId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
        Project project = projectRepository.findByIdAndUserId(projectId,userId).orElseThrow(()->new ResourceNotFoundException("Project", "Id", projectId));
        Wireframe wireframe = wireframeRepository.findById(wireframeId).orElseThrow(()->new ResourceNotFoundException("Wireframe", "Id", wireframeId));
        newInterface.setProject(project);
        newInterface.setUser(user);
        newInterface.setWireframe(wireframe);
        return interfaceRepository.save(newInterface);
    }

    @Override
    public ResponseEntity<?> deleteInterface(Long interfaceId) {
       return interfaceRepository.findById(interfaceId).map(anInterface -> {
                   interfaceRepository.delete(anInterface);
                   return ResponseEntity.ok().build();
               })
                .orElseThrow(() -> new ResourceNotFoundException("Interface", "Id", interfaceId));

    }
}
