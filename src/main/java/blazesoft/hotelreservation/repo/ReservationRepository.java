package blazesoft.hotelreservation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import blazesoft.hotelreservation.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    
}