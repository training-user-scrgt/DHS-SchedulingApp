package gov.dhs.uscis.odos2.useradmin.repository;

import gov.dhs.uscis.odos2.useradmin.model.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface UsersRepository extends CrudRepository<Users, UUID> {

    Users findByUsername (String username);

    Users findUsersById (UUID id);

    Users save(Users user);

    void delete(Users user);
}