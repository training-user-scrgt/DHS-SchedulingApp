package gov.dhs.uscis.odos2.reservation.service;


import gov.dhs.uscis.odos2.reservation.exception.InvalidReservationException;
import gov.dhs.uscis.odos2.reservation.repository.ReservationRepository;
import gov.dhs.uscis.odos2.reservation.util.ReservationConflictHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationQueryTest {

    @Autowired
    private ReservationService reservationService;

    @MockBean
    private ReservationRepository reservationRepository;

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

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionDueToNullDate() throws InvalidReservationException {
        reservationService.getReservationsByDate(null);
    }

    @Test
    public void shouldRunQueryByDate() {

        LocalDate date = LocalDate.now();

        reservationService.getReservationsByDate(date);
        verify(reservationRepository).getAllByReservationDateOrderByStartTime(date);

    }

}
