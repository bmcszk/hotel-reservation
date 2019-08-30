package blazesoft.hotelreservation.model.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * RatingDto
 */
@Getter
@Setter
@Builder
public class RatingDto {
    private Long id;

    private String roomNumber;
    
    private int rating;

    
}