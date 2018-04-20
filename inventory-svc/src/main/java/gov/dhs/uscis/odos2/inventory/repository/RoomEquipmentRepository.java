package gov.dhs.uscis.odos2.inventory.repository;

import gov.dhs.uscis.odos2.inventory.model.Room;
import gov.dhs.uscis.odos2.inventory.model.RoomEquipment;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class for the RoomEquipment entity
 *
 */
@Repository
public interface RoomEquipmentRepository extends CrudRepository<RoomEquipment, Integer> {

    /**
     * finds a roomEquipment for roomEquipmentId
     *
     * @param roomEquipmentId - the roomEquipment Id to view roomEquipment
     * @return matching roomEquipment.
     */
    RoomEquipment findByRoomEquipmentId(int roomEquipmentId);
    
    /**
     * finds a roomEquipment for roomId
     *
     * @param roomId - the room Id to view roomEquipment
     * @return matching roomEquipment.
     */
    List<RoomEquipment> findByRoom(Room room);

    
    /**
     * Delete a roomEquipment, given its identifier
     *
     * @param roomEquipmentId roomEquipment id
     */
    void deleteByRoomEquipmentId(int roomEquipmentId);
    
    /**
     * save changes made to a roomEquipment, or create the roomEquipment if its a new.
     *
     * @param RoomEquipment roomEquipment object to create
     * @return new roomEquipment object
     */
    RoomEquipment save(RoomEquipment roomEquipment);

}
