package com.afterdrawing.backendapi.junit;

import com.afterdrawing.backendapi.core.entity.User;
import com.afterdrawing.backendapi.core.repository.UserRepository;
import com.afterdrawing.backendapi.core.service.UserService;
import com.afterdrawing.backendapi.exception.ResourceNotFoundException;
import com.afterdrawing.backendapi.resource.authentication.SignUpResource;
import com.afterdrawing.backendapi.service.AuthenticationService;
import com.afterdrawing.backendapi.service.UserServiceImpl;
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
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserImplServiceTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;




    @TestConfiguration
    static class UserImplIntegrationTestConfiguration{
        @Bean
        public UserService userService(){
            return new UserServiceImpl();
        }
    }

    @Test
    @DisplayName("When Get User By Email With Valid User Then Returns User")
    public void whenGetUserByEmailWithValidUserThenReturnsUser(){
        String email = "EmailPro";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);

        when(userRepository.findByEmail(email))
                .thenReturn(Optional.of(user));

        // Act
        User foundUser = userService.getUserByEmail(email);

        // Assert
        assertThat(foundUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    @DisplayName("When GetUserByEmail With Invalid Username Then Returns ResourceNotFoundException")
    public void whenGetUserByUsernameWithInvalidUsernameThenReturnsResourceNotFoundException() {
        // Arrange
        String email = "string";
        String template = "Resource %s not found for %s with value %s";
        when(userRepository.findByEmail(email))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Email not found","email",email);

        // Act
        Throwable exception = catchThrowable(() -> {
            userService.getUserByEmail(email);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }

    
    @Test
    @DisplayName("When Get All Users Then Returns List of Users")
    public void whenGetAllUsersThenReturnsListOfUsers(){
       // create a users to introduce in list and use it in the user service
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
        User user2 = new User();
        user2.setId(2L);
        user2.setUserName("name2");
        user2.setFirstName("firstname2");
        user2.setLastName("lastname2");
        user2.setEmail("email2");
        user2.setPassword("password2");
        user2.setEnabled(false);
        user2.setSecretKey("");
        user2.setUsing2FA(false);
        User user3 = new User();
        user3.setId(3L);
        user3.setUserName("name3");
        user3.setFirstName("firstname3");
        user3.setLastName("lastname3");
        user3.setEmail("email3");
        user3.setPassword("password3");
        user3.setEnabled(false);
        user3.setSecretKey("");
        user3.setUsing2FA(false);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        // create a list of users
        List<User> users = List.of(user1,user2,user3);
        // create a pageable object
        Pageable pageable = Pageable.ofSize(1);
        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), users.size());
        final Page<User> page = new PageImpl<>(users.subList(start, end), pageable, users.size());

        // create a page of users
        when(userRepository.findAll(pageable))
                .thenReturn(page);
        // Act
        Page<User> foundUsers = userService.getAllUsers(pageable);
        foundUsers.getContent();
        // Assert
        assertThat(foundUsers.getContent()).isEqualTo(page.getContent());
       

    } 
    @Test
    @DisplayName("When Get All Users Then Returns a empty list")
    public void whenGetAllUsersThenReturnsEmptyList() {


        // create a empty list of users
        List<User> users = List.of();
        // create a pageable object
        Pageable pageable = Pageable.ofSize(1);
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), users.size());
        final Page<User> page = new PageImpl<>(users.subList(start, end), pageable, users.size());

        // create a page of users
        when(userRepository.findAll(pageable))
                .thenReturn(page);
        // Act
        Page<User> foundUsers = userService.getAllUsers(pageable);
        foundUsers.getContent();
        // Assert
        assertThat(foundUsers.getContent()).isEqualTo(page.getContent());

    }
}
