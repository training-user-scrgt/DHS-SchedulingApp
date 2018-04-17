package gov.dhs.uscis.odos2.inventory.service;

import gov.dhs.uscis.odos2.inventory.dto.RoomDTO;
import gov.dhs.uscis.odos2.inventory.model.Equipment;
import gov.dhs.uscis.odos2.inventory.model.Room;
import gov.dhs.uscis.odos2.inventory.model.RoomEquipment;
import gov.dhs.uscis.odos2.inventory.repository.EquipmentRepository;
import gov.dhs.uscis.odos2.inventory.repository.RoomEquipmentRepository;
import gov.dhs.uscis.odos2.inventory.repository.RoomRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Business service for Room entity related operations
 */
@Service
public class RoomService {

    private static final Logger logger = LoggerFactory.getLogger(RoomService.class);

    @Autowired
    private RoomRepository roomRepository;
    
    @Autowired
    private EquipmentRepository equipmentRepository;
    
    @Autowired
    private RoomEquipmentRepository roomEquipmentRepository;

    @Transactional(readOnly = true)
    public Room findByRoomId(Integer roomId) {
        return roomRepository.findByRoomId(roomId);
    }

    @Transactional(readOnly = true)
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
    
    /**
     * Get equipments of a room
     */
    @Transactional
    public List<RoomEquipment> getEquipmentsOfRoom(Integer roomId) {
    	Room room = findByRoomId(roomId);
        return roomEquipmentRepository.findByRoom(room);
    }

    /**
     * Add new equipment to a room
     */
    @Transactional
    public RoomEquipment AddEquipment(Integer roomId, Integer equipmentId) {
    	Room room = findByRoomId(roomId);
    	Equipment equipment = equipmentRepository.findByEquipmentId(equipmentId);
    	
    	RoomEquipment roomEquipment = new RoomEquipment();
    	roomEquipment.setRoom(room);
    	roomEquipment.setEquipment(equipment);
    	roomEquipment.setCreateDate(LocalDateTime.now());
    	roomEquipment.setCreateBy(1);
    	
    	roomEquipment = roomEquipmentRepository.save(roomEquipment);
        return roomEquipment;
    }

    /**
     * deletes room from the database
     */
    @Transactional
    public void RemoveEquipment(Integer roomEquipmentId) {
    	roomEquipmentRepository.deleteByRoomEquipmentId(roomEquipmentId);
    }

    /**
     * @param reportId
     * @param amend
     * @param scheduleADTO
     * @return ScheduleA
     */
    private Room getRoomFromDTO(RoomDTO roomDTO) {
    	Room room = new Room();
    	room.setRoomId(roomDTO.getRoomId());
    	room.setRoomName(roomDTO.getRoomName());
    	room.setAvailable(roomDTO.isAvailable());
    	room.setCapacity(roomDTO.getCapacity());
    	
    	room.setBuilding(roomDTO.getBuilding());
    	room.setCreateDate(roomDTO.getCreateDate());
    	room.setCreateBy(roomDTO.getCreateBy());
    	room.setUpdateDate(roomDTO.getUpdateDate());
    	room.setUpdateId(roomDTO.getUpdateId());
        return room;
    }
}
