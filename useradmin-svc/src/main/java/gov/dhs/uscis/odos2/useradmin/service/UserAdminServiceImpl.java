package gov.dhs.uscis.odos2.useradmin.service;


import gov.dhs.uscis.odos2.useradmin.model.Users;
import gov.dhs.uscis.odos2.useradmin.repository.UsersRepository;
import gov.dhs.uscis.odos2.useradmin.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAdminServiceImpl implements UserAdminService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public List<Users> findAllUsers() {
        return (List<Users>)usersRepository.findAll();
    }
}