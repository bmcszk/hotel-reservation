package blazesoft.hotelreservation.controllers;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import blazesoft.hotelreservation.model.dtos.ReservationRequest;

public class ReservationValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ReservationRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "userName", "userName.empty");
        ValidationUtils.rejectIfEmpty(errors, "numberOfPeople", "numberOfPeople.empty");
        ValidationUtils.rejectIfEmpty(errors, "startDate", "startDate.empty");
        ValidationUtils.rejectIfEmpty(errors, "endDate", "endDate.empty");
        if (errors.hasErrors()) {
            return;
        }

        ReservationRequest request = (ReservationRequest) target;
        if (request.getStartDate().isAfter(request.getEndDate())) {
            errors.rejectValue("endDate", "endDate.beforeStartDate");
        }
    }
    
}