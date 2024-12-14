package hu.inf.unideb.EventOrganizer.service.mapper;

import hu.inf.unideb.EventOrganizer.data.entity.TicketEntity;
import hu.inf.unideb.EventOrganizer.service.dto.TicketDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(target = "event", ignore = true)
    @Mapping(target = "participant", ignore = true)
    TicketEntity ticketDtoToTicketEntity(TicketDto ticketDto);

    @Mapping(target = "eventId", source = "event.id")
    @Mapping(target = "participantId", source = "participant.id")
    TicketDto ticketEntityToTicketDto(TicketEntity ticketEntity);

    List<TicketDto> ticketEntityToTicketDtoList(List<TicketEntity> entities);

    List<TicketEntity> ticketDtoListToEntityList(List<TicketDto> dtos);
}
