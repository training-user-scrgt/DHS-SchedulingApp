package gov.dhs.uscis.odos2.inventory.dto;

import gov.dhs.uscis.odos2.inventory.model.Equipment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * JSON-serializable DTO containing Building data.
 */
public class EquipmentDTO {

    private Integer equipmentId;
    private String equipmentName;
    private LocalDateTime createDate;
    private UUID createBy;
    private LocalDateTime updateDate;
    private UUID updateId;
    

    /**
     * This is Default Constructor.
     */
    public EquipmentDTO() {

    }

    public static EquipmentDTO mapFromEquipmentEntity(Equipment equipment) {
    	EquipmentDTO buildingDTO = new EquipmentDTO();
    	buildingDTO.setEquipmentId(equipment.getEquipmentId());
    	buildingDTO.setEquipmentName(equipment.getEquipmentName());
    	buildingDTO.setCreateDate(equipment.getCreateDate());
    	buildingDTO.setCreateBy(equipment.getCreateBy());
    	buildingDTO.setUpdateDate(equipment.getUpdateDate());
    	buildingDTO.setUpdateId(equipment.getUpdateId());
        return buildingDTO;
    }

    public static List<EquipmentDTO> mapFromBuildingsEntities(List<Equipment> equipments) {
        return equipments.stream().map((Equipment) -> mapFromEquipmentEntity(Equipment)).collect(Collectors.toList());
    }

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(java.time.LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public UUID getCreateBy() {
		return createBy;
	}

	public void setCreateBy(UUID createBy) {
		this.createBy = createBy;
	}

	public UUID getUpdateId() {
		return updateId;
	}

	public void setUpdateId(UUID updateId) {
		this.updateId = updateId;
	}    
}
