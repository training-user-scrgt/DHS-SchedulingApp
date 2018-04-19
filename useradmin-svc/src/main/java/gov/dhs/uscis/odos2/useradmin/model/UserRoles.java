package gov.dhs.uscis.odos2.useradmin.model;

import javax.persistence.*;
import java.util.UUID;;

@Entity(name = "user_roles")
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private UUID userid;

    @Column(name = "role_id", nullable = false)
    private Integer roleid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userid;
    }

    public void setUserId(UUID userid) {
        this.userid = userid;
    }

    public Integer getRoleId() {
        return roleid;
    }

    public void setRoleId(Integer roleid) {
        this.roleid = roleid;
    }
}
