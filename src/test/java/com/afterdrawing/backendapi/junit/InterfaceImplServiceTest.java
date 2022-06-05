package com.afterdrawing.backendapi.junit;

import com.afterdrawing.backendapi.core.entity.Interface;
import com.afterdrawing.backendapi.core.entity.Project;
import com.afterdrawing.backendapi.core.entity.User;
import com.afterdrawing.backendapi.core.repository.InterfaceRepository;
import com.afterdrawing.backendapi.core.repository.ProjectRepository;
import com.afterdrawing.backendapi.core.repository.UserRepository;
import com.afterdrawing.backendapi.core.service.InterfaceService;
import com.afterdrawing.backendapi.core.service.ProjectService;
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
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class InterfaceImplServiceTest {

    @MockBean
    InterfaceRepository interfaceRepository;

    @Autowired
    InterfaceService interfaceService;

    @MockBean
    UserRepository userRepository;
    @MockBean
    ProjectRepository projectRepository;

    // implement test cases of InterfaceService
    @Test
    @DisplayName("Get all interfaces By UserId")
    public void getAllInterfacesByUserIdAndReturnsListOfInterfaces() {

        // Create a Interfaces with id = 1L and interfaceName = "interfaceName1" and Project And User
        Interface anInterface = new Interface();
        // create user  and project
        User user = new User();
        user.setId(1L);
        Project project = new Project();
        anInterface.setId(1L);
        anInterface.setInterfaceName("interfaceName1");
        anInterface.setProject(project);
        anInterface.setUser(user);

        // Create a Interfaces with id = 2L and interfaceName = "interfaceName2" and Project And User
        Interface anInterface2 = new Interface();
        // create user  and project

        Project project2 = new Project();
        anInterface2.setId(2L);
        anInterface2.setInterfaceName("interfaceName2");
        anInterface2.setProject(project2);
        anInterface2.setUser(user);

        // Create a Interfaces with id = 3L and interfaceName = "interfaceName3" and Project And User

        Interface anInterface3 = new Interface();
        // create user  and project

        Project project3 = new Project();
        anInterface3.setId(3L);
        anInterface3.setInterfaceName("interfaceName3");
        anInterface3.setProject(project3);
        anInterface3.setUser(user);

        interfaceRepository.save(anInterface);
        interfaceRepository.save(anInterface2);
        interfaceRepository.save(anInterface3);
        // Create a List of Interfaces
        List<Interface> interfaces = List.of(anInterface, anInterface2, anInterface3);

        Pageable pageable = Pageable.ofSize(1);
        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), interfaces.size());
        final Page<Interface> page = new PageImpl<>(interfaces.subList(start, end), pageable, interfaces.size());

        when(interfaceRepository.findByUserId(user.getId(), pageable)).thenReturn(page);

        Page<Interface> foundInterfaces = interfaceService.getAllInterfacesByUserId(user.getId(),pageable);


        assertThat(foundInterfaces.getContent()).isEqualTo(page.getContent());




    }

    @Test
    @DisplayName("Get all interfaces By ProjectId")
    public void getAllInterfacesByProjectIdAndReturnsListOfInterfaces() {

        // Create a Interfaces with id = 1L and interfaceName = "interfaceName1" and Project And User
        Interface anInterface = new Interface();
        // create user  and project
        User user = new User();
        user.setId(1L);
        Project project = new Project();
        project.setId(1L);
        anInterface.setId(1L);
        anInterface.setInterfaceName("interfaceName1");
        anInterface.setProject(project);
        anInterface.setUser(user);

        // Create a Interfaces with id = 2L and interfaceName = "interfaceName2" and Project And User
        Interface anInterface2 = new Interface();
        // create user  and project

        Project project2 = new Project();
        anInterface2.setId(2L);
        anInterface2.setInterfaceName("interfaceName2");
        anInterface2.setProject(project2);
        anInterface2.setUser(user);

        // Create a Interfaces with id = 3L and interfaceName = "interfaceName3" and Project And User

        Interface anInterface3 = new Interface();
        // create user  and project

        Project project3 = new Project();
        anInterface3.setId(3L);
        anInterface3.setInterfaceName("interfaceName3");
        anInterface3.setProject(project3);
        anInterface3.setUser(user);

        interfaceRepository.save(anInterface);
        interfaceRepository.save(anInterface2);
        interfaceRepository.save(anInterface3);
        // Create a List of Interfaces
        List<Interface> interfaces = List.of(anInterface, anInterface2, anInterface3);

        Pageable pageable = Pageable.ofSize(1);
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), interfaces.size());
        final Page<Interface> page = new PageImpl<>(interfaces.subList(start, end), pageable, interfaces.size());

        when(interfaceRepository.findByProjectId(project.getId(), pageable)).thenReturn(page);

        Page<Interface> foundInterfaces = interfaceService.getAllInterfacesByProjectId(project.getId(),pageable);


        assertThat(foundInterfaces.getContent()).isEqualTo(page.getContent());
        
    }

    @Test
    @DisplayName("Get a Interface By ProjectId and UserId")
    public void getInterfaceByProjectIdAndUserIdAndReturnsInterface() {

        // Create a Interfaces with id = 1L and interfaceName = "interfaceName1" and Project And User
        Interface anInterface = new Interface();
        // create user  and project
        User user = new User();
        user.setId(1L);
        Project project = new Project();
        project.setId(1L);
        anInterface.setId(1L);
        anInterface.setInterfaceName("interfaceName1");
        anInterface.setProject(project);
        anInterface.setUser(user);

        interfaceRepository.save(anInterface);

        when(interfaceRepository.findByUserIdAndProjectId(project.getId(), user.getId())).thenReturn(Optional.of(anInterface));

        Interface foundInterface = interfaceService.getInterfaceByUserIdAndProjectId(project.getId(), user.getId());

        
        assertThat(foundInterface).isEqualTo(anInterface);
    }
        
    @Test
    @DisplayName("Get all Interfaces By UserId and returns empty list")
    public void getAllInterfacesByUserIdAndReturnsEmptyList() {

        
        // create user  and project
        User user = new User();
        user.setId(1L);
        Project project = new Project();
        project.setId(1L);
       

       // Create a List of Interfaces empty
        List<Interface> interfaces = List.of();
        
        Pageable pageable = Pageable.ofSize(1);
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), interfaces.size());
        final Page<Interface> page = new PageImpl<>(interfaces.subList(start, end), pageable, interfaces.size());

        when(interfaceRepository.findByUserId(user.getId(), pageable)).thenReturn(page);

        Page<Interface> foundInterfaces = interfaceService.getAllInterfacesByUserId(user.getId(),pageable);


        assertThat(foundInterfaces.getContent()).isEqualTo(page.getContent());
    }

    @Test
    @DisplayName("Get all Interfaces By ProjectId and returns empty list")
    public void getAllInterfacesByProjectIdAndReturnsEmptyList() {

        
        // create user  and project
        User user = new User();
        user.setId(1L);
        Project project = new Project();
        project.setId(1L);
       

       // Create a List of Interfaces empty
        List<Interface> interfaces = List.of();
        
        Pageable pageable = Pageable.ofSize(1);
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), interfaces.size());
        final Page<Interface> page = new PageImpl<>(interfaces.subList(start, end), pageable, interfaces.size());

        when(interfaceRepository.findByProjectId(project.getId(), pageable)).thenReturn(page);

        Page<Interface> foundInterfaces = interfaceService.getAllInterfacesByProjectId(project.getId(),pageable);


        assertThat(foundInterfaces.getContent()).isEqualTo(page.getContent());
        
    }

}
