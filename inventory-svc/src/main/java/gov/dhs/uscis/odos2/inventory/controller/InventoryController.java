package gov.dhs.uscis.odos2.inventory.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import gov.dhs.uscis.odos2.inventory.exception.InvalidInventoryException;
import gov.dhs.uscis.odos2.inventory.model.Building;
import gov.dhs.uscis.odos2.inventory.model.Equipment;
import gov.dhs.uscis.odos2.inventory.model.Room;
import gov.dhs.uscis.odos2.inventory.model.RoomEquipment;
import gov.dhs.uscis.odos2.inventory.service.BuildingService;
import gov.dhs.uscis.odos2.inventory.service.EquipmentService;
import gov.dhs.uscis.odos2.inventory.service.RoomService;

/*
 * AmendmentController This is the master REST controller using spring 4.3.10
 * 
 * It implements a method for each web request possible within the system. 
 * Currently this class has a method to invoke FEC OpenAPI and return JSONObject 
 * 
 */
@CrossOrigin
@RestController
public class InventoryController {

    private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    private BuildingService buildingService;
    
    @Autowired
    private RoomService roomService;

    @Autowired
    private EquipmentService equipmentService;
    
    /**
     * This service will return all rooms
     *
     * @return list of rooms
     */
    @GetMapping
    @ResponseBody
    @RequestMapping(value = "/building", produces = "application/json")
    public ResponseEntity<List<Building>> getAllBuildings() {

    	List<Building> buildingList = buildingService.getAllBuildings();

        return new ResponseEntity<List<Building>>(
        		buildingList, HttpStatus.OK);
    }
    
    /**
     * This service will return all rooms
     *
     * @return list of rooms
     */
    @GetMapping
    @ResponseBody
    @RequestMapping(value = "/room", produces = "application/json")
    public ResponseEntity<List<Room>> getAllRooms() {

    	List<Room> roomList = roomService.getAllRooms();

        return new ResponseEntity<List<Room>>(
        		roomList, HttpStatus.OK);
    }
    
    /**
     * This service will return room by ID
     *
     * @param roomId room id
     * @return room DTO
     */
    @GetMapping
    @ResponseBody
    @RequestMapping(value = "/room/{room_id}", produces = "application/json")
    public ResponseEntity<Room> findRoomByRoomId(@PathVariable("room_id") Integer roomId) {

    	Room room = roomService.findByRoomId(roomId);
    	if(null == room) {
    		logger.error("Room is not Found for this room id");
    		return new ResponseEntity<Room>(room, HttpStatus.NOT_FOUND);
    	}

    	return new ResponseEntity<Room>(room, HttpStatus.OK);
    }
    
    /**
     * This service will get all the equipments of room
     *
     * @param roomId     room id
     * @return ResponseList ResponseList
     */
    @GetMapping
    @ResponseBody
    @RequestMapping(value = "/room/{room_id}/equipment", produces = "application/json")
    public ResponseEntity<List<RoomEquipment>> getEquimentsOfRoom(@PathVariable("room_id") Integer roomId) {
    	
    	List<RoomEquipment> roomEquipmentList = roomService.getEquipmentsOfRoom(roomId);
    		if(roomEquipmentList.isEmpty()) {
    			logger.error("Room Equipment is not Found for this room id");
    			return new ResponseEntity<List<RoomEquipment>>(roomEquipmentList, HttpStatus.NOT_FOUND);
    		}
    		
    	return new ResponseEntity<List<RoomEquipment>>(roomEquipmentList, HttpStatus.OK);
    }
    
    /**
     * This service will add a equipment from room
     *
     * @param roomEquipmentId roomEquipment id
     */
    @PostMapping(value = "/room/{room_id}/equipment", produces = "application/json")
    public ResponseEntity<RoomEquipment> addRoomEquipment(@RequestBody RoomEquipment roomEquipment, UriComponentsBuilder builder) {

        HttpHeaders headers = new HttpHeaders();

        try {
        	RoomEquipment newRoomEquipment = roomService.addEquipment(roomEquipment);
            headers.setLocation(builder.path("/room/{room_id}/equipment").buildAndExpand
            		(newRoomEquipment.getRoomEquipmentId()).toUri());

        } catch (InvalidInventoryException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<RoomEquipment>(headers, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<RoomEquipment>(headers, HttpStatus.CREATED);
    }
    
    /**
     * This service will return all rooms
     *
     * @return list of rooms
     */
    @GetMapping
    @ResponseBody
    @RequestMapping(value = "/equipment", produces = "application/json")
    public ResponseEntity<List<Equipment>> getAllEquipments() {

    	List<Equipment> roomList = equipmentService.getAllEquipments();

        return new ResponseEntity<List<Equipment>>(
        		roomList, HttpStatus.OK);
    }
    
    /**
     * This service will delete a equipment from room
     *
     * @param roomEquipmentId roomEquipment id
     */
    
    @PostMapping(value = "/room_equipment/{room_equipment_id}")
    public ResponseEntity<Void> removeRoomEquipment(@PathVariable("room_equipment_id") Integer roomEquipmentId) {
    	HttpHeaders headers = new HttpHeaders();
    	roomService.removeRoomEquipment(roomEquipmentId);
    	return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }
    
    @PutMapping(value = "/room/{room_id}", produces = "application/json")
    public ResponseEntity<Void> updateRoomStatus(@RequestBody Room room, UriComponentsBuilder builder) {
    
    	HttpHeaders headers = new HttpHeaders();

        try {
        	Room updateStatus = roomService.updateRoomStatus(room);
            headers.setLocation(builder.path("/room/{room_id}").buildAndExpand(updateStatus.getRoomId()).toUri());
        } catch (InvalidInventoryException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<Void>(headers, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
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
