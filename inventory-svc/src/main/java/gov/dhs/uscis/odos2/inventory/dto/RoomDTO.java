package gov.dhs.uscis.odos2.inventory.dto;

import gov.dhs.uscis.odos2.inventory.model.Room;
import gov.dhs.uscis.odos2.inventory.model.Building;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JSON-serializable DTO containing Building data.
 */
public class RoomDTO {

    private Integer roomId;
    private Building building;
    private String roomName; 
    private boolean available;
    private Integer capacity;
    private LocalDateTime createDate;
    private Integer createBy;
    private LocalDateTime updateDate;
    private Integer updateId;
    

    /**
     * This is Default Constructor.
     */
    public RoomDTO() {

    }

    public static RoomDTO mapFromRoomEntity(Room room) {
    	RoomDTO roomDTO = new RoomDTO();
    	roomDTO.setRoomId(room.getRoomId());
    	roomDTO.setBuilding(room.getBuilding());
    	roomDTO.setRoomName(room.getRoomName());
    	roomDTO.setCapacity(room.getCapacity());
    	roomDTO.setAvailable(room.isAvailable());
    	roomDTO.setCreateDate(room.getCreateDate());
    	roomDTO.setCreateBy(room.getCreateBy());
    	roomDTO.setUpdateDate(room.getUpdateDate());
    	roomDTO.setUpdateId(room.getUpdateId());
        return roomDTO;
    }

    public static List<RoomDTO> mapFromBuildingsEntities(List<Room> rooms) {
        return rooms.stream().map((Room) -> mapFromRoomEntity(Room)).collect(Collectors.toList());
    }

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
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

	public void setUpdateDate(java.time.LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}

    
}
