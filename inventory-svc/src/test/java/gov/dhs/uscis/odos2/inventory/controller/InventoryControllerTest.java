package gov.dhs.uscis.odos2.inventory.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import gov.dhs.uscis.odos2.inventory.dto.EquipmentDTO;
import gov.dhs.uscis.odos2.inventory.model.Equipment;
import gov.dhs.uscis.odos2.inventory.model.Room;
import gov.dhs.uscis.odos2.inventory.model.RoomEquipment;
import gov.dhs.uscis.odos2.inventory.service.*;

@RunWith(SpringRunner.class)
@WebMvcTest(InventoryController.class)
public class InventoryControllerTest {

    @Autowired
    private MockMvc mvc;

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

    @Test
    public void shouldGetAllRooms() throws Exception {

    	List<Room> roomList = new ArrayList<Room>();
        Room room = new Room();
        room.setRoomId(1);
        room.setRoomName("Room 1");
        roomList.add(room);

        when(roomService.getAllRooms()).thenReturn(roomList);

        mvc.perform(get("/rooms")
        		.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk());

    }
    
    @Test
    public void shouldFindRoomByRoomId() throws Exception {

    	Room room = new Room();
        room.setRoomId(1);
        room.setRoomName("Room 1");
        
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
        room.setRoomName("Room 1");
        roomEquipment.setRoom(room);
        
        Equipment equipment = new Equipment();
        equipment.setEquipmentId(1);
        equipment.setEquipmentName("Equiment 1");
        roomEquipment.setEquipment(equipment);
        roomEquipmentList.add(roomEquipment);
        
        when(roomService.getEquipmentsOfRoom(any(Integer.class))).thenReturn(roomEquipmentList);

        mvc.perform(get("/room_equipment/room/1")
        		.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk());

    }
    
    @Test
    public void shouldAddRoomEquipment() throws Exception {

    	RoomEquipment roomEquipment = new RoomEquipment();
    	roomEquipment.setRoomEquipmentId(1);
    	
    	Room room = new Room();
        room.setRoomId(1);
        room.setRoomName("Room 1");
        roomEquipment.setRoom(room);
        
        Equipment equipment = new Equipment();
        equipment.setEquipmentId(1);
        equipment.setEquipmentName("Equiment 1");
        roomEquipment.setEquipment(equipment);
        
        when(roomService.AddEquipment(any(Integer.class), any(Integer.class))).thenReturn(roomEquipment);

        mvc.perform(post("/room_equipment/room/1/equipment/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    
    @Test
    public void shouldUpdateEquipment() throws Exception {

        Equipment equipment = new Equipment();
        equipment.setEquipmentId(1);
        equipment.setEquipmentName("Equiment 1");
        
        when(equipmentService.updateEquipments(any(Equipment.class))).thenReturn(equipment);
        when(equipmentService.getEquipmentFromDTO(any(EquipmentDTO.class))).thenReturn(equipment);

        mvc.perform(post("/equipment/1")
        		.content(EQUIPMENTDTO_BODY)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    
    @Test
    public void shouldRemoveRoomEquipment() throws Exception {

        mvc.perform(post("/room_equipment/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
