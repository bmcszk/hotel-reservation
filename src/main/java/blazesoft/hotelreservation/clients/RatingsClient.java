package blazesoft.hotelreservation.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import blazesoft.hotelreservation.model.dtos.RatingDto;

@FeignClient("rating-service")
public interface RatingsClient {
    @GetMapping("/ratings")
    List<RatingDto> getAllRatings();
    
    @GetMapping("/ratings/{roomNumber}")
    RatingDto getRating(@PathVariable String roomNumber);
}