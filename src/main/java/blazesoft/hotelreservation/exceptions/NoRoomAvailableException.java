package blazesoft.hotelreservation.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No room available")
public class NoRoomAvailableException extends RuntimeException {

    
}