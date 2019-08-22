package blazesoft.hotelreservation.model.dtos;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReservationRequest {
    
    private String userName;

    private int numberOfPeople;

    private LocalDate startDate;

    private LocalDate endDate;
}