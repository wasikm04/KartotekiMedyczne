package pl.medical.service.files.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.medical.service.files.models.Exceptions.ResourceNotFoundException;

import java.io.IOException;

@ControllerAdvice
@RequestMapping(produces = "application/json")
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> exception(final ResourceNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> assertionException(final IllegalArgumentException e) {
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Object> ioexp(final IOException e) {
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }

}
