package blazesoft.hotelreservation.exceptions;


import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Number of people exceeded")
public class NumberOfPeopleExceeded extends RuntimeException {

    
}