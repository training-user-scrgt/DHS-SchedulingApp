package gov.dhs.uscis.odos2.inventory.model;

import org.dom4j.tree.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime;

/**
 * The Building JPA entity.
 *
 * 
 */
@Entity
@Table(name = "equipment")
public final class Equipment extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2207980795802279921L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer equipmentId;
    
	@Column(name = "equipment_name", nullable = false)
    private String equipmentName;
    
	@Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;
    
    @Column(name = "create_by", nullable = false)
    private Integer createBy;
    
    @Column(name = "update_date", nullable = true)
    private LocalDateTime updateDate;
    
    @Column(name = "update_id", nullable = true)
    private Integer updateId;

    /**
     * default constructor
     */
    public Equipment() {

    }

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}
}
