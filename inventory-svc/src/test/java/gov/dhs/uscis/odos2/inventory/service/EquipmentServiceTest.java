package gov.dhs.uscis.odos2.inventory.service;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import gov.dhs.uscis.odos2.inventory.model.Equipment;
import gov.dhs.uscis.odos2.inventory.repository.EquipmentRepository;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class EquipmentServiceTest {

	@Autowired
    private EquipmentService equipmentService;
	
    @MockBean
    private EquipmentRepository equipmentRepository;

    Equipment equipment;

    @TestConfiguration
    static class EquipmentServiceTestContextConfiguration {

        @Bean
        public EquipmentService equipmentService() {
            return new EquipmentService();
        }
    }
    
    @Before
    public void setUp() {
        this.equipment = new Equipment();
        this.equipment.setEquipmentId(1);
        this.equipment.setEquipmentName("Equipment 1");
    }

    @Test
    public void shouldfindByEquipmentId() {

        when(equipmentRepository.findByEquipmentId(any(Integer.class))).thenReturn(this.equipment);

    	Equipment equipmentResult = equipmentService.findByEquipmentId(1);
        assertThat(equipmentResult).isNotNull();
    }

    @Test
    public void shouldGetAllEquipments() {

    	List<Equipment> equipmentList = new ArrayList<Equipment>();
    	equipmentList.add(this.equipment);
    	
        when(equipmentRepository.findAll()).thenReturn(equipmentList);

    	List<Equipment> equipmentListResult = equipmentService.getAllEquipments();
        assertThat(equipmentListResult).isNotNull();
        assertThat(equipmentListResult.size()).isEqualTo(1);
    }
    
    @Test
    public void shouldUpdateEquipments() {

    	Equipment equipmentInput = new Equipment();
    	equipmentInput.setEquipmentId(1);
    	equipmentInput.setEquipmentName("Equipment one");
    	
        when(equipmentRepository.save(any(Equipment.class))).thenReturn(equipmentInput);

    	Equipment equipmentResult = equipmentService.updateEquipments(equipmentInput);
        assertThat(equipmentResult).isNotNull();
        assertThat(equipmentResult.getEquipmentName()).isEqualTo("Equipment one");
    }
}
