package com.tecnicalTest.PicPay.dtos;

import org.springframework.http.HttpStatus;

public record ExceptionDTO(String message, String status) {
}
