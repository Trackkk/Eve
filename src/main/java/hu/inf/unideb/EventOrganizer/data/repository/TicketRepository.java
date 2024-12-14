package hu.inf.unideb.EventOrganizer.data.repository;

import hu.inf.unideb.EventOrganizer.data.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    List<TicketEntity> findByParticipantId(Long participantId);

    List<TicketEntity> findByEventId(Long eventId);


    long countByEventId(Long eventId);

    long countByParticipantId(Long participantId);
}
