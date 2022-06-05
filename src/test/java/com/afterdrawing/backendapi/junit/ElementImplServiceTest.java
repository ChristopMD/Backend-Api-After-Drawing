package com.afterdrawing.backendapi.junit;

import com.afterdrawing.backendapi.core.entity.Element;
import com.afterdrawing.backendapi.core.entity.Interface;
import com.afterdrawing.backendapi.core.entity.Project;
import com.afterdrawing.backendapi.core.entity.User;
import com.afterdrawing.backendapi.core.repository.ElementRepository;
import com.afterdrawing.backendapi.core.repository.InterfaceRepository;
import com.afterdrawing.backendapi.core.repository.ProjectRepository;
import com.afterdrawing.backendapi.core.repository.UserRepository;
import com.afterdrawing.backendapi.core.service.ElementService;
import com.afterdrawing.backendapi.core.service.UserService;
import com.afterdrawing.backendapi.exception.ResourceNotFoundException;
import com.afterdrawing.backendapi.resource.authentication.SignUpResource;
import com.afterdrawing.backendapi.service.AuthenticationService;
import com.afterdrawing.backendapi.service.UserServiceImpl;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.util.Lists.emptyList;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ElementImplServiceTest {
    @MockBean
    UserRepository userRepository;
    @MockBean
    ProjectRepository projectRepository;
    @MockBean
    InterfaceRepository interfaceRepository;
    @MockBean
    ElementRepository elementRepository;
    @Autowired
    ElementService elementService;

    @Test
    @DisplayName("Get all elements By UserId")
    public void getAllElementsByUserIdAndReturnsListOfElements() {
        // Create a User with id = 1L and userName = "userName1"
        User user = new User();
        user.setId(1L);
        user.setUserName("userName1");

        // Create a Project with id = 1L and projectName = "projectName1"
        Project project = new Project();
        project.setId(1L);
        project.setTitle("projectName1");
        project.setUser(user);

        // Create a Interface with id = 1L and interfaceName = "interfaceName1" and Project And User
        Interface anInterface = new Interface();
        anInterface.setId(1L);
        anInterface.setInterfaceName("interfaceName1");
        anInterface.setProject(project);
        anInterface.setUser(user);

        
        // Create a Element with id = 1L and elementName = "elementName1" and Interface And User And Project
        com.afterdrawing.backendapi.core.entity.Element element = new com.afterdrawing.backendapi.core.entity.Element();
        element.setId(1L);
        element.setPositionX("positionX1");
        element.setPositionY("positionY1");
        element.setElementType("elementType1");
        element.setUser(user);
        element.setAnInterface(anInterface);
        element.setProject(project);

        // Create a Element with id = 2L and elementName = "elementName2" and Interface And User And Project
        com.afterdrawing.backendapi.core.entity.Element element2 = new com.afterdrawing.backendapi.core.entity.Element();
        element2.setId(2L);
        element2.setPositionX("positionX2");
        element2.setPositionY("positionY2");
        element2.setElementType("elementType2");
        element2.setUser(user);
        element2.setAnInterface(anInterface);
        element2.setProject(project);


        // Create a Element with id = 3L and elementName = "elementName3" and Interface And User And Project
        com.afterdrawing.backendapi.core.entity.Element element3 = new com.afterdrawing.backendapi.core.entity.Element();
        element3.setId(3L);
        element3.setPositionX("positionX3");
        element3.setPositionY("positionY3");
        element3.setElementType("elementType3");
        element3.setUser(user);
        element3.setAnInterface(anInterface);
        element3.setProject(project);

        // Create a List of Interfaces
        List<Element> elements = List.of(element, element2, element3);

        Pageable pageable = Pageable.ofSize(1);
        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), elements.size());
        final Page<Element> page = new PageImpl<>(elements.subList(start, end), pageable, elements.size());

        when(elementRepository.findByUserId(user.getId(), pageable)).thenReturn(page);

        Page<Element> foundElements = elementService.getAllElementsByUserId(user.getId(),pageable);


        AssertionsForClassTypes.assertThat(foundElements.getContent()).isEqualTo(page.getContent());




    }

    @Test
    @DisplayName("Get all elements By UserId and returns empty list")
    public void getAllElementsByUserIdAndReturnsEmptyList() {


        User user2 = new User();
        user2.setId(2L);
        user2.setUserName("userName2");
        

        // Create a List of Interfaces
        List<Element> elements = emptyList();

        Pageable pageable = Pageable.ofSize(1);
        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), elements.size());
        
        when(elementRepository.findByUserId(user2.getId(), pageable)).thenReturn(new PageImpl<>(elements.subList(start, end), pageable, elements.size()));

        Page<Element> foundElements = elementService.getAllElementsByUserId(user2.getId(),pageable);


        AssertionsForClassTypes.assertThat(foundElements.getContent()).isEqualTo(Page.empty().getContent());
    }

    @Test
    @DisplayName("Get all elements By Project and returns ElementList")
    public void getAllElementsByProjectAndReturnsListOfElements() {
        // Create a User with id = 1L and userName = "userName1"
        User user = new User();
        user.setId(1L);
        user.setUserName("userName1");

        // Create a Project with id = 1L and projectName = "projectName1"
        Project project = new Project();
        project.setId(1L);
        project.setTitle("projectName1");
        project.setUser(user);

        // Create a Interface with id = 1L and interfaceName = "interfaceName1" and Project And User
        Interface anInterface = new Interface();
        anInterface.setId(1L);
        anInterface.setInterfaceName("interfaceName1");
        anInterface.setProject(project);
        anInterface.setUser(user);

        
        // Create a Element with id = 1L and elementName = "elementName1" and Interface And User And Project
        com.afterdrawing.backendapi.core.entity.Element element = new com.afterdrawing.backendapi.core.entity.Element();
        element.setId(1L);
        element.setPositionX("positionX1");
        element.setPositionY("positionY1");
        element.setElementType("elementType1");
        element.setUser(user);
        element.setAnInterface(anInterface);
        element.setProject(project);

        // Create a Element with id = 2L and elementName = "elementName2" and Interface And User And Project
        com.afterdrawing.backendapi.core.entity.Element element2 = new com.afterdrawing.backendapi.core.entity.Element();
        element2.setId(2L);
        element2.setPositionX("positionX2");
        element2.setPositionY("positionY2");
        element2.setElementType("elementType2");
        element2.setUser(user);
        element2.setAnInterface(anInterface);
        element2.setProject(project);


        // Create a Element with id = 3L and elementName = "elementName3" and Interface And User And Project
        com.afterdrawing.backendapi.core.entity.Element element3 = new com.afterdrawing.backendapi.core.entity.Element();
        element3.setId(3L);
        element3.setPositionX("positionX3");
        element3.setPositionY("positionY3");
        element3.setElementType("elementType3");
        element3.setUser(user);
        element3.setAnInterface(anInterface);
        element3.setProject(project);

        // Create a List of Interfaces
        List<Element> elements = List.of(element, element2, element3);

        Pageable pageable = Pageable.ofSize(1);
        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), elements.size());
        final Page<Element> page = new PageImpl<>(elements.subList(start, end), pageable, elements.size());

        when(elementRepository.findByProjectId(project.getId(), pageable)).thenReturn(page);

        Page<Element> foundElements = elementService.getAllElementsByProjectId(project.getId(),pageable);


        AssertionsForClassTypes.assertThat(foundElements.getContent()).isEqualTo(page.getContent());
    }

    @Test
    @DisplayName("Get all elements By Project and returns empty list")
    public void getAllElementsByProjectAndReturnsEmptyList() {

            
            Project project2 = new Project();
            project2.setId(2L);
            project2.setTitle("projectName2");
            
    
            // Create a List of Interfaces
            List<Element> elements = emptyList();
    
            Pageable pageable = Pageable.ofSize(1);
            final int start = (int)pageable.getOffset();
            final int end = Math.min((start + pageable.getPageSize()), elements.size());
            
            when(elementRepository.findByProjectId(project2.getId(), pageable)).thenReturn(new PageImpl<>(elements.subList(start, end), pageable, elements.size()));
    
            Page<Element> foundElements = elementService.getAllElementsByProjectId(project2.getId(),pageable);


            AssertionsForClassTypes.assertThat(foundElements.getContent()).isEqualTo(Page.empty().getContent());
    }

    @Test
    @DisplayName("Get all elements By Interface and returns ElementList")
    public void getAllElementsByInterfaceAndReturnsListOfElements() {
        // Create a User with id = 1L and userName = "userName1"
        User user = new User();
        user.setId(1L);
        user.setUserName("userName1");

        // Create a Project with id = 1L and projectName = "projectName1"
        Project project = new Project();
        project.setId(1L);
        project.setTitle("projectName1");
        project.setUser(user);

        // Create a Interface with id = 1L and interfaceName = "interfaceName1" and Project And User
        Interface anInterface = new Interface();
        anInterface.setId(1L);
        anInterface.setInterfaceName("interfaceName1");
        anInterface.setProject(project);
        anInterface.setUser(user);

        
        // Create a Element with id = 1L and elementName = "elementName1" and Interface And User And Project
        com.afterdrawing.backendapi.core.entity.Element element = new com.afterdrawing.backendapi.core.entity.Element();
        element.setId(1L);
        element.setPositionX("positionX1");
        element.setPositionY("positionY1");
        element.setElementType("elementType1");
        element.setUser(user);
        element.setAnInterface(anInterface);
        element.setProject(project);

        // Create a Element with id = 2L and elementName = "elementName2" and Interface And User And Project
        com.afterdrawing.backendapi.core.entity.Element element2 = new com.afterdrawing.backendapi.core.entity.Element();
        element2.setId(2L);
        element2.setPositionX("positionX2");
        element2.setPositionY("positionY2");
        element2.setElementType("elementType2");
        element2.setUser(user);
        element2.setAnInterface(anInterface);
        element2.setProject(project);


        // Create a Element with id = 3L and elementName = "elementName3" and Interface And User And Project
        com.afterdrawing.backendapi.core.entity.Element element3 = new com.afterdrawing.backendapi.core.entity.Element();
        element3.setId(3L);
        element3.setPositionX("positionX3");
        element3.setPositionY("positionY3");
        element3.setElementType("elementType3");
        element3.setUser(user);
        element3.setAnInterface(anInterface);
        element3.setProject(project);

        // Create a List of Interfaces
        List<Element> elements = List.of(element, element2, element3);

        Pageable pageable = Pageable.ofSize(1);
        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), elements.size());
        final Page<Element> page = new PageImpl<>(elements.subList(start, end), pageable, elements.size());

        when(elementRepository.findByAnInterfaceId(anInterface.getId(), pageable)).thenReturn(page);


        Page<Element> foundElements = elementService.getAllElementsByInterfaceId(anInterface.getId(),pageable);
        
        AssertionsForClassTypes.assertThat(foundElements.getContent()).isEqualTo(page.getContent());
    }
    
    @Test
    @DisplayName("Get all elements By Interface and returns empty list")
    public void getAllElementsByInterfaceAndReturnsEmptyList() {
      
        // Create a Interface with id = 1L and interfaceName = "interfaceName1" and Project And User
        Interface anInterface = new Interface();
        anInterface.setId(1L);
        anInterface.setInterfaceName("interfaceName1");
        

        
        
        
        // Create a Empty List of Elements
        List<Element> elements = emptyList();

        Pageable pageable = Pageable.ofSize(1);
        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), elements.size());
        final Page<Element> page = new PageImpl<>(elements.subList(start, end), pageable, elements.size());

        when(elementRepository.findByAnInterfaceId(anInterface.getId(), pageable)).thenReturn(page);


        Page<Element> foundElements = elementService.getAllElementsByInterfaceId(anInterface.getId(),pageable);


        AssertionsForClassTypes.assertThat(foundElements.getContent()).isEqualTo(Page.empty().getContent());
    }
}
