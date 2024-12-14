package hu.inf.unideb.EventOrganizer.controller;

import hu.inf.unideb.EventOrganizer.service.ParticipantService;
import hu.inf.unideb.EventOrganizer.service.dto.ParticipantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @PostMapping
    public ParticipantDto createParticipant(@RequestBody ParticipantDto participantDto) {
        return participantService.saveParticipant(participantDto);
    }

    @PutMapping("/{id}")
    public ParticipantDto updateParticipant(@PathVariable Long id, @RequestBody ParticipantDto participantDto) {
        participantDto.setId(id);
        ParticipantDto existingParticipant = participantService.getParticipantById(id);
        if (existingParticipant != null) {
            return participantService.saveParticipant(participantDto);
        } else {
            throw new RuntimeException("Participant not found with id: " + id);
        }
    }


    @DeleteMapping("/{id}")
    public void deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
    }

    @GetMapping
    public List<ParticipantDto> getAllParticipants() {
        return participantService.getAllParticipants();
    }

    @GetMapping("/{id}")
    public ParticipantDto getParticipantById(@PathVariable Long id) {
        return participantService.getParticipantById(id);
    }

    @GetMapping("/search")
    public List<ParticipantDto> searchParticipants(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Long eventId) {
        return participantService.searchParticipants(name, email, eventId);
    }
}
