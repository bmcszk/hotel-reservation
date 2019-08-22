package blazesoft.hotelreservation.model.dtos;

import java.time.LocalDate;

import blazesoft.hotelreservation.model.RoomType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReservationDto {
    
    private Long id;

    private String userName;

    private String roomNumber;

    private RoomType roomType;

    private int numberOfPeople;

    private LocalDate startDate;

    private LocalDate endDate;
}