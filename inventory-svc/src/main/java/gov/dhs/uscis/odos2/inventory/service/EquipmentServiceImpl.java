package gov.dhs.uscis.odos2.inventory.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.dhs.uscis.odos2.inventory.model.Equipment;
import gov.dhs.uscis.odos2.inventory.repository.EquipmentRepository;

/**
 * Business service for Room entity related operations
 */
@Service
public class EquipmentServiceImpl implements EquipmentService{

    private static final Logger logger = LoggerFactory.getLogger(EquipmentServiceImpl.class);

    @Autowired
    private EquipmentRepository equipmentRepository;
    
    public Equipment findByEquipmentId(Integer equipmentId) {
        return equipmentRepository.findByEquipmentId(equipmentId);
    }

    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }
    
   /* @Transactional(readOnly = false)
    public Equipment updateEquipments(Equipment equipment) {
    	
    	
    	equipment.setUpdateDate(LocalDateTime.now());
    	equipment.setUpdateId(1);
    	
    	return equipmentRepository.save(equipment);
    }*/

    /**
     * @param reportId
     * @param amend
     * @param scheduleADTO
     * @return ScheduleA
     *//*
    public Equipment getEquipmentFromDTO(EquipmentDTO equipmentDTO) {
    	Equipment equipment = new Equipment();
    	equipment.setEquipmentId(equipmentDTO.getEquipmentId());
    	equipment.setEquipmentName(equipmentDTO.getEquipmentName());
    	equipment.setCreateDate(equipmentDTO.getCreateDate());
    	equipment.setCreateBy(equipmentDTO.getCreateBy());
    	equipment.setUpdateDate(equipmentDTO.getUpdateDate());
    	equipment.setUpdateId(equipmentDTO.getUpdateId());
        return equipment;
    }*/

    public void setEquipmentRepository(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }
    
}
