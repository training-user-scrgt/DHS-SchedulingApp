package gov.dhs.uscis.odos2.inventory.model;

import org.dom4j.tree.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The Building JPA entity.
 *
 * 
 */
@Entity
@Table(name = "room_equipment")
public final class RoomEquipment extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2056317105792688707L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer roomEquipmentId;
    
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="room_id", nullable = false)
	private Room room;
    
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="equipment_id", nullable = false)
	private Equipment equipment;
    
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
    public RoomEquipment() {

    }

	public Integer getRoomEquipmentId() {
		return roomEquipmentId;
	}

	public void setRoomEquipmentId(Integer roomEquipmentId) {
		this.roomEquipmentId = roomEquipmentId;
	}
	
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
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
