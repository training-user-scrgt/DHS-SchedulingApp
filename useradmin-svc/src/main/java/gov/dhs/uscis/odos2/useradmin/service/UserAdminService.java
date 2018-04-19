package gov.dhs.uscis.odos2.useradmin.service;

import gov.dhs.uscis.odos2.useradmin.exception.*;
import gov.dhs.uscis.odos2.useradmin.model.Users;

import java.util.List;
import java.util.UUID;

public interface UserAdminService {
    Users findByUsername(String username);

    List<Users> findAllUsers();

    Users createNewUser(Users user) throws UserAlreadyExistsException, InvalidUserException;

    Users modifyExistingUser(UUID id, Users user) throws InvalidUserException;
    
    void deleteUser(UUID userID) throws InvalidUserException;
}
