package gov.dhs.uscis.odos2.inventory.repository;

import gov.dhs.uscis.odos2.inventory.model.Building;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository class for the building entity
 *
 */
@Repository
public interface BuildingRepository extends CrudRepository<Building, Integer>  {

    /**
     * finds a list of buildings
     *
     * @return a List of buildings.
     */
    List<Building> findAll();

    /**
     * finds a building for buildingId
     *
     * @param buildingId - the building Id to view buildings
     * @return matching building.
     */
    Building getBuildingByBuildingId(int buildingId);

    /**
     * Delete a building, given its identifier
     *
     * @param buildingId building id
     */
    void deleteByBuildingId(int buildingId);

    /**
     * save changes made to a building, or create the building if its a new.
     *
     * @param Building building object to create
     * @return new building object
     */
    Building save(Building building);
}
