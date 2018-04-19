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
@RequestMapping("/user")
public class UserAdminController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserAdminController.class);

    @Autowired
    private UserAdminService userAdminService;

    @GetMapping
    @PreAuthorize("hasAuthority('Administrator')")
    public List<Users> getUsers(){
        return userAdminService.findAllUsers();
    }

    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody Users user, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();
        try {
            Users newUser = userAdminService.createNewUser(user);
            headers.setLocation(builder.path("/user/{id}").buildAndExpand(newUser.getId()).toUri());

        } catch (UserAlreadyExistsException e) {
            LOGGER.error(e.getMessage(), e);
            return new ResponseEntity<Void>(headers, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> modifyUser(@PathVariable String id, @RequestBody Users user, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();
        try {
            Users modifiedUser = userAdminService.modifyExistingUser(UUID.fromString(id), user);
            headers.setLocation(builder.path("/user/{id}").buildAndExpand(modifiedUser.getId()).toUri());

        } catch (InvalidUserException e) {
            LOGGER.error(e.getMessage(), e);
            return new ResponseEntity<Void>(headers, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable String id, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();
        try {
           userAdminService.deleteUser(UUID.fromString(id));
           //headers.setLocation(builder.path("/users/{id}").buildAndExpand(userID).toUri());

        } catch (InvalidUserException e) {
            LOGGER.error(e.getMessage(), e);
            return new ResponseEntity<Void>(headers, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

}