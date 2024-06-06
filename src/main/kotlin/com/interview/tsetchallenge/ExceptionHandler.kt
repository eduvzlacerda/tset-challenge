package com.interview.tsetchallenge

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<String> {
        val errorMessage = ex.bindingResult.fieldErrors.firstOrNull()?.defaultMessage ?: "Invalid value"
        return ResponseEntity(errorMessage,HttpStatus.BAD_REQUEST)
    }
}