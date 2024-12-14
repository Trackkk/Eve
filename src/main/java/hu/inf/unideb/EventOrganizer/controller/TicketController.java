package hu.inf.unideb.EventOrganizer.controller;

import hu.inf.unideb.EventOrganizer.service.TicketService;
import hu.inf.unideb.EventOrganizer.service.dto.TicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public TicketDto createTicket(@RequestBody TicketDto ticketDto) {
        return ticketService.saveTicket(ticketDto);
    }

    @PutMapping("/{id}")
    public TicketDto updateTicket(@PathVariable Long id, @RequestBody TicketDto ticketDto) {
        ticketDto.setId(id);
        return ticketService.saveTicket(ticketDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
    }

    @GetMapping
    public List<TicketDto> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public TicketDto getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id);
    }

    @GetMapping("/search")
    public List<TicketDto> searchTickets(@RequestParam(required = false) Long eventId,
                                         @RequestParam(required = false) Long participantId,
                                         @RequestParam(required = false) String ticketType) {
        return ticketService.searchTickets(eventId, participantId, ticketType);
    }

    @GetMapping("/countByEvent")
    public long countTicketsByEventId(@RequestParam Long eventId) {
        return ticketService.countTicketsByEventId(eventId);
    }

    @GetMapping("/countByParticipant")
    public long countTicketsByParticipantId(@RequestParam Long participantId) {
        return ticketService.countTicketsByParticipantId(participantId);
    }
}
