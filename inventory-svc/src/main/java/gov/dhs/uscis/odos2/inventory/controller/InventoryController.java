package gov.dhs.uscis.odos2.inventory.controller;

import gov.dhs.uscis.odos2.inventory.dto.RoomDTO;
import gov.dhs.uscis.odos2.inventory.dto.RoomEquipmentDTO;
import gov.dhs.uscis.odos2.inventory.dto.EquipmentDTO;
import gov.dhs.uscis.odos2.inventory.model.Equipment;
import gov.dhs.uscis.odos2.inventory.model.Room;
import gov.dhs.uscis.odos2.inventory.model.RoomEquipment;
import gov.dhs.uscis.odos2.inventory.service.EquipmentService;
import gov.dhs.uscis.odos2.inventory.service.RoomService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/*
 * AmendmentController This is the master REST controller using spring 4.3.10
 * 
 * It implements a method for each web request possible within the system. 
 * Currently this class has a method to invoke FEC OpenAPI and return JSONObject 
 * 
 */

@RestController
public class InventoryController {

    private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    private RoomService roomService;

    @Autowired
    private EquipmentService equipmentService;
    
    /**
     * This service will return all rooms
     *
     * @return list of rooms
     */
    @ResponseBody
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/rooms", produces = "application/json")
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
    	List<Room> roomList = roomService.getAllRooms();
        List<RoomDTO> roomDTOList = roomList.stream().map(RoomDTO::mapFromRoomEntity)
                .collect(Collectors.toList());
    	
    	return new ResponseEntity<List<RoomDTO>>(roomDTOList, HttpStatus.OK);
    }
    
    
    /**
     * This service will return room by ID
     *
     * @param roomId room id
     * @return room DTO
     */
    @ResponseBody
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/room/{room_id}", produces = "application/json")
    public ResponseEntity<RoomDTO> findRoomByRoomId(@PathVariable("room_id") Integer roomId) {
    	Room room = roomService.findByRoomId(roomId);
        return new ResponseEntity<RoomDTO>(RoomDTO.mapFromRoomEntity(room), HttpStatus.OK);
    }
    
    /**
     * This service will get all the equipments of room
     *
     * @param roomId     room id
     * @return ResponseList ResponseList
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/room_equipment/room/{room_id}", produces = "application/json")
    public ResponseEntity<List<RoomEquipmentDTO>> getEquimentsOfRoom(@PathVariable("room_id") Integer roomId) {
    	List<RoomEquipment> roomEquipmentList = roomService.getEquipmentsOfRoom(roomId);
    	List<RoomEquipmentDTO> roomEquipmentDTOList = roomEquipmentList.stream().map(RoomEquipmentDTO::mapFromRoomEquipmentEntity)
                .collect(Collectors.toList());
    	
        return new ResponseEntity<List<RoomEquipmentDTO>>(roomEquipmentDTOList, HttpStatus.OK);
    }
    
    /**
     * This service will add a equipment from room
     *
     * @param roomEquipmentId roomEquipment id
     */
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/room_equipment/room/{room_id}/equipment/{equipment_id}")
    public ResponseEntity<RoomEquipment> addRoomEquipment(@PathVariable("room_id") Integer roomId,
    		@PathVariable("equipment_id") Integer equipmentId) {
    	return new ResponseEntity<RoomEquipment>(roomService.AddEquipment(roomId, equipmentId), HttpStatus.OK);
    }
    
    /**
     * This service will delete a equipment from room
     *
     * @param roomEquipmentId roomEquipment id
     */
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/room_equipment/{room_equipment_id}")
    public ResponseEntity<Void> removeRoomEquipment(@PathVariable("room_equipment_id") Integer roomEquipmentId) {
    	HttpHeaders headers = new HttpHeaders();
    	roomService.RemoveEquipment(roomEquipmentId);
    	return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }
    
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/equipment/{equipment_id}", produces = "application/json")
    public ResponseEntity<EquipmentDTO> updateEquipment(@PathVariable("equipment_id") Integer equipmentId,
                                        @RequestBody EquipmentDTO equipmentDTO) {
    	Equipment equipment = equipmentService.updateEquipments(equipmentService.getEquipmentFromDTO(equipmentDTO));
        return new ResponseEntity<EquipmentDTO>(EquipmentDTO.mapFromEquipmentEntity(equipment), HttpStatus.OK);
    }

    /**
     * ExceptionHandler
     *
     * @param exc exception
     * @return generic response to unexpected requests
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorHandler(Exception exc) {
        logger.error(exc.getMessage(), exc);
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
