package gov.dhs.uscis.odos2.reservation.repository;

import gov.dhs.uscis.odos2.reservation.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

    List<Reservation> getAllByReservationDateOrderByStartTime(LocalDate date);
    List<Reservation> getAllByReservationDateAndRoomIdOrderByStartTime(LocalDate date, int roomId);

    @Query(value = "select *\n" +
            "from reservation\n" +
            "where reservation_date = :reservationDate\n" +
            "and ((start_time > :fromTime and start_time < :toTime)\n" +
            "     or (end_time > :fromTime and end_time < :toTime))",
            nativeQuery = true)
    List<Reservation> getReservationsByReservationDateAndTime(
            @Param("reservationDate") LocalDate reservationDate,
            @Param("fromTime") LocalTime fromTime,
            @Param("toTime") LocalTime toTime);

    Reservation save(Reservation reservation);


}
