package gov.dhs.uscis.odos2.inventory.dto;

import gov.dhs.uscis.odos2.inventory.model.Building;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JSON-serializable DTO containing Building data.
 */
public class BuildingDTO {

    private Integer buildingId;
    private String buildingName;
    private LocalDateTime createDate;
    private Integer createBy;
    private LocalDateTime updateDate;
    private Integer updateId;
    

    /**
     * This is Default Constructor.
     */
    public BuildingDTO() {

    }

    public static BuildingDTO mapFromBuildingEntity(Building building) {
    	BuildingDTO buildingDTO = new BuildingDTO();
    	buildingDTO.setBuildingid(building.getBuildingId());
    	buildingDTO.setBuildingName(building.getBuildingName());
    	buildingDTO.setCreateDate(building.getCreateDate());
    	buildingDTO.setCreateBy(building.getCreateBy());
    	buildingDTO.setUpdateDate(building.getUpdateDate());
    	buildingDTO.setUpdateId(building.getUpdateId());
        return buildingDTO;
    }

    public static List<BuildingDTO> mapFromBuildingsEntities(List<Building> buildings) {
        return buildings.stream().map((Building) -> mapFromBuildingEntity(Building)).collect(Collectors.toList());
    }

	public Integer getBuildingid() {
		return buildingId;
	}

	public void setBuildingid(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
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
