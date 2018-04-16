package gov.dhs.uscis.odos2.reservation.repository;

import gov.dhs.uscis.odos2.reservation.model.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

    List<Reservation> getAllByReservationDate(LocalDate date);

    Reservation save(Reservation reservation);


}
