package com.rickandmorty.infrastructure.controller.advice;

import com.rickandmorty.domain.exception.AlreadyApiException;
import com.rickandmorty.domain.exception.DataNotFoundException;
import com.rickandmorty.domain.exception.RickandmortyException;
import com.rickandmorty.infrastructure.adapter.repository.entity.AuditEntity;
import com.rickandmorty.infrastructure.adapter.repository.jpa.AuditJpaRepository;
import com.rickandmorty.infrastructure.controller.dto.ApiResponseDto;
import com.rickandmorty.infrastructure.controller.dto.NotificationDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor
@ControllerAdvice
public class GeneralAdviceController {

    private static final List<ErrorDescriptor> ERROR_CATALOG = new ArrayList<>();
    @Autowired
    private AuditJpaRepository auditJpaRepository;

    static {

        ERROR_CATALOG.add(new ErrorDescriptor(DataNotFoundException.class, HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.toString()));
        ERROR_CATALOG.add(new ErrorDescriptor(AlreadyApiException.class, HttpStatus.CONFLICT, HttpStatus.CONFLICT.toString()));
        ERROR_CATALOG.add(new ErrorDescriptor(RuntimeException.class, HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString()));
    }

    @ExceptionHandler({DataNotFoundException.class,
            AlreadyApiException.class

    })
    public final ResponseEntity<ApiResponseDto> handleAllHandledExceptions(
            RickandmortyException exception, HttpServletRequest req, HttpServletResponse res) {

        log.error(exception.getMessage(), exception);
        saveAudit(req, res,exception.getMessage());
        return ResponseEntity.status(findDescriptorByException(exception).httpStatus)
                .body(ApiResponseDto.builder().data(null)
                        .notification(buildNotification(exception.getMessage(),
                                exception.getNotificationCode().getApiCode()))
                        .build());

    }

    @ExceptionHandler({
            RuntimeException.class
    })
    public final ResponseEntity<ApiResponseDto> handleAllExceptions(Exception exception, HttpServletRequest req, HttpServletResponse res) {

        log.error(exception.getMessage(), exception);

        ErrorDescriptor errorDesc = findDescriptorByException(exception);
        saveAudit(req, res,exception.getMessage());
        return ResponseEntity.status(errorDesc.getHttpStatus())
                .body(ApiResponseDto.builder().data(null)
                        .notification(buildNotification(exception.getMessage(),
                                errorDesc.getResponseCode()))
                        .build());

    }

    private NotificationDto buildNotification(String message, String apicode) {

        return NotificationDto.builder()
                .description(message)
                .apiCode(apicode)
                .build();

    }

    private ErrorDescriptor findDescriptorByException(Exception ex) {

        return ERROR_CATALOG.stream()
                .filter(errorDes -> errorDes.getExType().equals(ex.getClass()))
                .findFirst()
                .orElse(new ErrorDescriptor(
                        null, HttpStatus.INTERNAL_SERVER_ERROR,
                        HttpStatus.INTERNAL_SERVER_ERROR.toString()));

    }

    @Getter
    private static class ErrorDescriptor {

        Class<? extends Throwable> exType;
        HttpStatus httpStatus;
        String responseCode;

        private ErrorDescriptor(Class<? extends Throwable> exType,
                                HttpStatus httpStatus, String responseCode) {

            this.exType = exType;
            this.httpStatus = httpStatus;
            this.responseCode = responseCode;

        }

    }

    private void saveAudit(HttpServletRequest req, HttpServletResponse res, String messageError) {
        AuditEntity auditEntity = new AuditEntity();
        auditEntity.setCreationDate(LocalDateTime.now());
        auditEntity.setMessageError(messageError);
        auditEntity.setRequestInformation("Method :  " + req.getMethod() + " Status : " + res.getStatus() + " Path : " + req.getServletPath());
        auditJpaRepository.save(auditEntity);
    }
}