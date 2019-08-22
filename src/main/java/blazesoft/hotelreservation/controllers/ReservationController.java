package blazesoft.hotelreservation.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import blazesoft.hotelreservation.model.dtos.ReservationDto;
import blazesoft.hotelreservation.model.dtos.ReservationRequest;
import blazesoft.hotelreservation.services.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("reservation")
@Slf4j
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping
    public List<ReservationDto> getAllReservations() {
        log.debug("getAllReservations()");
        return reservationService.getAllReservations();
    }

    @GetMapping("{id}")
    public Optional<ReservationDto> getReservation(
        @RequestParam Long id) {
        log.debug("getReservation()");
        return reservationService.getReservation(id);
    }

    @PostMapping
    public ReservationDto addReservation(@RequestBody ReservationRequest reservationRequest) {
        log.debug("addReservation()");
        return reservationService.addResevation(reservationRequest);
    }

    @PutMapping("{id}")
    public Optional<ReservationDto> updateReservation(
        @RequestParam Long id,
        @RequestBody ReservationRequest reservationRequest) {
        log.debug("updateReservation()");
        return reservationService.updateReservation(id, reservationRequest);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReservation(
        @RequestParam Long id) {
        log.debug("delete reservation by id: {}", id);
        reservationService.deleteReservation(id);
    }
    
}