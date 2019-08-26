package blazesoft.hotelreservation.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import blazesoft.hotelreservation.exceptions.ReservationNotFoundException;
import blazesoft.hotelreservation.model.dtos.ReservationDto;
import blazesoft.hotelreservation.model.dtos.ReservationRequest;
import blazesoft.hotelreservation.services.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("reservations")
@Slf4j
public class ReservationController {
    private final ReservationService reservationService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new ReservationValidator());
    }

    @GetMapping
    public List<ReservationDto> getFilteredReservations(
            @RequestParam(required = false) String roomNumber) {
        log.debug("getFilteredReservations()");
        if (!StringUtils.isEmpty(roomNumber)) {
            return reservationService.getReservationsByRoom(roomNumber);
        } else {
            return reservationService.getAllReservations();
        }
    }

    @GetMapping("{id}")
    public ReservationDto getReservation(@PathVariable Long id) {
        log.debug("getReservation()");
        return reservationService.getReservation(id)
                .orElseThrow(() -> new ReservationNotFoundException());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationDto addReservation(@RequestBody @Valid ReservationRequest reservationRequest) {
        log.debug("addReservation()");
        return reservationService.addResevation(reservationRequest);
    }

    @PutMapping("{id}")
    public ReservationDto updateReservation(@PathVariable Long id,
            @RequestBody @Valid ReservationRequest reservationRequest) {
        log.debug("updateReservation()");
        return reservationService.updateReservation(id, reservationRequest)
                .orElseThrow(() -> new ReservationNotFoundException());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReservation(@PathVariable Long id) {
        log.debug("delete reservation by id: {}", id);
        reservationService.deleteReservation(id);
    }

}