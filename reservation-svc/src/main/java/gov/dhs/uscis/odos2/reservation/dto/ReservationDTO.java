package gov.dhs.uscis.odos2.reservation.dto;

import gov.dhs.uscis.odos2.reservation.model.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationDTO {

    private Integer id;
    private LocalDate reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer roomId;
    private String conferenceTitle;

    public ReservationDTO(Integer id, LocalDate reservationDate, LocalTime startTime, LocalTime endTime, Integer roomId, String conferenceTitle) {
        this.id = id;
        this.reservationDate = reservationDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomId = roomId;
        this.conferenceTitle = conferenceTitle;
    }

    public ReservationDTO(Reservation reservation) {
        this(reservation.getId(),
                reservation.getReservationDate(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getRoomId(),
                reservation.getConferenceTitle());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getConferenceTitle() {
        return conferenceTitle;
    }

    public void setConferenceTitle(String conferenceTitle) {
        this.conferenceTitle = conferenceTitle;
    }
}
