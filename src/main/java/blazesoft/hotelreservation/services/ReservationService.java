package blazesoft.hotelreservation.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import blazesoft.hotelreservation.converters.ReservationConverter;
import blazesoft.hotelreservation.model.dtos.ReservationDto;
import blazesoft.hotelreservation.repo.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final ReservationConverter reservationConverter;

    public ReservationDto addResevation(ReservationDto reservation) {
        log.debug("adding reservation");
        return reservationConverter.convertToDto(
            reservationRepository.save(
                reservationConverter.convertToEntity(reservation)
            )
        );
    }


    
}