package blazesoft.hotelreservation.model.dtos;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReservationDto {
    
    private Long id;

    private String username;

    private String roomNumber;

    private int numberOfPeople;

    private LocalDate startDate;

    private LocalDate endDate;
}