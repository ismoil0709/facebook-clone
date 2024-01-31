package uz.pdp.facebookapp.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.pdp.facebookapp.exception.AlreadyExistsException;
import uz.pdp.facebookapp.exception.NotFoundException;
import uz.pdp.facebookapp.exception.NullOrEmptyException;

@RestControllerAdvice
public class GlobalHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<?> handleAlreadyExistsException(AlreadyExistsException e){
        var exceptionResolver = new ExceptionHandlerExceptionResolver();
        return ResponseEntity.badRequest().body(ErrorResponse.builder().message(e.getMessage()));
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e){
        return ResponseEntity.badRequest().body(ErrorResponse.builder().message(e.getMessage()).build());
    }
    @ExceptionHandler(NullOrEmptyException.class)
    public ResponseEntity<?> handleNullOrEmptyException(NullOrEmptyException e){
        return ResponseEntity.badRequest().body(ErrorResponse.builder().message(e.getMessage()));
    }
}
