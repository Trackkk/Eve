package hu.inf.unideb.EventOrganizer.data.repository;

import hu.inf.unideb.EventOrganizer.data.entity.EligibilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EligibilityRepository extends JpaRepository<EligibilityEntity,Long> {
    EligibilityEntity findByName(String name);
}