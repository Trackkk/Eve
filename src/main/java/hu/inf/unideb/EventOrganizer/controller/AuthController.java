package hu.inf.unideb.EventOrganizer.controller;

import hu.inf.unideb.EventOrganizer.data.entity.ParticipantEntity;
import hu.inf.unideb.EventOrganizer.data.repository.ParticipantRepository;
import hu.inf.unideb.EventOrganizer.service.AuthenticationService;
import hu.inf.unideb.EventOrganizer.service.dto.LoginDto;
import hu.inf.unideb.EventOrganizer.service.dto.RegistrationDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationService service;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> handleOptions(){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/registration")
    public String regisztracio(@RequestBody RegistrationDto dto){
        return service.registration(dto);
    }

    @PostMapping("/login")
    public String bejelentkezes(@RequestBody LoginDto dto){
        return service.login(dto);
    }

    @GetMapping("/user-email")
    public String getUserEmail(Authentication authentication) {
        return authentication.getName();
    }

    @GetMapping("/participant-id")
    public Long getParticipantId(@RequestParam String email) {
        ParticipantEntity participant = participantRepository.findByEmail(email);
        if (participant != null) {
            return participant.getId();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Participant not found");
        }
    }
}
