package hu.inf.unideb.EventOrganizer.service.impl;

import hu.inf.unideb.EventOrganizer.data.repository.ParticipantRepository;
import hu.inf.unideb.EventOrganizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    ParticipantRepository repo;

    @Override
    public UserDetailsService userDetailsService() {

        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return repo.findByEmail(username);
            }
        };
    }
}