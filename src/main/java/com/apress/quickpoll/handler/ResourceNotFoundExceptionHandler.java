package com.apress.quickpoll.handler;

import com.apress.quickpoll.vo.ErrorDetailVO;
import com.apress.quickpoll.vo.ValidationErrorVO;
import com.apress.quickpoll.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class ResourceNotFoundExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException, HttpServletRequest request) {
        ErrorDetailVO errorDetail = new ErrorDetailVO(
                "Resource Not Found",
                HttpStatus.NOT_FOUND.value(),
                resourceNotFoundException.getMessage(),
                new Date().getTime(),
                resourceNotFoundException.getClass().getName(),
                new HashMap<>());
        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException manve, HttpServletRequest request) {
        ErrorDetailVO errorDetail = new ErrorDetailVO("Validation Failed",
                HttpStatus.BAD_REQUEST.value(),
                "Input validation failed",
                new Date().getTime(),
                manve.getClass().getName(),
                new HashMap<>());
        List<FieldError> fieldErrors =  manve.getBindingResult().getFieldErrors();
        for(FieldError fe : fieldErrors) {
            List<ValidationErrorVO> validationErrorList = errorDetail.getErrors().computeIfAbsent(fe.getField(), k -> new ArrayList<>());
            validationErrorList.add(new ValidationErrorVO(fe.getCode(), messageSource.getMessage(fe, null)));
        }
        return new ResponseEntity<>(errorDetail, null, HttpStatus. BAD_REQUEST);
    }


}
