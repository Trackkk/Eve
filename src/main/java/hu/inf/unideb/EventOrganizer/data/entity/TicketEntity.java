package hu.inf.unideb.EventOrganizer.data.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ticket")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "category", nullable = false)
    private String category;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private EventEntity event;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "participant_id", referencedColumnName = "id")
    private ParticipantEntity participant;

    public TicketEntity() {
    }

    public TicketEntity(long id, double price, String category) {
        this.id = id;
        this.price = price;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    public ParticipantEntity getParticipant() {
        return participant;
    }

    public void setParticipant(ParticipantEntity participant) {
        this.participant = participant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketEntity that = (TicketEntity) o;
        return id == that.id &&
                Double.compare(that.price, price) == 0 &&
                Objects.equals(category, that.category) &&
                Objects.equals(event, that.event) &&
                Objects.equals(participant, that.participant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, category, event, participant);
    }
}
