package com.afterdrawing.backendapi.core.service;

import com.afterdrawing.backendapi.core.entity.Element;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ElementService {
    Element getElementById(Long elementId);

    Page<Element> getAllElementsByProjectId(Long projectId, Pageable pageable);

    Page<Element> getAllElementsByUserId(Long userId, Pageable pageable);
    Page<Element> getAllElementsByInterfaceId(Long interfaceId, Pageable pageable);



    Element updateElement(Long elementId, Element elementRequest);

    Element saveElement(Element elementRequest,Long userId,Long projectId, Long interfaceId);

    ResponseEntity<?> deleteElement(Long elementId);
}
