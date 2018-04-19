package gov.dhs.uscis.odos2.reservation.util;

import gov.dhs.uscis.odos2.reservation.model.Reservation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationConflictHelper {

    public boolean isThereConflict(List<Reservation> reservationList, Reservation reservation) {

        Reservation currentReservation;
        Reservation previousReservation = null;

        for (int i = 0; i < reservationList.size(); i++) {

            currentReservation = reservationList.get(i);

            // We are in the first reservation of the list
            if (previousReservation == null) {

                if (!reservation.getEndTime().isAfter(currentReservation.getStartTime())) {
                    return false;
                }
            } else if (i == reservationList.size() - 1) { // We are in the last reservation

                if (!reservation.getStartTime().isBefore(currentReservation.getEndTime())) {
                    return false;
                }

            } else {

                if (!reservation.getEndTime().isAfter(currentReservation.getStartTime()) &&
                        !reservation.getStartTime().isBefore(previousReservation.getEndTime())) {
                    return false;
                }

            }

            previousReservation = currentReservation;

        }

        return true;

    }


}
