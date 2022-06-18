package com.afterdrawing.backendapi.junit;

import com.afterdrawing.backendapi.core.entity.Interface;
import com.afterdrawing.backendapi.core.entity.Wireframe;
import com.afterdrawing.backendapi.core.repository.*;
import com.afterdrawing.backendapi.core.service.WireframeService;
import com.afterdrawing.backendapi.exception.ResourceNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class WireframeImplServiceTest {

   

    @MockBean
    InterfaceRepository interfaceRepository;
    
    @MockBean
    WireframeRepository wireframeRepository;
    @Autowired
    WireframeService wireframeService;


    @Test
    @DisplayName("Get all wireframes and returns list of wireframes")
    public void getAllWireframesAndReturnsListOfWireframes() {

        // Create a Interfaces with id = 1L and interfaceName = "interfaceName1" and Project And User
        Wireframe wireframe = new Wireframe();
        // create a interface
        Interface anInterface = new Interface();
        

        wireframe.setId(1L);

        //wireframe.setAnInterface(anInterface);
        //wireframe.setWireFrameName("anInterface");
        //wireframe.setRoute("route");
        anInterface.setId(1L);
        // Create a Interfaces with id = 2L and interfaceName = "interfaceName2" and Project And User
        Wireframe wireframe2 = new Wireframe();
        // create a interface
        Interface anInterface2 = new Interface();
        wireframe2.setId(2L);
        //wireframe2.setAnInterface(anInterface2);
        //wireframe2.setWireFrameName("anInterface2");
        //wireframe2.setRoute("route2");
        anInterface2.setId(2L);

        // Create a Interfaces with id = 3L and interfaceName = "interfaceName3" and Project And User
        Wireframe wireframe3 = new Wireframe();
        // create a interface
        Interface anInterface3 = new Interface();
        wireframe3.setId(3L);
        //wireframe3.setAnInterface(anInterface3);
        //wireframe3.setWireFrameName("anInterface3");
        //wireframe3.setRoute("route3");
        anInterface3.setId(3L);

        List<Wireframe> wireframeList = List.of(wireframe,wireframe2,wireframe3);
        // create a pageable object
        Pageable pageable = Pageable.ofSize(1);
        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), wireframeList.size());
        final Page<Wireframe> page = new PageImpl<>(wireframeList.subList(start, end), pageable, wireframeList.size());

        // create a page of users
        when(wireframeRepository.findAll(pageable))
                .thenReturn(page);
        // Act
        Page<Wireframe> wireframes = wireframeService.getAllWireframes(pageable);
        wireframes.getContent();
        // Assert
        Assertions.assertThat(wireframes.getContent()).isEqualTo(page.getContent());

        



    }

    @Test
    @DisplayName("Get wireframe by id and returns wireframe")
    public void getWireframeByIdAndReturnsWireframe() {

        // Create a Interfaces with id = 1L and interfaceName = "interfaceName1" and Project And User
        Wireframe wireframe = new Wireframe();
        // create a interface
        Interface anInterface = new Interface();
        wireframe.setId(1L);
        //wireframe.setAnInterface(anInterface);
        //wireframe.setWireFrameName("anInterface");
        //wireframe.setRoute("route");
        anInterface.setId(1L);
        // Act
        when(wireframeRepository.findById(1L))
                .thenReturn(java.util.Optional.of(wireframe));
        // Assert
        assertThat(wireframeService.getWireframeById(1L)).isEqualTo(wireframe);

    }

    @Test
    @DisplayName("Get wireframe by id and returns ResourceNotFoundException")
    public void getWireframeByIdAndReturnsResourceNotFoundException() {
        
       Interface anInterface = new Interface();
       anInterface.setId(1L);
        // Act
        //returns resource not found exception
        when(wireframeRepository.findById(1L))
                .thenThrow(new ResourceNotFoundException("Wireframe not found with id 1"));


        Assertions.assertThatThrownBy(() -> wireframeService.getWireframeById(1L));

    }


    @Test
    @DisplayName("Get all wireframes and returns a empty list")
    public void getAllWireframesAndReturnsAList() {


        //empty list
        List<Wireframe> wireframeList = List.of();
        // create a pageable object
        Pageable pageable = Pageable.ofSize(1);
        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), wireframeList.size());
        final Page<Wireframe> page = new PageImpl<>(wireframeList.subList(start, end), pageable, wireframeList.size());

        // create a page of users
        when(wireframeRepository.
                findAll(pageable))
                .thenReturn(page);
        // Act
        Page<Wireframe> wireframes = wireframeService.getAllWireframes(pageable);
        wireframes.getContent();
        // Assert
        Assertions.assertThat(wireframes.getContent()).isEqualTo(page.getContent());

    }


    

}

    
