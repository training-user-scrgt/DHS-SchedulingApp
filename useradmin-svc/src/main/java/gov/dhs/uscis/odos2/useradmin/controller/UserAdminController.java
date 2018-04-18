package gov.dhs.uscis.odos2.useradmin.controller;

import gov.dhs.uscis.odos2.useradmin.exception.InvalidUserException;
import gov.dhs.uscis.odos2.useradmin.exception.UserAlreadyExistsException;
import gov.dhs.uscis.odos2.useradmin.model.Users;
import gov.dhs.uscis.odos2.useradmin.service.UserAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/useradmin")
public class UserAdminController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserAdminController.class);

    @Autowired
    private UserAdminService userAdminService;

    @RequestMapping(value ="/getallusers", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('Administrator')")
    public List<Users> getUsers(){
        return userAdminService.findAllUsers();
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<Void> addUser(@RequestBody Users user, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();
        try {
            Users newUser = userAdminService.createNewUser(user);
            headers.setLocation(builder.path("/users/{id}").buildAndExpand(newUser.getId()).toUri());

        } catch (UserAlreadyExistsException e) {
            LOGGER.error(e.getMessage(), e);
            return new ResponseEntity<Void>(headers, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PostMapping(value = "/users/{id}")
    public ResponseEntity<Void> modifyUser(@RequestBody Users user, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();
        try {
            userAdminService.modifyExistingUser(user);
            headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());

        } catch (InvalidUserException e) {
            LOGGER.error(e.getMessage(), e);
            return new ResponseEntity<Void>(headers, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Void> removeUser(@RequestBody UUID userID, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();
        try {
           userAdminService.deleteUser(userID);
           //headers.setLocation(builder.path("/users/{id}").buildAndExpand(userID).toUri());

        } catch (InvalidUserException e) {
            LOGGER.error(e.getMessage(), e);
            return new ResponseEntity<Void>(headers, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

}