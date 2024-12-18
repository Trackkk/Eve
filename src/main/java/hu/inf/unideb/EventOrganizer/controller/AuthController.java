package hu.inf.unideb.EventOrganizer.controller;

import hu.inf.unideb.EventOrganizer.service.AuthenticationService;
import hu.inf.unideb.EventOrganizer.service.dto.LoginDto;
import hu.inf.unideb.EventOrganizer.service.dto.RegistrationDto;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationService service;

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
}
