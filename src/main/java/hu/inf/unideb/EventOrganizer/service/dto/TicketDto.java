package hu.inf.unideb.EventOrganizer.service.dto;

import lombok.Data;
import java.util.Objects;

@Data
public class TicketDto {

    private Long id;
    private double price;
    private String category;
    private Long eventId;
    private Long participantId;

    public TicketDto() {}

    public TicketDto(Long id, double price, String category, Long eventId, Long participantId) {
        this.id = id;
        this.price = price;
        this.category = category;
        this.eventId = eventId;
        this.participantId = participantId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketDto ticketDto = (TicketDto) o;
        return Double.compare(price, ticketDto.price) == 0 && Objects.equals(id, ticketDto.id) && Objects.equals(category, ticketDto.category) && Objects.equals(eventId, ticketDto.eventId) && Objects.equals(participantId, ticketDto.participantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, category, eventId, participantId);
    }
}
