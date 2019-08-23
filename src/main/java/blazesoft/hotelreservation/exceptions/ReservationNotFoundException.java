package blazesoft.hotelreservation.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Reservation not found")
public class ReservationNotFoundException extends RuntimeException {

    
}