package gov.dhs.uscis.odos2.inventory.model;

import javax.persistence.*;
import java.util.UUID;

/**
 * This is a base class for all entities. It provides an equals and hashcode
 * that will always work correctly in all circumstances. This avoids frequent
 * errors related to the implementation of those same methods.
 */
@MappedSuperclass
public class AbstractEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @Transient
    private UUID uuid;

    @Column(name = "UUID")
    private String uuidStr;

    @PrePersist
    protected void prePersist() {
        syncUuidString();
    }

    protected void syncUuidString() {
        if (null == uuidStr) {
            // initial method call fills the uuid
            getUuid();
        }
    }

    /**
     * @return uuid
     */
    public UUID getUuid() {
        if (uuidStr == null) {
            if (uuid == null) {
                uuid = UUID.randomUUID();
            }
            uuidStr = uuid.toString();
        }
        if (uuid == null && uuidStr != null) {
            uuid = UUID.fromString(uuidStr);
        }
        return uuid;
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /*
     *
     * This method is mean for testing purposes only (create mock data), as we
     * should not set Ids manually, Hibernate will do it automatically
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractEntity that = (AbstractEntity) o;

        return getUuid().equals(that.getUuid());
    }

    @Override
    public int hashCode() {
//        if (getUuid() != null) {
        return getUuid().hashCode();
//        }
//        return 0;
    }

    /**
     * @return
     */
    public Long getVersion() {
        return version;
    }

    /**
     * @return
     */
    public String getUuidStr() {
        return uuidStr;
    }
}
