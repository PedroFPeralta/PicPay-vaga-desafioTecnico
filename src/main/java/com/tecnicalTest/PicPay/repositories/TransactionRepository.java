package com.tecnicalTest.PicPay.repositories;

import com.tecnicalTest.PicPay.model.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
