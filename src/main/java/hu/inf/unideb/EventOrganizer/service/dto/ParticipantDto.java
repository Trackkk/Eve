package hu.inf.unideb.EventOrganizer.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDto {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
}
