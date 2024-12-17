package hu.inf.unideb.EventOrganizer.data.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "participant")
public class ParticipantEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @ManyToMany(mappedBy = "participants")
    private Set<EventEntity> eventId;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<EligibilityEntity> eligibility;

    public ParticipantEntity() {
    }

    public ParticipantEntity(Long id, String name, String email, String password, String phoneNumber, Set<EventEntity> eventId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.eventId = eventId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<EventEntity> getEventId() {
        return eventId;
    }

    public void setEventId(Set<EventEntity> eventId) {
        this.eventId = eventId;
    }

    public Set<EligibilityEntity> getEligibility() {
        return eligibility;
    }

    public void setEligibility(Set<EligibilityEntity> eligibility) {
        this.eligibility = eligibility;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(EligibilityEntity j : eligibility){
            authorities.add(new SimpleGrantedAuthority(j.getName()));
        }
        return authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipantEntity that = (ParticipantEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(eventId, that.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, phoneNumber, eventId);
    }
}
