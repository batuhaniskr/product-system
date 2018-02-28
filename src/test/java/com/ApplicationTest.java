package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {

   @Autowired
   private MockMvc mockMvc;

   @Test
   public void loginWithValidUserThenAuthenticated() throws Exception{

      SecurityMockMvcRequestBuilders.FormLoginRequestBuilder formLoginRequestBuilder = formLogin()
              .user("user")
              .password("user");

      mockMvc.perform(formLoginRequestBuilder)
              .andExpect(authenticated().withUsername("user"));
   }

   @Test
   public void loginInvalidUserThenUnauthenticated() throws Exception{

      SecurityMockMvcRequestBuilders.FormLoginRequestBuilder formLoginRequestBuilder = formLogin()
              .user("user")
              .password("wrong_password");

      mockMvc.perform(formLoginRequestBuilder)
              .andExpect(unauthenticated());
   }
}
