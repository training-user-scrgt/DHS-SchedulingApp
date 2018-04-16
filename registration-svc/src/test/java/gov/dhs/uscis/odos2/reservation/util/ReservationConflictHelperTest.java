package gov.dhs.uscis.odos2.reservation.util;

import gov.dhs.uscis.odos2.reservation.model.Reservation;
import gov.dhs.uscis.odos2.reservation.service.ReservationService;
import gov.dhs.uscis.odos2.reservation.service.ReservationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
public class ReservationConflictHelperTest {


    List<Reservation> reservationList;

    @Autowired
    private ReservationConflictHelper reservationConflictHelper;

    @TestConfiguration
    static class ReservationConflictHelperTestContextConfiguration {

        @Bean
        public ReservationConflictHelper reservationConflictHelper() {
            return new ReservationConflictHelper();
        }
    }

    @Before
    public void setUp() {

        reservationList = new ArrayList<Reservation>();

        Reservation reservation = new Reservation();
        reservation.setStartTime(LocalTime.of(9, 00));
        reservation.setEndTime(LocalTime.of(10, 00));

        reservationList.add(reservation);

        reservation = new Reservation();
        reservation.setStartTime(LocalTime.of(12, 00));
        reservation.setEndTime(LocalTime.of(13, 30));

        reservationList.add(reservation);

        reservation = new Reservation();
        reservation.setStartTime(LocalTime.of(14, 00));
        reservation.setEndTime(LocalTime.of(14, 30));

        reservationList.add(reservation);

    }

    @Test
    public void shouldBeAbleToAddReservationBeginning() {

        Reservation reservation = new Reservation();
        reservation.setStartTime(LocalTime.of(8, 30));
        reservation.setEndTime(LocalTime.of(9, 00));

        assertThat(reservationConflictHelper.isThereConflict(reservationList, reservation)).isTrue();

    }

    @Test
    public void shouldBeAbleToAddReservationEnd() {

        Reservation reservation = new Reservation();
        reservation.setStartTime(LocalTime.of(14, 30));
        reservation.setEndTime(LocalTime.of(16, 00));

        assertThat(reservationConflictHelper.isThereConflict(reservationList, reservation)).isTrue();

    }

    @Test
    public void shouldBeAbleToAddBetween() {

        Reservation reservation = new Reservation();
        reservation.setStartTime(LocalTime.of(10, 00));
        reservation.setEndTime(LocalTime.of(12, 00));

        assertThat(reservationConflictHelper.isThereConflict(reservationList, reservation)).isTrue();

    }


    @Test
    public void shouldConflict() {

        Reservation reservation = new Reservation();
        reservation.setStartTime(LocalTime.of(9, 30));
        reservation.setEndTime(LocalTime.of(10, 00));

        assertThat(reservationConflictHelper.isThereConflict(reservationList, reservation)).isFalse();

    }

    @Test
    public void shouldConflict2() {

        Reservation reservation = new Reservation();
        reservation.setStartTime(LocalTime.of(10, 00));
        reservation.setEndTime(LocalTime.of(12, 30));

        assertThat(reservationConflictHelper.isThereConflict(reservationList, reservation)).isFalse();

    }

    @Test
    public void shouldConflict3() {

        Reservation reservation = new Reservation();
        reservation.setStartTime(LocalTime.of(8, 00));
        reservation.setEndTime(LocalTime.of(11, 00));

        assertThat(reservationConflictHelper.isThereConflict(reservationList, reservation)).isFalse();

    }
}
