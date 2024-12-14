package hu.inf.unideb.EventOrganizer.service.impl;

import hu.inf.unideb.EventOrganizer.data.entity.TicketEntity;
import hu.inf.unideb.EventOrganizer.data.repository.TicketRepository;
import hu.inf.unideb.EventOrganizer.service.TicketService;
import hu.inf.unideb.EventOrganizer.service.dto.TicketDto;
import hu.inf.unideb.EventOrganizer.service.mapper.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository repo;

    @Autowired
    private TicketMapper mapper;

    @Override
    public TicketDto saveTicket(TicketDto ticketDto) {
        TicketEntity ticketEntity = mapper.ticketDtoToTicketEntity(ticketDto);
        TicketEntity savedTicket = repo.save(ticketEntity);

        return mapper.ticketEntityToTicketDto(savedTicket);
    }

    @Override
    public TicketDto getTicketById(Long id) {
        TicketEntity ticketEntity = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        return mapper.ticketEntityToTicketDto(ticketEntity);
    }

    @Override
    public List<TicketDto> getAllTickets() {
        List<TicketEntity> ticketEntities = repo.findAll();
        return mapper.ticketEntityToTicketDtoList(ticketEntities);
    }

    @Override
    public void deleteTicket(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<TicketDto> searchTickets(Long eventId, Long participantId, String ticketType) {
        List<TicketEntity> ticketEntities;

        if (eventId != null) {
            ticketEntities = repo.findByEventId(eventId);
        } else if (participantId != null) {
            ticketEntities = repo.findByParticipantId(participantId);
        } else {
            ticketEntities = repo.findAll();
        }

        return mapper.ticketEntityToTicketDtoList(ticketEntities);
    }

    @Override
    public long countTicketsByEventId(Long eventId) {
        return repo.countByEventId(eventId);
    }

    @Override
    public long countTicketsByParticipantId(Long participantId) {
        return repo.countByParticipantId(participantId);
    }
}
