package br.com.mercadolivre.desafiospring.exception;

import br.com.mercadolivre.desafiospring.dto.ResponseDTO;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({Exception.class, JsonMappingException.class, UserNotFoundException.class, UserIsNotASellerException.class, SortUtilException.class})
    public final ResponseEntity handleException(Exception ex){
        String errorMessage = ex.getMessage();
        if (ex instanceof UserNotFoundException) errorMessage = "The User informed does not exist.";
        if (ex instanceof UserIsNotASellerException) errorMessage = "The user is not a seller.";
        if (ex instanceof SortUtilException) errorMessage = "Error in order parameters interpretation.";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(errorMessage));
    }
}
