package com.afterdrawing.backendapi.service;

import com.afterdrawing.backendapi.core.entity.Element;
import com.afterdrawing.backendapi.core.entity.Interface;
import com.afterdrawing.backendapi.core.entity.Project;
import com.afterdrawing.backendapi.core.entity.User;
import com.afterdrawing.backendapi.core.repository.ElementRepository;
import com.afterdrawing.backendapi.core.repository.InterfaceRepository;
import com.afterdrawing.backendapi.core.repository.ProjectRepository;
import com.afterdrawing.backendapi.core.repository.UserRepository;
import com.afterdrawing.backendapi.core.service.ElementService;
import com.afterdrawing.backendapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ElementServiceImpl implements ElementService {
    @Autowired
    private InterfaceRepository interfaceRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ElementRepository elementRepository;


    @Override
    public Element getElementById(Long elementId) {
        return elementRepository.findById(elementId)
                .orElseThrow(() -> new ResourceNotFoundException("Element", "Id", elementId));
    }

    @Override
    public Page<Element> getAllElementsByProjectId(Long projectId, Pageable pageable) {
        return elementRepository.findByProjectId(projectId,pageable);
    }

    @Override
    public Page<Element> getAllElementsByUserId(Long userId, Pageable pageable) {
        return elementRepository.findByUserId(userId,pageable);
    }

    @Override
    public Page<Element> getAllElementsByInterfaceId(Long interfaceId, Pageable pageable) {
        return elementRepository.findByAnInterfaceId(interfaceId,pageable);
    }



    @Override
    public Element updateElement(Long elementId, Element elementRequest) {
        return elementRepository.findById(elementId).map(element -> {
            element.setPositionX1(elementRequest.getPositionX1());
            element.setPositionY1(elementRequest.getPositionY1());
            element.setPositionX2(elementRequest.getPositionX2());
            element.setPositionY2(elementRequest.getPositionY2());
            element.setElementType(elementRequest.getElementType());

            return elementRepository.save(element);
        } ).orElseThrow(()-> new ResourceNotFoundException("Element","id",elementId));
    }

    @Override
    public Element saveElement(Element elementRequest, Long userId, Long projectId, Long interfaceId) {
        User user1 = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
        Project project = projectRepository.findByIdAndUserId(projectId,userId).orElseThrow(()->new ResourceNotFoundException("Project", "Id", projectId));
        Interface anInterface = interfaceRepository.findById(interfaceId).orElseThrow(()->new ResourceNotFoundException("Interface", "Id", interfaceId));
        if (anInterface.getUser()==user1 && anInterface.getProject()==project){
            elementRequest.setProject(project);
            elementRequest.setUser(user1);
            elementRequest.setAnInterface(anInterface);

            return elementRepository.save(elementRequest);
        }


        return null;
    }

    @Override
    public ResponseEntity<?> deleteElement(Long elementId) {
        return elementRepository.findById(elementId).map(element -> {
                    elementRepository.delete(element);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException("Element", "Id", elementId));

    }
    }

