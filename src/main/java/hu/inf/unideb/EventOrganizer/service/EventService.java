package hu.inf.unideb.EventOrganizer.service;

import hu.inf.unideb.EventOrganizer.service.dto.EventDto;

import java.util.Date;
import java.util.List;

public interface EventService {

    EventDto saveEvent(EventDto eventDto);

    EventDto getEventById(Long id);

    List<EventDto> getAllEvents();

    void deleteEvent(Long id);

    EventDto updateEvent(EventDto eventDto);

    List<EventDto> searchEvents(String name, String location, Date date);
}
