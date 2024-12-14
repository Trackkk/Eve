package hu.inf.unideb.EventOrganizer.data.repository;

import hu.inf.unideb.EventOrganizer.data.entity.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {

    ParticipantEntity findByEmail(String email);

    List<ParticipantEntity> findByNameContainingIgnoreCase(String name);

    List<ParticipantEntity> findByEmailContainingIgnoreCase(String email);
}
