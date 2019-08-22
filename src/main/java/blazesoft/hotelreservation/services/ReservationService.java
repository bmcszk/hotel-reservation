package blazesoft.hotelreservation.services;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import blazesoft.hotelreservation.converters.ReservationConverter;
import blazesoft.hotelreservation.exceptions.NoRoomAvailableException;
import blazesoft.hotelreservation.exceptions.NumberOfPeopleExceeded;
import blazesoft.hotelreservation.model.Reservation;
import blazesoft.hotelreservation.model.Room;
import blazesoft.hotelreservation.model.RoomType;
import blazesoft.hotelreservation.model.User;
import blazesoft.hotelreservation.model.dtos.ReservationDto;
import blazesoft.hotelreservation.model.dtos.ReservationRequest;
import blazesoft.hotelreservation.repo.ReservationRepository;
import blazesoft.hotelreservation.repo.RoomRepository;
import blazesoft.hotelreservation.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final RoomRepository roomRepository;

    private final ReservationConverter reservationConverter;

    private final UserRepository userRepository;

    public ReservationDto addResevation(ReservationRequest reservationRequest) {
        Reservation entity = reservationConverter.convertToEntity(reservationRequest);
        entity = calculateReservation(reservationRequest, entity);
        entity = reservationRepository.save(entity);
        return reservationConverter.convertToDto(entity);
    }

	public Optional<ReservationDto> getReservation(Long id) {
        return reservationRepository.findById(id)
            .map(reservationConverter::convertToDto);
	}

	public List<ReservationDto> getAllReservations() {
        return reservationRepository.findAll()
            .stream()
            .map(reservationConverter::convertToDto)
            .collect(Collectors.toList());
    }
    
    public List<ReservationDto> getReservationsByRoom(String roomNumber) {
        return reservationRepository.findByRoomNumber(roomNumber)
            .stream()
            .map(reservationConverter::convertToDto)
            .collect(Collectors.toList());
	}

	public Optional<ReservationDto> updateReservation(Long id, ReservationRequest reservationRequest) {
        return reservationRepository.findById(id)
            .map(e -> reservationConverter.convertToEntity(reservationRequest, e))
            .map(e -> calculateReservation(reservationRequest, e))
            .map(reservationRepository::save)
            .map(reservationConverter::convertToDto);
	}

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
    
    private Reservation calculateReservation(ReservationRequest reservationRequest, Reservation entity) {
        List<RoomType> roomTypes = getRoomTypes(reservationRequest.getNumberOfPeople());
        Room availableRoom = getFirstAvailableRoom(reservationRequest.getStartDate(), 
                            reservationRequest.getEndDate(), roomTypes, 
                            entity.getId() != null ? entity : null);
        User user = getUser(reservationRequest.getUserName());

        entity.setRoom(availableRoom);
        entity.setUser(user);
        return entity;
    }

    private List<RoomType> getRoomTypes(int numberOfPeople) {
        List<RoomType> roomTypes = RoomType.getTypesForPeople(numberOfPeople);
        if (roomTypes.isEmpty()) {
            log.error("Number of people exceeded {}", numberOfPeople);
            throw new NumberOfPeopleExceeded();
        }

        return roomTypes;
    }

    private Room getFirstAvailableRoom(LocalDate startDate, LocalDate endDate, 
                Collection<RoomType> roomTypes, Reservation excludedReservation) {
        List<Room> availableRooms = roomRepository
                .findAvailableRooms(startDate, endDate, roomTypes, excludedReservation);
        if (availableRooms.isEmpty()) {
            log.error("No room available");
            throw new NoRoomAvailableException();
        }

        return availableRooms.get(0); //TODO change query to return first element
    }

    private User getUser(String username) {
        User user = userRepository.findByName(username);
        if (user == null) {
            user = new User();
            user.setName(username);
            user = userRepository.save(user);
        }

        return user;
    }

    
}