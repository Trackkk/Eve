package hu.inf.unideb.EventOrganizer.controller;

import hu.inf.unideb.EventOrganizer.service.EventService;
import hu.inf.unideb.EventOrganizer.service.ParticipantService;
import hu.inf.unideb.EventOrganizer.service.dto.ParticipantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    @Autowired
    EventService eventService;

    @Autowired
    private ParticipantService participantService;

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> handleOptions(){
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ParticipantDto createParticipant(@RequestBody ParticipantDto participantDto) {
        return participantService.saveParticipant(participantDto);
    }

    @PutMapping("/{id}")
    public ParticipantDto updateParticipant(@PathVariable Long id, @RequestBody ParticipantDto participantDto, Authentication authentication) {
        participantDto.setId(id);
        ParticipantDto existingParticipant = participantService.getParticipantById(id);

        if (existingParticipant == null) {
            throw new RuntimeException("Participant not found with id: " + id);
        }

        if (!Objects.equals(authentication.getName(), existingParticipant.getEmail())) {
            throw new RuntimeException("Unauthorized to modify this participant");
        }

        String oldEmail = existingParticipant.getEmail();

        if (participantDto.getName() == null) {
            participantDto.setName(existingParticipant.getName());
        }
        if (participantDto.getEmail() == null) {
            participantDto.setEmail(existingParticipant.getEmail());
        }
        if (participantDto.getPassword() == null) {
            participantDto.setPassword(existingParticipant.getPassword());
        }

        if (!Objects.equals(oldEmail, participantDto.getEmail())) {
            eventService.updateCreatorEmailForEvents(oldEmail, participantDto.getEmail());
        }

        return participantService.saveParticipant(participantDto);
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
