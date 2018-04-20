package gov.dhs.uscis.odos2.reservation.controller;


import gov.dhs.uscis.odos2.reservation.dto.ReservationDTO;
import gov.dhs.uscis.odos2.reservation.exception.InvalidReservationException;
import gov.dhs.uscis.odos2.reservation.model.ErrorResponse;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("reservation")
@Api(value = "reservation", description = "Online Conference Room Reservation")
public class ReservationController {

    private final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private ReservationService reservationService;

    @PostMapping
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
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
                    headers, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new ReservationDTO(newReservation), headers, HttpStatus.CREATED);
    }


    @GetMapping
    @ResponseBody
    @RequestMapping(value = "/{date}")
    @ApiOperation(value = "Return all the Reservations for the given date", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned")
    })
    public ResponseEntity<List<ReservationDTO>> getReservationsByDate(
            @NotNull @PathVariable String date) {

        LocalDate reservationDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);

        List<Reservation> reservationList = reservationService.getReservationsByDate(reservationDate);
        return new ResponseEntity<>(
                reservationList
                        .stream()
                        .map(ReservationDTO::new)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    @RequestMapping(value = "/search")
    @ApiOperation(value = "Return all the Reservations for the given date, start and end time", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created reservation")
    })
    public ResponseEntity<List<ReservationDTO>> getReservationsByDateAndTime(
            @NotNull @RequestParam String date,
            @NotNull @RequestParam String from,
            @NotNull @RequestParam String to) {

        LocalDate reservationDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        LocalTime fromTime = LocalTime.parse(from);
        LocalTime toTime = LocalTime.parse(to);

        List<Reservation> reservationList = reservationService.
                getReservationsByDateAndTime(reservationDate, fromTime, toTime);

        return new ResponseEntity<>(
                reservationList
                        .stream()
                        .map(ReservationDTO::new)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

}
