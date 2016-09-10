package challenge.aspect;

import challenge.dto.ErrorDTO;
import challenge.exception.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(value=HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public ErrorDTO handleError(AuthorizationException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError(e.getMessage());
        errorDTO.setMessage("Unable to process event");
        errorDTO.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        return errorDTO;
    }

}
