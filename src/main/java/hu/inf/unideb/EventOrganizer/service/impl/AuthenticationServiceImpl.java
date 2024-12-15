package hu.inf.unideb.EventOrganizer.service.impl;

import hu.inf.unideb.EventOrganizer.data.entity.EligibilityEntity;
import hu.inf.unideb.EventOrganizer.data.entity.ParticipantEntity;
import hu.inf.unideb.EventOrganizer.data.repository.EligibilityRepository;
import hu.inf.unideb.EventOrganizer.data.repository.ParticipantRepository;
import hu.inf.unideb.EventOrganizer.service.AuthenticationService;
import hu.inf.unideb.EventOrganizer.service.JwtService;
import hu.inf.unideb.EventOrganizer.service.dto.LoginDto;
import hu.inf.unideb.EventOrganizer.service.dto.RegistrationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    ParticipantRepository participantrepo;
    @Autowired
    EligibilityRepository eligibilityRepo;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    JwtService jwtService;


    @Override
    public String registration(RegistrationDto dto) {
        ParticipantEntity participantEntity = modelMapper.map(dto, ParticipantEntity.class);
        participantEntity.setPassword(encoder.encode(participantEntity.getPassword()));
        EligibilityEntity eligibility = eligibilityRepo.findByName("USER");
        if (eligibility != null) {
            participantEntity.setEligibility(Set.of(eligibility));
        } else {
            eligibility = new EligibilityEntity(null, "USER");
            eligibility = eligibilityRepo.save(eligibility);
            participantEntity.setEligibility(Set.of(eligibility));
        }

        participantEntity = participantrepo.save(participantEntity);

        return jwtService.generateToken(participantEntity);
    }

    @Override
    public String login(LoginDto dto) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getPassword())
        );
        var user = participantrepo.findByEmail(dto.getEmail());
        return jwtService.generateToken(user);
    }
}
