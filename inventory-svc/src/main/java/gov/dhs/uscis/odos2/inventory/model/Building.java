package gov.dhs.uscis.odos2.inventory.model;

import org.dom4j.tree.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The Building JPA entity.
 *
 * 
 */
@Entity
@Table(name = "building")
public final class Building extends AbstractEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1777774909169907516L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer buildingId;
    
	@Column(name = "building_name", nullable = false)
    private String buildingName;
    
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;
    
    @Column(name = "create_by", nullable = false)
    private UUID createBy;
    
    @Column(name = "update_date", nullable = true)
    private LocalDateTime updateDate;
    
    @Column(name = "update_id", nullable = true)
    private UUID updateId;

    /**
     * default constructor
     */
    public Building() {

    }
    
    public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public UUID getCreateBy() {
		return createBy;
	}

	public void setCreateBy(UUID createBy) {
		this.createBy = createBy;
	}

	public UUID getUpdateId() {
		return updateId;
	}

	public void setUpdateId(UUID updateId) {
		this.updateId = updateId;
	}	
}
