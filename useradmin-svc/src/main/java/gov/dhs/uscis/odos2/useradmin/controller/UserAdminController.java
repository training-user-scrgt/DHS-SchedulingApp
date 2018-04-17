package gov.dhs.uscis.odos2.useradmin.controller;

import gov.dhs.uscis.odos2.useradmin.model.Users;
import gov.dhs.uscis.odos2.useradmin.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/useradmin")
public class UserAdminController {
    @Autowired
    private UserAdminService userAdminService;

    /*@RequestMapping(value ="/auth")
    @PreAuthorize("hasAuthority('Administrator') or hasAuthority('User')")
    public sometoken dosomething(){
        return userAdminService.authenticate();
    }*/

    @RequestMapping(value ="/findusers", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public List<Users> getUsers(){
        return userAdminService.findAllUsers();
    }
}