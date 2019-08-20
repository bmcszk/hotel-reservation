package blazesoft.hotelreservation.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import blazesoft.hotelreservation.model.Reservation;
import blazesoft.hotelreservation.model.dtos.ReservationDto;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReservationConverter {
    private final ModelMapper modelMapper;

    public ReservationDto convertToDto(Reservation entity) {
        return modelMapper.map(entity, ReservationDto.class);
    }

	public Reservation convertToEntity(ReservationDto dto) {
		return modelMapper.map(dto, Reservation.class);
    }
    
    public Reservation convertToEntity(ReservationDto dto, Reservation entity) {
        modelMapper.map(dto, entity);
        return entity;
	}
}