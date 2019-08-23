package blazesoft.hotelreservation.repo;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import blazesoft.hotelreservation.model.Reservation;
import blazesoft.hotelreservation.model.Room;
import blazesoft.hotelreservation.model.RoomType;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT r FROM Room AS r WHERE r.type IN (:roomTypes)"
            + " AND r NOT IN ("
            + "SELECT rv.room FROM Reservation AS rv WHERE rv.room = r"
            + " AND (:excludedReservation IS NULL OR rv != :excludedReservation)"
            + " AND (rv.startDate <= :end AND rv.endDate >= :start)"
            + ") ORDER BY r.type ASC")
    List<Room> findAvailableRooms(LocalDate start, LocalDate end, 
                                    Collection<RoomType> roomTypes,
                                    Reservation excludedReservation);
    
}