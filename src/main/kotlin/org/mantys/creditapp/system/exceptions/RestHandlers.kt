package org.mantys.creditapp.system.exceptions

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class RestHandlers {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerValidExcpetion(ex: MethodArgumentNotValidException): ResponseEntity<DetailsHandler> {
        val errors: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.stream().forEach {
            error: ObjectError ->
            val fieldName: String = (error as FieldError).field
            val messageError: String? = error.defaultMessage
            errors[fieldName]=messageError
        }
        return ResponseEntity(
            DetailsHandler(
                title = "Bad Request: Verify the documentation",
                timestamp = LocalDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                excepetion = ex.javaClass.toString(),
                details = errors
            ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handlerValidExcpetion(ex: DataIntegrityViolationException): ResponseEntity<DetailsHandler> {
        return ResponseEntity(
            DetailsHandler(
                title = "Conflict: Verify the documentation",
                timestamp = LocalDateTime.now(),
                status = HttpStatus.CONFLICT.value(),
                excepetion = ex.javaClass.toString(),
                details = mutableMapOf(ex.cause.toString() to ex.message)
            ), HttpStatus.CONFLICT
        )
    }

    @ExceptionHandler(BusinessHandler::class)
    fun handlerValidExcpetion(ex: BusinessHandler): ResponseEntity<DetailsHandler> {
        return ResponseEntity(
            DetailsHandler(
                title = "Bad Request: Verify the documentation",
                timestamp = LocalDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                excepetion = ex.javaClass.toString(),
                details = mutableMapOf(ex.cause.toString() to ex.message)
            ), HttpStatus.BAD_REQUEST
        )
    }
}