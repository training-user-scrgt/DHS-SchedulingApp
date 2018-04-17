package gov.dhs.uscis.odos2.useradmin.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.List;

@Entity(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 200)
    private String userName;

    @Column(name = "first_name", nullable = false, length = 200)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 200)
    private String lastName;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "created_by", nullable = false)
    private Integer createdBy;

    @Column(name = "updated_date", nullable = false)
    private LocalDateTime updatedDate;

    @Column(name = "updated_by", nullable = false)
    private Integer updatedBy;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns
            = @JoinColumn(name = "user_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id"))
    private List<Roles> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLaststName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    private String getRolesAsString()
    {
        String returnRoleString = new String();
        Integer roleNumber = 0;
        for (Roles role : roles) {
            returnRoleString += ", role" + roleNumber.toString() + "=" + role.getRole() +
                                ", description" + roleNumber.toString() + "=" + role.getDescription();
                                roleNumber++;
        }
        return returnRoleString;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userName=" + userName +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                getRolesAsString() +
                ", createdDate=" + createdDate +
                ", createdBy=" + createdBy +
                ", updatedDate=" + updatedDate +
                ", updatedBy=" + updatedBy +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users that = (Users) o;
        return userName == that.userName &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, firstName, lastName);
    }
}
