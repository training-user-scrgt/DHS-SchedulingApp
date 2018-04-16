package gov.dhs.uscis.odos2.reservation.controller;

import gov.dhs.uscis.odos2.reservation.exception.InvalidReservationException;
import gov.dhs.uscis.odos2.reservation.model.Reservation;
import gov.dhs.uscis.odos2.reservation.service.ReservationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReservationService reservationService;

    private final String GOOD_RESERVATION_BODY = "{\n" +
            "\t\"reservationDate\": \"2018-04-20\",\n" +
            "\t\"startTime\": \"12:00\",\n" +
            "\t\"endTime\": \"13:00\",\n" +
            "\t\"conferenceTitle\": \"Code Coverage Bash\",\n" +
            "\t\"roomId\": \"1\"\n" +
            "}";

    private final String BAD_RESERVATION_BODY = "{\n" +
            "\t\"reservationDate\": \"2018-04-20\",\n" +
            "\t\"startTime\": \"12:00\",\n" +
            "\t\"endTime\": \"12:20\",\n" +
            "\t\"conferenceTitle\": \"Code Coverage Bash\",\n" +
            "\t\"roomId\": \"1\"\n" +
            "}";


    @Test
    public void shouldCreateReservation() throws Exception {

        Reservation reservation = new Reservation();
        reservation.setId(1);

        when(reservationService.createNewReservation(any(Reservation.class))).thenReturn(reservation);

        mvc.perform(post("/reservation")
                .content(GOOD_RESERVATION_BODY)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    public void shouldFailCreateReservation() throws Exception {

        Reservation reservation = new Reservation();
        reservation.setId(1);

        when(reservationService.createNewReservation(any(Reservation.class))).thenThrow(InvalidReservationException.class);

        mvc.perform(post("/reservation")
                .content(BAD_RESERVATION_BODY)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void shouldGetReservationsByDate() throws Exception {

        Reservation reservation = new Reservation();
        reservation.setId(1);
        reservation.setReservationDate(LocalDate.of(2018, 4, 20));

        List<Reservation> reservationList = new ArrayList<Reservation>(1);
        reservationList.add(reservation);

        when(reservationService.getReservationsByDate(any(LocalDate.class))).thenReturn(reservationList);

        mvc.perform(get("/reservation/2018-04-20")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


}
