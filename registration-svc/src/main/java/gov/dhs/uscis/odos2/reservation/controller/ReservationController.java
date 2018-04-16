package gov.dhs.uscis.odos2.reservation.controller;


import gov.dhs.uscis.odos2.reservation.exception.InvalidReservationException;
import gov.dhs.uscis.odos2.reservation.model.Reservation;
import gov.dhs.uscis.odos2.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("reservation")
public class ReservationController {


    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Void> addArticle(@RequestBody Reservation reservation, UriComponentsBuilder builder) {

        Reservation newReservation = null;
        HttpHeaders headers = new HttpHeaders();

        try {
            newReservation = reservationService.createNewReservation(reservation);
            headers.setLocation(builder.path("/reservation/{id}").buildAndExpand(newReservation.getId()).toUri());

        } catch (InvalidReservationException e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(headers, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

}
