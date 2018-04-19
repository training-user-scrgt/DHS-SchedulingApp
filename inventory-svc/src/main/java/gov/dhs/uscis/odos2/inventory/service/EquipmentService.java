package gov.dhs.uscis.odos2.inventory.service;

import java.util.List;

import gov.dhs.uscis.odos2.inventory.dto.EquipmentDTO;
import gov.dhs.uscis.odos2.inventory.model.Equipment;

public interface EquipmentService {
	
	Equipment findByEquipmentId(Integer equipmentId);
	
	List<Equipment> getAllEquipments();
	
	//Equipment updateEquipments(Integer equipmentId);

	Equipment getEquipmentFromDTO(EquipmentDTO equipmentDTO);
}
