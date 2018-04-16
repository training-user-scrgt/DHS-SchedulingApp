package gov.dhs.uscis.odos2.reservation.service;

import gov.dhs.uscis.odos2.reservation.exception.InvalidReservationException;
import gov.dhs.uscis.odos2.reservation.model.Reservation;
import gov.dhs.uscis.odos2.reservation.repository.ReservationRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {


    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Reservation createNewReservation(Reservation reservation) throws InvalidReservationException {

        if (reservation != null
                && reservation.getReservationDate() != null
                && reservation.getStartTime() != null
                && reservation.getEndTime() != null
                && StringUtils.isNoneEmpty(reservation.getConferenceTitle())) {

            // Minimum reservation time is 30 minutes
            if (Duration.between(reservation.getStartTime(), reservation.getEndTime()).toMinutes() < 30) {
                throw new InvalidReservationException("The minimum reservation time is 30 minutes");
            }

            // Max is 3 hours
            if (Duration.between(reservation.getStartTime(), reservation.getEndTime()).toMinutes() > 180) {
                throw new InvalidReservationException("The maximum reservation time is 3 hours");
            }

        } else {
            throw new IllegalArgumentException("Reservation is empty");
        }

        reservation.setCreateBy(1);
        reservation.setCreateDate(LocalDateTime.now());

        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getReservationsByDate(LocalDate date) {

        if (date == null) {
            throw new IllegalArgumentException("Invalid date");
        }

        return reservationRepository.getAllByReservationDate(date);
    }
}
