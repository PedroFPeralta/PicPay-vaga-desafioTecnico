package com.tecnicalTest.PicPay.dtos;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long payerId, Long receiverId) {
}
