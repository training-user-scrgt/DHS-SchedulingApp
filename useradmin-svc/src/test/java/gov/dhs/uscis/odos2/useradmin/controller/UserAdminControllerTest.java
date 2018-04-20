package gov.dhs.uscis.odos2.useradmin.controller;

import gov.dhs.uscis.odos2.useradmin.model.*;
import gov.dhs.uscis.odos2.useradmin.service.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;

import javax.servlet.Filter;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(UserAdminController.class)
public class UserAdminControllerTest {

   @Autowired
   private WebApplicationContext context;

   @Autowired
   private Filter springSecurityFilterChain;

   @Autowired
   private MockMvc mvc;

   @MockBean
   private UserAdminService userAdminService;

   Users existingUser;

   @Before
   public void setup() {
       mvc = MockMvcBuilders
               .webAppContextSetup(context)
               .addFilters(springSecurityFilterChain)
               .build();

        this.existingUser = new Users();
        this.existingUser.setId(UUID.randomUUID());
        this.existingUser.setUserName("testuser");
        this.existingUser.setFirstName("first");
        this.existingUser.setLastName("last");
        this.existingUser.setCreatedDate(LocalDateTime.now());
        this.existingUser.setCreatedBy(UUID.randomUUID());
        this.existingUser.setUpdatedDate(LocalDateTime.now());
        this.existingUser.setUpdatedBy(UUID.randomUUID());
   }

   private final String CREATE_USER_BODY = "{\n" +
   "\t\"userName\": \"testusername\",\n" +
   "\t\"firstName\": \"Testfirstname\",\n" +
   "\t\"lastName\": \"Testlastname\",\n" +
   "}";

   @Test
   @Ignore
   public void shouldCreateUser() throws Exception {

       Users user = new Users();
       user.setId(UUID.randomUUID());

       when(userAdminService.createNewUser(any(Users.class))).thenReturn(user);


       mvc.perform(post("/user")
               .content(CREATE_USER_BODY)
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated());
   }

   @Test
   @Ignore
   public void shouldDeleteUser() throws Exception {

       when(userAdminService.modifyExistingUser(any(UUID.class), any(Users.class))).thenReturn(existingUser);


       mvc.perform(post("/user/" + existingUser.getId())
               .content(CREATE_USER_BODY)
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().is2xxSuccessful());
   }
}