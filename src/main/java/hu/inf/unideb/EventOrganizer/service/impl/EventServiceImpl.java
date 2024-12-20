package hu.inf.unideb.EventOrganizer.service.impl;

import hu.inf.unideb.EventOrganizer.data.entity.EventEntity;
import hu.inf.unideb.EventOrganizer.data.repository.EventRepository;
import hu.inf.unideb.EventOrganizer.service.EventService;
import hu.inf.unideb.EventOrganizer.service.dto.EventDto;
import hu.inf.unideb.EventOrganizer.service.mapper.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository repo;

    @Autowired
    EventMapper mapper;

    @Override
    public EventDto saveEvent(EventDto eventDto) {
        EventEntity eventEntity = mapper.eventDtoToEventEntity(eventDto);
        EventEntity savedEvent = repo.save(eventEntity);
        return mapper.eventEntityToEventDto(savedEvent);
    }

    @Override
    public EventDto getEventById(Long id) {
        EventEntity eventEntity = repo.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
        return mapper.eventEntityToEventDto(eventEntity);
    }

    @Override
    public List<EventDto> getAllEvents() {
        List<EventEntity> eventEntities = repo.findAll();
        return mapper.eventEntityToEventDtoList(eventEntities);
    }

    @Override
    public void deleteEvent(Long id) {
        repo.deleteById(id);
    }

    @Override
    public EventDto updateEvent(EventDto eventDto) {
        Optional<EventEntity> existingEvent = repo.findById(eventDto.getId());

        if (existingEvent.isPresent()) {
            EventEntity eventEntity = existingEvent.get();

            eventEntity.setName(eventDto.getName());
            eventEntity.setLocation(eventDto.getLocation());
            eventEntity.setDate(eventDto.getDate());
            EventEntity updatedEvent = repo.save(eventEntity);

            return mapper.eventEntityToEventDto(updatedEvent);
        } else {
            throw new RuntimeException("Event not found with id: " + eventDto.getId());
        }
    }


    @Override
    public List<EventDto> searchEvents(String name, String location, Date date) {
        List<EventEntity> eventEntities;

        if (name != null && !name.isEmpty()) {
            eventEntities = repo.findByNameContainingIgnoreCase(name);
        } else if (location != null && !location.isEmpty()) {
            eventEntities = repo.findByLocationContainingIgnoreCase(location);
        } else if (date != null) {
            eventEntities = repo.findByDate(date);
        } else {
            eventEntities = repo.findAll();
        }

        return mapper.eventEntityToEventDtoList(eventEntities);
    }


    @Override
    public void updateCreatorEmailForEvents(String oldEmail, String newEmail) {
        List<EventEntity> events = repo.findByCreatorEmail(oldEmail);
        for (EventEntity event : events) {
            event.setCreatorEmail(newEmail);
            repo.save(event);
        }
    }


}
