package gov.dhs.uscis.odos2.useradmin.repository;

import gov.dhs.uscis.odos2.useradmin.model.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {

    List<Users> getAllUsers();

    Users save(Users users);


}