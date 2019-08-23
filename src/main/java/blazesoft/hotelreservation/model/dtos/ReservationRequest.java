package blazesoft.hotelreservation.model.dtos;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReservationRequest {
    
    @NotBlank
    private String userName;

    @Min(1)
    @Max(8)
    private int numberOfPeople;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;
}