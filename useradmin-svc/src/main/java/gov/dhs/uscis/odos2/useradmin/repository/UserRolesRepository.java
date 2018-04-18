package gov.dhs.uscis.odos2.useradmin.repository;

import gov.dhs.uscis.odos2.useradmin.model.UserRoles;
import org.springframework.data.repository.CrudRepository;

public interface UserRolesRepository extends CrudRepository<UserRoles, Long> {
    UserRoles save(UserRoles userrole);

    void delete(UserRoles userrole);
}