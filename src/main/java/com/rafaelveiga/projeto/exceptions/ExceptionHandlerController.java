package com.rafaelveiga.projeto.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

  private MessageSource messageSource;

  public ExceptionHandlerController(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ErrorMessageDTO>> handle(MethodArgumentNotValidException e) {

    List<ErrorMessageDTO> dto = new ArrayList<>();

    e.getBindingResult().getFieldErrors().forEach(fieldError -> {
      String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());

      ErrorMessageDTO error = new ErrorMessageDTO(message, fieldError.getField());

      dto.add(new ErrorMessageDTO(message, error.getField()));
    });

    return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
  }

}
