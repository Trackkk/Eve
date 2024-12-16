package hu.inf.unideb.EventOrganizer.controller;

import hu.inf.unideb.EventOrganizer.service.EventService;
import hu.inf.unideb.EventOrganizer.service.dto.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> handleOptions(){
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public EventDto createEvent(@RequestBody EventDto eventDto) {
        return eventService.saveEvent(eventDto);
    }

    @PutMapping("/{id}")
    public EventDto updateEvent(@PathVariable Long id, @RequestBody EventDto eventDto) {
        eventDto.setId(id);
        return eventService.updateEvent(eventDto);
    }


    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
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
