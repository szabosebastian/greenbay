package com.gfa.greenbay.exceptions;

import com.gfa.greenbay.models.DTO.ErrorMessageDTO;
import com.gfa.greenbay.services.ErrorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

  private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerControllerAdvice.class);
  private final ErrorService errorService;

  public ExceptionHandlerControllerAdvice(ErrorService errorService) {
    this.errorService = errorService;
  }

  @ExceptionHandler(MissingParamsException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public @ResponseBody
  ErrorMessageDTO handleMissingParamsException(final Exception exception) {
    log.error(exception.getMessage());
    return errorService.defaultExceptionResponse(exception);
  }

  @ExceptionHandler(UserNameAlreadyExistException.class)
  @ResponseStatus(value = HttpStatus.CONFLICT)
  public @ResponseBody
  ErrorMessageDTO handleUserNameAlreadyExistException(final Exception exception) {
    log.error(exception.getMessage());
    return errorService.defaultExceptionResponse(exception);
  }

  @ExceptionHandler(PasswordTooShortException.class)
  @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
  public @ResponseBody
  ErrorMessageDTO handlePasswordTooShortException(final Exception exception) {
    log.error(exception.getMessage());
    return errorService.defaultExceptionResponse(exception);
  }

  @ExceptionHandler(UsernameNotFoundOrInvalidPasswordException.class)
  @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
  public @ResponseBody
  ErrorMessageDTO handleUsernameNotFoundOrInvalidPasswordException(final Exception exception) {
    log.error(exception.getMessage());
    return errorService.defaultExceptionResponse(exception);
  }
}
