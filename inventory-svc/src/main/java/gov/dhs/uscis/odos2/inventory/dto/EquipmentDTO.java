package gov.dhs.uscis.odos2.inventory.dto;

import gov.dhs.uscis.odos2.inventory.model.Equipment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JSON-serializable DTO containing Building data.
 */
public class EquipmentDTO {

    private Integer equipmentId;
    private String equipmentName;
    private LocalDateTime createDate;
    private Integer createBy;
    private LocalDateTime updateDate;
    private Integer updateId;
    

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

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(java.time.LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}

    
}
