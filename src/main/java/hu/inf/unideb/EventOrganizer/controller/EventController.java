package hu.inf.unideb.EventOrganizer.controller;

import hu.inf.unideb.EventOrganizer.service.AuthenticationService;
import hu.inf.unideb.EventOrganizer.service.EventService;
import hu.inf.unideb.EventOrganizer.service.dto.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public EventDto createEvent(@RequestBody EventDto eventDto, Authentication authentication) {
        String email = authentication.getName();
        eventDto.setCreatorEmail(email);
        return eventService.saveEvent(eventDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDto> updateEvent(@PathVariable Long id, @RequestBody EventDto eventDto, Authentication authentication) {
        EventDto existingEvent = eventService.getEventById(id);

        if (!existingEvent.getCreatorEmail().equals(authentication.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        eventDto.setId(id);
        return ResponseEntity.ok(eventService.updateEvent(eventDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id, Authentication authentication) {
        EventDto existingEvent = eventService.getEventById(id);

        String creatorEmail = existingEvent.getCreatorEmail();
        String currentUserEmail = authentication.getName();

        if (!creatorEmail.equals(currentUserEmail)) {
            System.out.println("Forbidden: " + currentUserEmail + " is not the creator of this event.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        eventService.deleteEvent(id);
        System.out.println("Event deleted, ID: " + id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<EventDto> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public EventDto getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @GetMapping("/search")
    public List<EventDto> searchEvents(@RequestParam(required = false) String name,
                                       @RequestParam(required = false) String location,
                                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return eventService.searchEvents(name, location, date);
    }
}
