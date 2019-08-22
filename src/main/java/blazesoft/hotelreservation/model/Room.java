package blazesoft.hotelreservation.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Room {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private RoomType type;

    @Column(unique = true)
    private String number;

    @OneToMany
    @JoinColumn(name = "room_id")
    private Set<Reservation> reservations;


    
}