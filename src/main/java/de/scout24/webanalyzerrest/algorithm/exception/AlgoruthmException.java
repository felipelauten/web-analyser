package de.scout24.webanalyzerrest.algorithm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AlgoruthmException extends RuntimeException {
}