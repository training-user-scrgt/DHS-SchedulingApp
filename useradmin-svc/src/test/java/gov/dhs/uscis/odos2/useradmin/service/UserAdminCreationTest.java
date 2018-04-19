package gov.dhs.uscis.odos2.useradmin.service;


import gov.dhs.uscis.odos2.useradmin.exception.*;
import gov.dhs.uscis.odos2.useradmin.model.*;
import gov.dhs.uscis.odos2.useradmin.repository.*;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserAdminCreationTest {

    @Autowired
    private UserAdminService useradminService;

    @MockBean
    private UsersRepository usersRepository;

    @MockBean
    private RolesRepository rolesRepository;

    @MockBean
    private UserRolesRepository userRolesRepository;

    Users user;

    Roles role;

    UserRoles userRoles;

    @TestConfiguration
    static class ReservationServiceImplTestContextConfiguration {

        @Bean
        public UserAdminService useradminService() {
            return new UserAdminServiceImpl();
        }

    }

    @Before
    public void setUp() {
        this.user = new Users();
        this.user.setUserName("testuser");
        this.user.setFirstName("first");
        this.user.setLastName("last");

        this.role = new Roles();
        this.role.setId(0);
        this.role.setRole("ROLE_REQUESTOR");
        this.role.setDescription("Some Description");

        this.userRoles = new UserRoles();
        this.userRoles.setId(0);
        this.userRoles.setUserId(this.user.getId());
        this.userRoles.setRoleId(0);
    }

    @Test(expected = InvalidUserException.class)
    public void shouldThrowExceptionDueToEmptyEndTime() throws UserAlreadyExistsException, InvalidUserException  {
        when(rolesRepository.findByRole(any(String.class))).thenReturn(this.role);
        this.user.setFirstName(null);
        Users newUser = useradminService.createNewUser(this.user);
        assertTrue(newUser.getFirstName() == null);
    }

    @Test
    public void shouldPersist() {
        when(usersRepository.save(any(Users.class))).thenReturn(this.user);
        when(rolesRepository.findByRole(any(String.class))).thenReturn(this.role);
        when(userRolesRepository.save(any(UserRoles.class))).thenReturn(this.userRoles);

        try {
            Users newUser = useradminService.createNewUser(this.user);
            assertThat(newUser).isNotNull();
        } catch (UserAlreadyExistsException | InvalidUserException e) {
            fail(e.getMessage());
        }

    }

}
