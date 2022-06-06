/*
package com.afterdrawing.backendapi.junit;

import com.afterdrawing.backendapi.core.entity.Project;
import com.afterdrawing.backendapi.core.entity.User;
import com.afterdrawing.backendapi.core.repository.ProjectRepository;
import com.afterdrawing.backendapi.core.repository.UserRepository;
import com.afterdrawing.backendapi.core.service.ProjectService;
import jdk.jshell.execution.LoaderDelegate;
import org.hibernate.Session;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ProjectImplServiceTest {
    @MockBean
    ProjectRepository projectRepository;

    @Autowired
    ProjectService projectService;

    @MockBean
    UserRepository userRepository;


    // implement test cases of ProjectService
    @Test
    @DisplayName("Get all projects")
    public void getAllProjectsAndReturnsListofProjects() {
        // implement test cases of ProjectService

        Project project1 = new Project();
        User user1 = new User();
        project1.setId(1L);
        project1.setTitle("title1");
        project1.setDescription("description1");
        project1.setUser(user1);
        Project project2 = new Project();
        User user2 = new User();
        project2.setId(2L);
        project2.setTitle("title2");
        project2.setDescription("description2");
        project2.setUser(user2);
        Project project3 = new Project();
        User user3 = new User();
        project3.setId(3L);
        project3.setTitle("title3");
        project3.setDescription("description3");
        project3.setUser(user3);
 

        projectRepository.save(project1);
        projectRepository.save(project2);
        projectRepository.save(project3);
        //create a list of projects
        List<Project> projects = List.of(project1, project2, project3);

        Pageable pageable = Pageable.ofSize(1);
        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), projects.size());
        final Page<Project> page = new PageImpl<>(projects.subList(start, end), pageable, projects.size());

        when(projectRepository.findAll(pageable)).thenReturn(page);

        Page<Project> foundProjects = projectService.getAllProjects(pageable);
        

        assertThat(foundProjects.getContent()).isEqualTo(page.getContent());


    }

    @Test
    @DisplayName("Get All Projects by UserId")
    public void getAllProjectsByUserIdAndReturnsListOfProjects() {
        // implement test cases of ProjectService
        Project project1 = new Project();
        User user1 = new User();
        project1.setId(1L);
        project1.setTitle("title1");
        project1.setDescription("description1");
        project1.setUser(user1);
        Project project2 = new Project();
        User user2 = new User();
        project2.setId(2L);
        project2.setTitle("title2");
        project2.setDescription("description2");
        project2.setUser(user2);
        Project project3 = new Project();
        User user3 = new User();
        project3.setId(3L);
        project3.setTitle("title3");
        project3.setDescription("description3");
        project3.setUser(user3);


        projectRepository.save(project1);
        projectRepository.save(project2);
        projectRepository.save(project3);
        //create a list of projects
        List<Project> projects = List.of(project1, project2, project3);

        Pageable pageable = Pageable.ofSize(1);
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), projects.size());
        final Page<Project> page = new PageImpl<>(projects.subList(start, end), pageable, projects.size());

        when(projectRepository.findByUserId(user1.getId(), pageable)).thenReturn(page);

        Page<Project> foundProjects = projectService.getAllProjectsByUserId(user1.getId(), pageable);


        assertThat(foundProjects.getContent()).isEqualTo(page.getContent());
    }
    @Test
    @DisplayName("Get  Project by UserId And ProjectId")
    public void getProjectByUserIdAndProjectIdAndReturnsProject() {
        // implement test cases of ProjectService
        Project project1 = new Project();
        project1.setTitle("title1");
        project1.setDescription("description1");



        User user1 = new User();
        user1.setId(1L);
        user1.setUserName("name1");
        user1.setFirstName("firstname1");
        user1.setLastName("lastname1");
        user1.setEmail("email1");
        user1.setPassword("password1");
        user1.setEnabled(false);
        user1.setSecretKey("");
        user1.setUsing2FA(false);
        project1.setUser(user1);

        userRepository.save(user1);
        projectRepository.save(project1);
        
            
            when(projectRepository.findByIdAndUserId(project1.getId(), user1.getId())).thenReturn(Optional.of(project1));

            Project foundProject = projectService.getProjectByUserIdAndProjectId(user1.getId(), project1.getId());


            assertThat(foundProject).isEqualTo(project1);

    }
    @Test
    @DisplayName("Get  Projects by UserId  and Returns a empty list")
    public void getProjectsByUserIdAndReturnsEmptyList() {

        // create a User
        User user1 = new User();
        user1.setId(1L);
        user1.setUserName("name1");
        user1.setFirstName("firstname1");
        user1.setLastName("lastname1");
        user1.setEmail("email1");
        user1.setPassword("password1");
        user1.setEnabled(false);
        user1.setSecretKey("");
        user1.setUsing2FA(false);
        
        // Create a empty list of projects
        List<Project> projects = List.of();

        Pageable pageable = Pageable.ofSize(1);
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), projects.size());
        final Page<Project> page = new PageImpl<>(projects.subList(start, end), pageable, projects.size());

        when(projectRepository.findByUserId(user1.getId(), pageable)).thenReturn(page);

        Page<Project> foundProjects = projectService.getAllProjectsByUserId(user1.getId(), pageable);


        assertThat(foundProjects.getContent()).isEqualTo(page.getContent());


    }
        


    
}
*/