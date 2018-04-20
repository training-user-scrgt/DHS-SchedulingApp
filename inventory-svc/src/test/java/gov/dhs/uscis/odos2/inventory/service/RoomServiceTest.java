package gov.dhs.uscis.odos2.inventory.service;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import gov.dhs.uscis.odos2.inventory.exception.InvalidInventoryException;
import gov.dhs.uscis.odos2.inventory.model.Building;
import gov.dhs.uscis.odos2.inventory.model.Equipment;
import gov.dhs.uscis.odos2.inventory.model.Room;
import gov.dhs.uscis.odos2.inventory.model.RoomEquipment;
import gov.dhs.uscis.odos2.inventory.repository.EquipmentRepository;
import gov.dhs.uscis.odos2.inventory.repository.RoomEquipmentRepository;
import gov.dhs.uscis.odos2.inventory.repository.RoomRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    @MockBean
    private RoomRepository roomRepository;
    
    @MockBean
    private EquipmentRepository equipmentRepository;
    
    @MockBean
    private RoomEquipmentRepository roomEquipmentRepository;
    
    Room room;
    
    @TestConfiguration
    static class RoomServiceTestContextConfiguration {

        @Bean
        public RoomService roomService() {
            return new RoomServiceImpl();
        }
    }
    
    @Before
    public void setUp() {
        this.room = new Room();
        this.room.setRoomId(3);
        this.room.setRoomName("Room 1");
        this.room.setAvailable(true);
        this.room.setCapacity(10);
        
        Building building = new Building();
        building.setBuildingId(2);
        building.setBuildingName("Building 1");
        
        this.room.setBuilding(building);
    }

    @Test
    public void shouldGetAllRooms() {
    	List<Room> roomList = new ArrayList<Room>();
    	roomList.add(this.room);
    	
        when(roomRepository.findRoomByAvailable(true)).thenReturn(roomList);

    	List<Room> roomListResult = roomService.getAllRooms();
        assertThat(roomListResult).isNotNull();
        assertThat(roomListResult.size()).isEqualTo(1);
    }
    
    @Test
    public void shouldGetEquipmentsOfRoom() {
    	
    	List<RoomEquipment> roomEquipmentList = new ArrayList<RoomEquipment>();
    	RoomEquipment roomEquipment = new RoomEquipment();
    	roomEquipment.setRoomEquipmentId(1);
        roomEquipment.setRoom(this.room);
        
        Equipment equipment = new Equipment();
        equipment.setEquipmentId(1);
        equipment.setEquipmentName("Equiment 1");
        roomEquipment.setEquipment(equipment);
        roomEquipmentList.add(roomEquipment);
    	
        when(roomRepository.findByRoomId(any(Integer.class))).thenReturn(this.room);
    	when(roomEquipmentRepository.findByRoom(any(Room.class))).thenReturn(roomEquipmentList);
        
        List<RoomEquipment> roomEquipmentListResult = roomService.getEquipmentsOfRoom(1);
        assertThat(roomEquipmentListResult).isNotNull();
        assertThat(roomEquipmentListResult.size()).isEqualTo(1);
    }

    @Test
    public void shouldAddEquipment() throws InvalidInventoryException {
    	
    	RoomEquipment roomEquipment = new RoomEquipment();
    	roomEquipment.setRoomEquipmentId(1);
        roomEquipment.setRoom(this.room);
        
        Equipment equipment = new Equipment();
        equipment.setEquipmentId(1);
        equipment.setEquipmentName("Equiment 1");
        roomEquipment.setEquipment(equipment);
    	
    	when(roomRepository.findByRoomId(any(Integer.class))).thenReturn(this.room);
    	when(equipmentRepository.findByEquipmentId(any(Integer.class))).thenReturn(equipment);
    	when(roomEquipmentRepository.save(any(RoomEquipment.class))).thenReturn(roomEquipment);
    	
    	RoomEquipment roomEquipmentResult = roomService.addEquipment(roomEquipment);
    	assertThat(roomEquipmentResult).isNotNull();
    }

    @Test
    public void shouldRemoveEquipment() {
    	roomService.removeRoomEquipment(1);
    	verify(roomEquipmentRepository).deleteByRoomEquipmentId(1);
    }
}
