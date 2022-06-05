package com.afterdrawing.backendapi.core.service;

import com.afterdrawing.backendapi.core.entity.Interface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface InterfaceService {



    Interface getInterfaceById(Long interfaceId);

    Page<Interface> getAllInterfacesByProjectId(Long projectId, Pageable pageable);

    Page<Interface> getAllInterfacesByUserId(Long userId, Pageable pageable);

    Interface getInterfaceByUserIdAndProjectId(Long userId, Long projectId);

    Interface updateInterface(Long interfaceId, Interface interfaceRequest);

    Interface saveInterface(Interface newInterface,Long userId,Long projectId,Long wireframeId);

    ResponseEntity<?> deleteInterface(Long interfaceId);
}
