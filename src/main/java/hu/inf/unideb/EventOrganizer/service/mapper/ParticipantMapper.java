package hu.inf.unideb.EventOrganizer.service.mapper;

import hu.inf.unideb.EventOrganizer.data.entity.ParticipantEntity;
import hu.inf.unideb.EventOrganizer.service.dto.ParticipantDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {

    ParticipantDto participantEntityToParticipantDto(ParticipantEntity entity);

    List<ParticipantDto> participantEntityToParticipantDtoList(List<ParticipantEntity> entities);

    ParticipantEntity participantDtoToParticipantEntity(ParticipantDto dto);

    List<ParticipantEntity> participantDtoListToEntityList(List<ParticipantDto> dtos);
}
