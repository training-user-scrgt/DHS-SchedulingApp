package gov.dhs.uscis.odos2.inventory.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import gov.dhs.uscis.odos2.inventory.model.Building;
import gov.dhs.uscis.odos2.inventory.model.Equipment;
import gov.dhs.uscis.odos2.inventory.model.Room;
import gov.dhs.uscis.odos2.inventory.model.RoomEquipment;
import gov.dhs.uscis.odos2.inventory.service.BuildingService;
import gov.dhs.uscis.odos2.inventory.service.EquipmentService;
import gov.dhs.uscis.odos2.inventory.service.RoomService;

@RunWith(SpringRunner.class)
@WebMvcTest(InventoryController.class)
public class InventoryControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private BuildingService buildingService;

    @MockBean
    private RoomService roomService;
    
    @MockBean
    private EquipmentService equipmentService;
    
    private final String EQUIPMENTDTO_BODY = "{\n" +
            "\t\"equipmentId\": 1,\n" +
            "\t\"equipmentName\": \"Equipment 1\",\n" +
            "\t\"createDate\": \"2018-04-17T15:09:31.829\",\n" +
            "\t\"createBy\": 1,\n" +
            "\t\"updateDate\": \"2018-04-17T16:09:31.829\",\n" +
            "\t\"updateId\": 1\n" +
            "}";
    
    private final String ROOM_BODY = "{\n" +
            "\t\"roomId\": 1,\n" +
            "\t\"building\" : {\n" + 
            "\t\"buildingId\" : 1\n" + 
            "\t},\n" +         
            "\t\"available\": false,\n" +
            "\t\"createDate\": \"2018-04-17T15:09:31.829\",\n" +
            "\t\"createBy\": \"9c9b92ec-44ab-11e8-842f-0ed5f89f718b\",\n" +
            "\t\"updateDate\": null,\n" + 
    		"\t\"updateId\": \"9c9b92ec-44ab-11e8-842f-0ed5f89f718b\"\n" + 
            "}";
    
    private final String GOOD_ROOM_BODY = "{\n" + 
    		"\t\"room\" : {\n" + 
    		"\t\"roomId\" : 1\n" + 
    		"\t},\n" + 
    		"\t\"equipment\" : {\n" + 
    		"\t\"equipmentId\" : 1\n" + 
    		"\t},\n" + 
    		"\t\"createDate\": \"2018-04-19T15:09:31.829\",\n" + 
    		"\t\"createBy\": \"9c9b92ec-44ab-11e8-842f-0ed5f89f718b\",\n" + 
    		"\t\"updateDate\": null,\n" + 
    		"\t\"updateId\": null\n" + 
    		"}";

    @Test
    public void shouldGetAllBuildings() throws Exception {

    	List<Building> buildingList = new ArrayList<Building>();
        
        when(buildingService.getAllBuildings()).thenReturn(buildingList);

        mvc.perform(get("/building")
        		.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk());

    }
    
    @Test
    public void shouldGetAllRooms() throws Exception {

    	List<Room> roomList = new ArrayList<Room>();
        /*Room room = new Room();
        room.setRoomId(1);
        room.setRoomName("Room 1");
        roomList.add(room);*/

        when(roomService.getAllRooms()).thenReturn(roomList);

        mvc.perform(get("/room")
        		.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk());

    }
    
    @Test
    public void shouldGetAllEquipments() throws Exception {

    	List<Equipment> equipmentList = new ArrayList<Equipment>();
        
        when(equipmentService.getAllEquipments()).thenReturn(equipmentList);

        mvc.perform(get("/equipment")
        		.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk());

    }
    
   @Test
    public void shouldFindRoomByRoomId() throws Exception {

    	Room room = new Room();
        room.setRoomId(1);
        //room.setRoomName("Room 1");
        
        when(roomService.findByRoomId(any(Integer.class))).thenReturn(room);

        mvc.perform(get("/room/1")
        		.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk());

    }
    
    @Test
    public void shouldGetEquimentsOfRoom() throws Exception {

    	List<RoomEquipment> roomEquipmentList = new ArrayList<RoomEquipment>();
    	RoomEquipment roomEquipment = new RoomEquipment();
    	roomEquipment.setRoomEquipmentId(1);
    	
    	Room room = new Room();
        room.setRoomId(1);
        //room.setRoomName("Room 1");
        roomEquipment.setRoom(room);
        
        Equipment equipment = new Equipment();
        equipment.setEquipmentId(3);
        //equipment.setEquipmentName("Equiment 1");
        roomEquipment.setEquipment(equipment);
        roomEquipmentList.add(roomEquipment);
        
        when(roomService.getEquipmentsOfRoom(any(Integer.class))).thenReturn(roomEquipmentList);

        mvc.perform(get("/room/1/equipment")
        		.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk());

    }
    
    @Test
    public void shouldAddRoomEquipment() throws Exception {

    	RoomEquipment roomEquipment = new RoomEquipment();
    	//roomEquipment.setRoomEquipmentId(5);
    	
    	Room room = new Room();
        room.setRoomId(1);
        room.setRoomName("CONF_ROOM_1");
        roomEquipment.setRoom(room);
        
        Equipment equipment = new Equipment();
        equipment.setEquipmentId(1);
        equipment.setEquipmentName("Projector");
        roomEquipment.setEquipment(equipment);
        roomEquipment.setCreateDate(LocalDateTime.now());
        roomEquipment.setUpdateDate(null);
        roomEquipment.setUpdateId(null);
        roomEquipment.setCreateBy(UUID.randomUUID());
        
        when(roomService.addEquipment(any(RoomEquipment.class))).thenReturn(roomEquipment);

        mvc.perform(post("/room/1/equipment")
        		.content(GOOD_ROOM_BODY)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
    
    @Test
    public void shouldUpdateRoom() throws Exception {

        Room room = new Room();
        room.setRoomId(1);
        room.setAvailable(false);
        
        when(roomService.updateRoomStatus(any(Room.class))).thenReturn(room);

        mvc.perform(post("/room/1")
        		.content(ROOM_BODY)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
    
   @Test
    public void shouldRemoveRoomEquipment() throws Exception {

        mvc.perform(post("/room_equipment/12")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
