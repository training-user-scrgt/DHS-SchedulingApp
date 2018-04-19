package gov.dhs.uscis.odos2.reservation.service;

import gov.dhs.uscis.odos2.reservation.exception.InvalidReservationException;
import gov.dhs.uscis.odos2.reservation.model.Reservation;
import gov.dhs.uscis.odos2.reservation.repository.ReservationRepository;
import gov.dhs.uscis.odos2.reservation.util.ReservationConflictHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {


    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationConflictHelper reservationConflictHelper;

    @Override
    public Reservation createNewReservation(Reservation reservation) throws InvalidReservationException {

        validateReservationValues(reservation);

        checkConflicts(reservation);

        reservation.setCreateBy(1);
        reservation.setCreateDate(LocalDateTime.now());

        return reservationRepository.save(reservation);
    }

    private void checkConflicts(Reservation reservation) throws InvalidReservationException {
        List<Reservation> reservationList = reservationRepository
                .getAllByReservationDateAndRoomIdOrderByStartTime(
                        reservation.getReservationDate(),
                        reservation.getRoomId());

        // Let's look for any overlaping
        if (reservationConflictHelper.isThereConflict(reservationList, reservation)) {
            throw new InvalidReservationException("The reservation is invalid due to a conflict");
        }
    }

    private void validateReservationValues(Reservation reservation) throws InvalidReservationException {
        if (reservation != null
                && reservation.getReservationDate() != null
                && reservation.getStartTime() != null
                && reservation.getEndTime() != null
                && StringUtils.isNoneEmpty(reservation.getConferenceTitle())) {

            if (reservation.getReservationDate().isBefore(LocalDate.now())) {
                throw new InvalidReservationException("You can't make a reservation in the past");
            }

            if (reservation.getReservationDate().getDayOfWeek() == DayOfWeek.SATURDAY
                    || reservation.getReservationDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
                throw new InvalidReservationException("Reservations are only allowed form Monday to Friday");
            }

            if ((reservation.getStartTime().getMinute() != 0
                    && reservation.getStartTime().getMinute() != 15
                    && reservation.getStartTime().getMinute() != 30
                    && reservation.getStartTime().getMinute() != 45) ||
                    (reservation.getEndTime().getMinute() != 0
                    && reservation.getEndTime().getMinute() != 15
                    && reservation.getEndTime().getMinute() != 30
                    && reservation.getEndTime().getMinute() != 45)) {

                throw new InvalidReservationException("Reservations are only allowed in interval of 15 minutes");
            }

            if (reservation.getStartTime().isBefore(LocalTime.of(8, 0))
                    || reservation.getEndTime().isAfter(LocalTime.of(17, 0))) {

                throw new InvalidReservationException("Reservations are only allowed between 0800 and 1700");
            }

            // Minimum reservation time is 30 minutes
            if (Duration.between(reservation.getStartTime(), reservation.getEndTime()).toMinutes() < 30) {
                throw new InvalidReservationException("The minimum reservation time is 30 minutes");
            }

            // Max is 3 hours
            if (Duration.between(reservation.getStartTime(), reservation.getEndTime()).toMinutes() > 180) {
                throw new InvalidReservationException("The maximum reservation time is 3 hours");
            }

        } else {
            throw new IllegalArgumentException("Reservation required values missing");
        }
    }

    @Override
    public List<Reservation> getReservationsByDate(LocalDate date) {

        if (date == null) {
            throw new IllegalArgumentException("Invalid date");
        }

        return reservationRepository.getAllByReservationDateOrderByStartTime(date);
    }
}
