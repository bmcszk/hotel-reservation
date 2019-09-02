package blazesoft.hotelreservation.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import blazesoft.hotelreservation.clients.RatingsClient;
import blazesoft.hotelreservation.model.dtos.RatingDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("ratings")
public class RatingController {
    private final RatingsClient ratingsClient;

    @GetMapping
    public List<RatingDto> getAllRatings() {
        return this.ratingsClient.getAllRatings();
    }

    @GetMapping("{roomNumber}")
    public RatingDto getRating(@PathVariable String roomNumber) {
        return this.ratingsClient.getRating(roomNumber);
    }

}