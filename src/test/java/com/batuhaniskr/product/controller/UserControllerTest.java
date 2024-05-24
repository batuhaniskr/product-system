package com.batuhaniskr.product.controller;

import com.batuhaniskr.product.dto.UserRegistrationDto;

import com.batuhaniskr.product.model.User;
import com.batuhaniskr.product.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void registerUserAccount_withValidInput_shouldSaveUser() throws Exception {
        // prepare test data
        UserRegistrationDto userDto = new UserRegistrationDto();
        userDto.setUsername("batuhan");
        userDto.setEmail("test@example.com");
        userDto.setPassword("Password1");
        userDto.setConfirmPassword("Password1");

        // define mock behavior
        when(userService.findByEmail(userDto.getEmail())).thenReturn(null);

        User savedUser = new User();
        savedUser.setUsername("batuhan");
        savedUser.setEmail("test@example.com");
        savedUser.setPassword("Password1");

        when(userService.save(userDto)).thenReturn(savedUser);
        // perform the request and assert the response
        mockMvc.perform(post("/registration")
                        .flashAttr("user", userDto)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?success"))
                .andReturn();
    }

    @Test
    public void testRegisterUserAccount_InvalidPassword() throws Exception {
        // Arrange
        UserRegistrationDto userDto = new UserRegistrationDto();
        userDto.setUsername("testuser");
        userDto.setPassword("invalidpassword");
        userDto.setConfirmPassword("invalidpassword");
        userDto.setEmail("testuser@example.com");

        when(userService.findByEmail(userDto.getEmail())).thenReturn(null);

        // Act
        mockMvc.perform(post("/registration")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", userDto.getUsername())
                        .param("password", userDto.getPassword())
                        .param("confirmPassword", userDto.getConfirmPassword())
                        .param("email", userDto.getEmail())
                        .param("terms", "true")
                )

                // Assert
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("user", "password","confirmPassword"))
                .andReturn();
        verify(userService, never()).save(any());
    }

    @Test
    public void testExistingUserRegistration() throws Exception {
        UserRegistrationDto userDto = new UserRegistrationDto();
        userDto.setUsername("testuser");
        userDto.setPassword("testpassword1");
        userDto.setConfirmPassword("testpassword1");
        userDto.setEmail("testuser@test.com");
        userDto.setTerms(true);

        User existingUser = new User();
        existingUser.setEmail("testuser@test.com");

        Mockito.when(userService.findByEmail("testuser@test.com")).thenReturn(existingUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/registration")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", userDto.getUsername())
                        .param("password", userDto.getPassword())
                        .param("confirmPassword", userDto.getConfirmPassword())
                        .param("email", userDto.getEmail())
                        .param("terms", userDto.getTerms().toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("login"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("user", "email"));
    }

}