package gov.dhs.uscis.odos2.reservation.service;


import gov.dhs.uscis.odos2.reservation.exception.InvalidReservationException;
import gov.dhs.uscis.odos2.reservation.model.Reservation;
import gov.dhs.uscis.odos2.reservation.repository.ReservationRepository;
import gov.dhs.uscis.odos2.reservation.util.ReservationConflictHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationCreationTest {

    @Autowired
    private ReservationService reservationService;

    @MockBean
    private ReservationRepository reservationRepository;

    @MockBean
    private ReservationConflictHelper reservationConflictHelper;

    Reservation reservation;

    @TestConfiguration
    static class ReservationServiceImplTestContextConfiguration {

        @Bean
        public ReservationService reservationService() {
            return new ReservationServiceImpl();
        }

        @Bean
        public ReservationConflictHelper reservationConflictHelper() {
            return new ReservationConflictHelper();
        }
    }

    @Before
    public void setUp() {
        this.reservation = new Reservation();
        this.reservation.setConferenceTitle("Some title");
        this.reservation.setReservationDate(LocalDate.now());
        this.reservation.setStartTime(LocalTime.of(12, 0));
        this.reservation.setEndTime(LocalTime.of(13, 0));
    }

    @Test(expected = InvalidReservationException.class)
    public void shouldThrowExceptionDueToMinTimeConstraint() throws InvalidReservationException {

        this.reservation.setEndTime(LocalTime.of(12, 15));
        reservationService.createNewReservation(this.reservation);
    }

    @Test(expected = InvalidReservationException.class)
    public void shouldThrowExceptionDueToMaxTimeConstraint() throws InvalidReservationException {

        this.reservation.setEndTime(LocalTime.of(16, 15));
        reservationService.createNewReservation(this.reservation);

    }

    @Test(expected = InvalidReservationException.class)
    public void shouldThrowExceptionDueToConflict() throws InvalidReservationException {

        when(reservationConflictHelper
                .isThereConflict(anyList(), any(Reservation.class))).thenReturn(true);
        reservationService.createNewReservation(this.reservation);

    }

    @Test(expected = InvalidReservationException.class)
    public void shouldThrowExceptionDueToPass1700() throws InvalidReservationException {

        this.reservation.setEndTime(LocalTime.of(17, 15));
        reservationService.createNewReservation(this.reservation);

    }

    @Test(expected = InvalidReservationException.class)
    public void shouldThrowExceptionDueTooEarly() throws InvalidReservationException {

        this.reservation.setStartTime(LocalTime.of(7, 15));
        reservationService.createNewReservation(this.reservation);

    }

    @Test(expected = InvalidReservationException.class)
    public void shouldThrowExceptionDueToStartTimeMinutesInterval() throws InvalidReservationException {

        this.reservation.setStartTime(LocalTime.of(11, 20));
        reservationService.createNewReservation(this.reservation);

    }

    @Test(expected = InvalidReservationException.class)
    public void shouldThrowExceptionDueToEndTimeMinutesInterval() throws InvalidReservationException {
        this.reservation.setEndTime(LocalTime.of(13, 20));
        reservationService.createNewReservation(this.reservation);

    }

    @Test(expected = InvalidReservationException.class)
    public void shouldThrowExceptionDueToWeekend() throws InvalidReservationException {

        LocalDate date = LocalDate.now();
        this.reservation.setReservationDate(date.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)));
        reservationService.createNewReservation(this.reservation);
    }

    @Test(expected = InvalidReservationException.class)
    public void shouldThrowExceptionDueToPastReservation() throws InvalidReservationException {

        this.reservation.setReservationDate(LocalDate.now().minusDays(1));
        reservationService.createNewReservation(this.reservation);
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionDueToEmptyValues() throws InvalidReservationException {

        Reservation reservation = new Reservation();
        reservationService.createNewReservation(reservation);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionDueToEmptyConferenceTitle() throws InvalidReservationException {

        this.reservation.setConferenceTitle("");
        reservationService.createNewReservation(this.reservation);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionDueToEmptyDate() throws InvalidReservationException {

        this.reservation.setReservationDate(null);
        reservationService.createNewReservation(this.reservation);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionDueToEmptyStartTime() throws InvalidReservationException {

        this.reservation.setStartTime(null);
        reservationService.createNewReservation(this.reservation);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionDueToEmptyEndTime() throws InvalidReservationException {

        this.reservation.setEndTime(null);
        reservationService.createNewReservation(this.reservation);

    }

    @Test
    public void shouldPersist() {

        when(reservationRepository.save(any(Reservation.class))).thenReturn(this.reservation);

        try {
            Reservation newReservation = reservationService.createNewReservation(this.reservation);
            assertThat(newReservation).isNotNull();
        } catch (InvalidReservationException e) {
            fail(e.getMessage());
        }

    }

}
