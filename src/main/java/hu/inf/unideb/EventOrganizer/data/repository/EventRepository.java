package hu.inf.unideb.EventOrganizer.data.repository;

import hu.inf.unideb.EventOrganizer.data.entity.EventEntity;
import hu.inf.unideb.EventOrganizer.service.dto.EventDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {
    Optional<EventEntity> findByNameAndLocation(String name, String location);
    List<EventEntity> findByNameContainingIgnoreCase(String name);
    List<EventEntity> findByLocationContainingIgnoreCase(String location);
    List<EventEntity> findByDate(Date date);
    List<EventEntity> findByCreatorEmail(String creatorEmail);
}
