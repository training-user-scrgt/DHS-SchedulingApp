package gov.dhs.uscis.odos2.useradmin.repository;

import gov.dhs.uscis.odos2.useradmin.model.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {

    Users findByUsername (String username);

    Users save(Users user);

    void delete(Users user);
}