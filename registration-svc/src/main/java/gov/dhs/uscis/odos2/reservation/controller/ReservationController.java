package gov.dhs.uscis.odos2.reservation.controller;


import gov.dhs.uscis.odos2.reservation.exception.InvalidReservationException;
import gov.dhs.uscis.odos2.reservation.model.Reservation;
import gov.dhs.uscis.odos2.reservation.service.ReservationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("reservation")
@Api(value = "reservation", description = "Online Conference Room Reservation")
public class ReservationController {

    private final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private ReservationService reservationService;

    @PostMapping
//    @PreAuthorize("hasAnyAuthority('ROLE_REQUESTOR','ROLE_ADMIN')")
    @ApiOperation(value = "Creates a new Reservation", response = Reservation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created reservation"),
            @ApiResponse(code = 400, message = "You are not authorized to create a reservation")
    })
    public ResponseEntity addReservation(@RequestBody Reservation reservation, UriComponentsBuilder builder) {

        HttpHeaders headers = new HttpHeaders();
        Reservation newReservation;

        try {
            newReservation = reservationService.createNewReservation(reservation);
            headers.setLocation(builder.path("/reservation/{id}").buildAndExpand(newReservation.getId()).toUri());

        } catch (InvalidReservationException e) {
            LOGGER.error(e.getMessage(), e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(newReservation, headers, HttpStatus.CREATED);
    }


    @GetMapping
    @ResponseBody
    @RequestMapping(value = "/{dateString}")
//    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER','ROLE_ADMIN','ROLE_REQUESTOR')")
    @ApiOperation(value = "Return all the Reservations for the given date", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created reservation")
    })
    public ResponseEntity<List<Reservation>> getReservationsByDate(@PathVariable String dateString) {

        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);

        return new ResponseEntity<>(
                reservationService.getReservationsByDate(date), HttpStatus.OK);
    }

}
