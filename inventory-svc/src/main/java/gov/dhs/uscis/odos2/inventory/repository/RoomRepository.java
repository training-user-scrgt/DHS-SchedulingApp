package gov.dhs.uscis.odos2.inventory.repository;

import gov.dhs.uscis.odos2.inventory.model.Room;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository class for the Room entity
 *
 */
@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {

    /**
     * finds a list of rooms
     *
     * @return a List of rooms.
     */
    List<Room> findAll();

    /**
     * finds a room for roomId
     *
     * @param roomId - the room Id to view rooms
     * @return matching room.
     */
    Room findByRoomId(int roomId);

    
    /**
     * Delete a room, given its identifier
     *
     * @param roomId room id
     */
    void deleteByRoomId(int roomId);

    /**
     * save changes made to a room, or create the building if its a new.
     *
     * @param Room room object to create
     * @return new room object
     */
    Room save(Room room);
    
    /**
     * finds a list of rooms available
     *
     * @return a List of rooms available.
     */
    List<Room> findRoomByAvailable(boolean value);

}
