package hu.inf.unideb.EventOrganizer.service;

import hu.inf.unideb.EventOrganizer.service.dto.ParticipantDto;
import java.util.List;

public interface ParticipantService {

    ParticipantDto saveParticipant(ParticipantDto participantDto);

    ParticipantDto getParticipantById(Long id);

    List<ParticipantDto> getAllParticipants();

    void deleteParticipant(Long id);

    List<ParticipantDto> searchParticipants(String name, String email, Long eventId);
}
