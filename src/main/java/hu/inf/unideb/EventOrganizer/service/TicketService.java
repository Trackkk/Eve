package hu.inf.unideb.EventOrganizer.service;

import hu.inf.unideb.EventOrganizer.service.dto.TicketDto;

import java.util.List;

public interface TicketService {

    TicketDto saveTicket(TicketDto ticketDto);

    TicketDto getTicketById(Long id);

    List<TicketDto> getAllTickets();

    void deleteTicket(Long id);

    List<TicketDto> searchTickets(Long eventId, Long participantId, String ticketType);

    long countTicketsByEventId(Long eventId);

    long countTicketsByParticipantId(Long participantId);
}
