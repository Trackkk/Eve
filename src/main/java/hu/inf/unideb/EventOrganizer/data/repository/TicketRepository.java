package hu.inf.unideb.EventOrganizer.data.repository;

import hu.inf.unideb.EventOrganizer.data.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
    // Lekérdezés egy Ticket alapján, ami a participant id-jával kapcsolódik
    List<TicketEntity> findByParticipantId(Long participantId);

    // Lekérdezés esemény alapján
    List<TicketEntity> findByEventId(Long eventId);
}