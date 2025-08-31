package com.example.board.web.error;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> onValidation(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", "validation");
        var fields = new ArrayList<Map<String, String>>();
        ex.getBindingResult().getFieldErrors().forEach(f -> {
            fields.add(Map.of(
                "field", f.getField(),
                "message", f.getDefaultMessage()
            ));
        });
        body.put("fields", fields);
        return body;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> onIllegalArg(IllegalArgumentException ex) {
        return Map.of("error", ex.getMessage());
    }
}
