package gov.dhs.uscis.odos2.reservation.service;

import gov.dhs.uscis.odos2.reservation.exception.InvalidReservationException;
import gov.dhs.uscis.odos2.reservation.model.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationService {

    Reservation createNewReservation(Reservation reservation) throws InvalidReservationException;

    List<Reservation> getReservationsByDate(LocalDate date);

    List<Reservation> getReservationsByDateAndTime(LocalDate date, LocalTime fromTime, LocalTime toTime);
}
