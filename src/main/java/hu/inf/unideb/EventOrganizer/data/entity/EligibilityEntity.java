package hu.inf.unideb.EventOrganizer.data.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "eligibility")
public class EligibilityEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;

    public EligibilityEntity() {
    }

    public EligibilityEntity(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EligibilityEntity that = (EligibilityEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
