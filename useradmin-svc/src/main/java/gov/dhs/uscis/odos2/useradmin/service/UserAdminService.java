package gov.dhs.uscis.odos2.useradmin.service;

import gov.dhs.uscis.odos2.useradmin.model.Users;

import java.util.List;

public interface UserAdminService {
    Users findByUsername(String username);

    List<Users> findAllUsers();
}
