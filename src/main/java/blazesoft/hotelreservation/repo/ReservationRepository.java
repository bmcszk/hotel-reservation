package blazesoft.hotelreservation.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import blazesoft.hotelreservation.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByRoomNumber(String roomNumber);

    
}