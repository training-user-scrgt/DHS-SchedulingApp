package gov.dhs.uscis.odos2.inventory.service;

import gov.dhs.uscis.odos2.inventory.dto.EquipmentDTO;
import gov.dhs.uscis.odos2.inventory.model.Equipment;
import gov.dhs.uscis.odos2.inventory.repository.EquipmentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Business service for Room entity related operations
 */
@Service
public class EquipmentService {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentService.class);

    @Autowired
    private EquipmentRepository equipmentRepository;
    
    public Equipment findByEquipmentId(Integer equipmentId) {
        return equipmentRepository.findByEquipmentId(equipmentId);
    }

    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }
    
    @Transactional(readOnly = false)
    public Equipment updateEquipments(Equipment equipment) {
    	return equipmentRepository.save(equipment);
    }

    /**
     * @param reportId
     * @param amend
     * @param scheduleADTO
     * @return ScheduleA
     */
    public Equipment getEquipmentFromDTO(EquipmentDTO equipmentDTO) {
    	Equipment equipment = new Equipment();
    	equipment.setEquipmentId(equipmentDTO.getEquipmentId());
    	equipment.setEquipmentName(equipmentDTO.getEquipmentName());
    	equipment.setCreateDate(equipmentDTO.getCreateDate());
    	equipment.setCreateBy(equipmentDTO.getCreateBy());
    	equipment.setUpdateDate(equipmentDTO.getUpdateDate());
    	equipment.setUpdateId(equipmentDTO.getUpdateId());
        return equipment;
    }

    public void setEquipmentRepository(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }
    
}
