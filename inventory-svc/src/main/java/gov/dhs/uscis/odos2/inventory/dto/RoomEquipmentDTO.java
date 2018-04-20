package gov.dhs.uscis.odos2.inventory.dto;

import gov.dhs.uscis.odos2.inventory.model.Equipment;
import gov.dhs.uscis.odos2.inventory.model.Room;
import gov.dhs.uscis.odos2.inventory.model.RoomEquipment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * JSON-serializable DTO containing Building data.
 */
public class RoomEquipmentDTO {

    private Integer roomEquipmentId;
    private Room room;
    private Equipment equipment;
    private LocalDateTime createDate;
    private UUID createBy;
    private LocalDateTime updateDate;
    private UUID updateId;
    

    /**
     * This is Default Constructor.
     */
    public RoomEquipmentDTO() {

    }

    public static RoomEquipmentDTO mapFromRoomEquipmentEntity(RoomEquipment roomEquipment) {
    	RoomEquipmentDTO roomEquipmentDTO = new RoomEquipmentDTO();
    	roomEquipmentDTO.setRoomEquipmentId(roomEquipment.getRoomEquipmentId());
    	roomEquipmentDTO.setRoom(roomEquipment.getRoom());
    	roomEquipmentDTO.setEquipment(roomEquipment.getEquipment());
    	roomEquipmentDTO.setCreateDate(roomEquipment.getCreateDate());
    	roomEquipmentDTO.setCreateBy(roomEquipment.getCreateBy());
    	roomEquipmentDTO.setUpdateDate(roomEquipment.getUpdateDate());
    	roomEquipmentDTO.setUpdateId(roomEquipment.getUpdateId());
        return roomEquipmentDTO;
    }

    public static List<RoomEquipmentDTO> mapFromBuildingsEntities(List<RoomEquipment> roomEquipments) {
        return roomEquipments.stream().map((RoomEquipment) -> mapFromRoomEquipmentEntity(RoomEquipment)).collect(Collectors.toList());
    }

	public Integer getRoomEquipmentId() {
		return roomEquipmentId;
	}

	public void setRoomEquipmentId(Integer roomEquipmentId) {
		this.roomEquipmentId = roomEquipmentId;
	}

	public final Room getRoom() {
		return room;
	}

	public final void setRoom(Room room) {
		this.room = room;
	}

	public final Equipment getEquipment() {
		return equipment;
	}

	public final void setEquipment(Equipment equipment) {
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

	public void setUpdateDate(java.time.LocalDateTime updateDate) {
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
