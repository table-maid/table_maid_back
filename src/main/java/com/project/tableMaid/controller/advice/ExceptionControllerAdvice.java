package com.project.tableMaid.controller.advice;

import com.project.tableMaid.exception.DeleteException;
import com.project.tableMaid.exception.SaveException;
import com.project.tableMaid.exception.ValidException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    // internalServerError 500에러(서버잘못)를 보냄
    @ExceptionHandler(SaveException.class)
    public ResponseEntity<?> saveException(SaveException e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    // badRequest 400에러(클라이언트 잘못)를 보냄
    @ExceptionHandler(ValidException.class)
    public ResponseEntity<?> validException(ValidException e) {
        return ResponseEntity.badRequest().body(e.getErrorMap());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> usernameNotFoundException(UsernameNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(DeleteException.class)
    public ResponseEntity<?> deleteException(DeleteException e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }


}
