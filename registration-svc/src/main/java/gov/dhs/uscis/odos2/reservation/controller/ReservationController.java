package gov.dhs.uscis.odos2.reservation.controller;


import gov.dhs.uscis.odos2.reservation.exception.InvalidReservationException;
import gov.dhs.uscis.odos2.reservation.model.Reservation;
import gov.dhs.uscis.odos2.reservation.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("reservation")
public class ReservationController {

    private final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Void> addReservation(@RequestBody Reservation reservation, UriComponentsBuilder builder) {

        HttpHeaders headers = new HttpHeaders();

        try {
            Reservation newReservation = reservationService.createNewReservation(reservation);
            headers.setLocation(builder.path("/reservation/{id}").buildAndExpand(newReservation.getId()).toUri());

        } catch (InvalidReservationException e) {
            LOGGER.error(e.getMessage(), e);
            return new ResponseEntity<Void>(headers, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    @GetMapping
    @ResponseBody
    @RequestMapping(value = "/{dateString}")
    public ResponseEntity<List<Reservation>> getReservationsByDate(@PathVariable String dateString) {

        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);

        return new ResponseEntity<List<Reservation>>(
                reservationService.getReservationsByDate(date), HttpStatus.OK);
    }

}
