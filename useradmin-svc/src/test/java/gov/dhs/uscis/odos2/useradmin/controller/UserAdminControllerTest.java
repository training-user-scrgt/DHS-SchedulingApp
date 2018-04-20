package gov.dhs.uscis.odos2.useradmin.controller;

import gov.dhs.uscis.odos2.useradmin.exception.*;
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
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;


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

    @MockBean
    private AuthController authcontroller;

    @MockBean
    private Authentication authentication;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();
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
        when(authcontroller.getJWTToken((any(Authentication.class)))).thenReturn(ResponseEntity.ok(ResponseEntity.class));


        mvc.perform(post("/user").with(authentication(authentication))
                .content(CREATE_USER_BODY)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}