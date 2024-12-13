package hu.inf.unideb.EventOrganizer.service.mapper;

import hu.inf.unideb.EventOrganizer.data.entity.TicketEntity;
import hu.inf.unideb.EventOrganizer.service.dto.TicketDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    TicketDto ticketEntityToTicketDto(TicketEntity entity);

    List<TicketDto> ticketEntityToTicketDtoList(List<TicketEntity> entities);

    TicketEntity ticketDtoToTicketEntity(TicketDto dto);

    List<TicketEntity> ticketDtoListToEntityList(List<TicketDto> dtos);
}
