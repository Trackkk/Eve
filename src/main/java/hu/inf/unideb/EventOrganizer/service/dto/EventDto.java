package hu.inf.unideb.EventOrganizer.service.dto;

import lombok.Data;
import java.util.Date;
import java.util.Objects;

@Data
public class EventDto {

    private Long id;
    private String name;
    private String location;
    private Date date;
    private String creatorEmail;

    public EventDto() {
    }

    public EventDto(Long id, String name, String location, Date date) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCreatorEmail() {
        return creatorEmail;
    }

    public void setCreatorEmail(String creatorEmail) {
        this.creatorEmail = creatorEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventDto eventDto = (EventDto) o;
        return Objects.equals(id, eventDto.id) && Objects.equals(name, eventDto.name) && Objects.equals(location, eventDto.location) && Objects.equals(date, eventDto.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, date);
    }

}
