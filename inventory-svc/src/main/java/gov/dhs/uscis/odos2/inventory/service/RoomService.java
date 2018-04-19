package gov.dhs.uscis.odos2.inventory.service;

import java.util.List;

import gov.dhs.uscis.odos2.inventory.exception.InvalidInventoryException;
import gov.dhs.uscis.odos2.inventory.model.Room;
import gov.dhs.uscis.odos2.inventory.model.RoomEquipment;

public interface RoomService {
	
	List<Room> getAllRooms();
	
	Room findByRoomId(Integer roomId);
	
	List<RoomEquipment> getEquipmentsOfRoom(Integer roomId);
	
	RoomEquipment addEquipment(RoomEquipment roomEquipment) throws InvalidInventoryException;
	
	public void removeRoomEquipment(Integer roomEquipmentId);
	
	Room updateRoomStatus(Room room) throws InvalidInventoryException;

}
