package hu.inf.unideb.EventOrganizer.service;

import hu.inf.unideb.EventOrganizer.service.dto.LoginDto;
import hu.inf.unideb.EventOrganizer.service.dto.RegistrationDto;

public interface AuthenticationService {
    String registration(RegistrationDto dto);

    String login(LoginDto dto);


}
