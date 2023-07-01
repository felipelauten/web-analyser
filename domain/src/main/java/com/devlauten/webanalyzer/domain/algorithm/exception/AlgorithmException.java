package com.devlauten.webanalyzer.domain.algorithm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AlgorithmException extends RuntimeException {
}
