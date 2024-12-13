package hu.inf.unideb.EventOrganizer.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "participant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String nev;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String jelszo;

    @Column(name = "phone_number")
    private String phonenumber;

    @ManyToMany(mappedBy = "participants")
    private Set<EventEntity> events;

    // KÉRDÉS MIÉRT NEM KELL OneToMany kapcsoalt hogyha az eventél kellett ? A ticketnél meg van adva mindkettő
}
