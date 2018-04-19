package gov.dhs.uscis.odos2.useradmin.repository;

import gov.dhs.uscis.odos2.useradmin.model.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface UsersRepository extends CrudRepository<Users, UUID> {

    @Query(value = "SELECT * FROM users WHERE LOWER(username) = LOWER(:username)",
            nativeQuery = true)
    Users findByUsername (@Param("username") String username);

    Users findUsersById (UUID id);

    Users save(Users user);

    void delete(Users user);
}