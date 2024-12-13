package hu.inf.unideb.EventOrganizer.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    private Long id;
    private double price;
    private String category;
    private Long eventId;
    private Long participantId;
}
