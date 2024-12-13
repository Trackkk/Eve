package hu.inf.unideb.EventOrganizer.service;

import hu.inf.unideb.EventOrganizer.service.dto.EventDto;

import java.util.List;

public interface EventService {

    EventDto saveEvent(EventDto eventDto);

    EventDto getEventById(Long id);

    List<EventDto> getAllEvents();

    void deleteEvent(Long id);
}
