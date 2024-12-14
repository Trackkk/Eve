package hu.inf.unideb.EventOrganizer.service.impl;

import hu.inf.unideb.EventOrganizer.data.entity.ParticipantEntity;
import hu.inf.unideb.EventOrganizer.data.repository.ParticipantRepository;
import hu.inf.unideb.EventOrganizer.service.ParticipantService;
import hu.inf.unideb.EventOrganizer.service.dto.ParticipantDto;
import hu.inf.unideb.EventOrganizer.service.mapper.ParticipantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private ParticipantRepository repo;

    @Autowired
    private ParticipantMapper mapper;

    @Override
    public ParticipantDto saveParticipant(ParticipantDto participantDto) {
        ParticipantEntity participantEntity = mapper.participantDtoToParticipantEntity(participantDto);
        ParticipantEntity savedEntity = repo.save(participantEntity);
        return mapper.participantEntityToParticipantDto(savedEntity);
    }

    @Override
    public ParticipantDto getParticipantById(Long id) {
        Optional<ParticipantEntity> participantEntity = repo.findById(id);
        if (participantEntity.isPresent()) {
            return mapper.participantEntityToParticipantDto(participantEntity.get());
        } else {
            throw new RuntimeException("Participant not found with id: " + id);
        }
    }

    @Override
    public List<ParticipantDto> getAllParticipants() {
        List<ParticipantEntity> participantEntities = repo.findAll();
        return mapper.participantEntityToParticipantDtoList(participantEntities);
    }

    @Override
    public void deleteParticipant(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<ParticipantDto> searchParticipants(String name, String email, Long eventId) {
        List<ParticipantEntity> participantEntities;

        if (name != null && !name.isEmpty()) {
            participantEntities = repo.findByNameContainingIgnoreCase(name);
        } else if (email != null && !email.isEmpty()) {
            participantEntities = repo.findByEmailContainingIgnoreCase(email);
        } else {
            participantEntities = repo.findAll();
        }

        return mapper.participantEntityToParticipantDtoList(participantEntities);
    }
}
