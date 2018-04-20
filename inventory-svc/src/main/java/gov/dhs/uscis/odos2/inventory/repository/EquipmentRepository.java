package gov.dhs.uscis.odos2.inventory.repository;

import gov.dhs.uscis.odos2.inventory.model.Equipment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository class for the equipment entity
 *
 */
@Repository
public interface EquipmentRepository extends CrudRepository<Equipment, Integer> {

    /**
     * finds a list of Equipments
     *
     * @return a List of Equipments.
     */
    List<Equipment> findAll();

    /**
     * finds a equipment for equipmentId
     *
     * @param equipmentId - the equipment Id to view equipments
     * @return matching equipment.
     */
    Equipment findByEquipmentId(int equipmentId);

    
    /**
     * Delete a Equipment, given its identifier
     *
     * @param equipmentId equipment id
     */
    void deleteByEquipmentId(int equipmentId);

    /**
     * save changes made to a equipment, or create the equipment if its a new.
     *
     * @param Equipment equipment object to create
     * @return new equipment object
     */
    Equipment save(Equipment equipment);

}
