package gov.dhs.uscis.odos2.reservation.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "reservation_date", nullable = false)
    private LocalDate reservationDate;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "room_id", nullable = false)
    private Integer roomId;

    @Column(name = "conference_title", nullable = false, length = 200)
    private String conferenceTitle;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "create_by", nullable = false)
    private UUID createBy;

    @Column(name = "update_date", nullable = true)
    private LocalDateTime lastUpdateDate;

    @Column(name = "update_by", nullable = false)
    private UUID lastUpdateBy;



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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public UUID getCreateBy() {
        return createBy;
    }

    public void setCreateBy(UUID createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public UUID getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(UUID lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservationDate=" + reservationDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", roomId=" + roomId +
                ", conferenceTitle='" + conferenceTitle + '\'' +
                ", createDate=" + createDate +
                ", createBy=" + createBy +
                ", lastUpdateDate=" + lastUpdateDate +
                ", lastUpdateBy=" + lastUpdateBy +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        return new EqualsBuilder()
                .append(reservationDate, that.reservationDate)
                .append(startTime, that.startTime)
                .append(endTime, that.endTime)
                .append(roomId, that.roomId)
                .append(conferenceTitle, that.conferenceTitle)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(reservationDate)
                .append(startTime)
                .append(endTime)
                .append(roomId)
                .append(conferenceTitle)
                .toHashCode();
    }
}
