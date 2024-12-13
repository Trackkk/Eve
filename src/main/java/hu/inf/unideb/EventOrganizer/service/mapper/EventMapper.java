package hu.inf.unideb.EventOrganizer.service.mapper;

import hu.inf.unideb.EventOrganizer.data.entity.EventEntity;
import hu.inf.unideb.EventOrganizer.service.dto.EventDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDto eventEntityToEventDto(EventEntity entity);

    List<EventDto> eventEntityToEventDtoList(List<EventEntity> entities);

    EventEntity eventDtoToEventEntity(EventDto dto);

    List<EventEntity> eventDtoListToEntityList(List<EventDto> dtos);
}
