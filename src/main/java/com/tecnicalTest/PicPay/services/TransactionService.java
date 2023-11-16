package com.tecnicalTest.PicPay.services;

import com.tecnicalTest.PicPay.model.transaction.Transaction;
import com.tecnicalTest.PicPay.model.user.User;
import com.tecnicalTest.PicPay.dtos.TransactionDTO;
import com.tecnicalTest.PicPay.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transactionDTO) throws Exception {
        User payer = this.userService.findUserById(transactionDTO.payerId());
        User receiver = this.userService.findUserById(transactionDTO.receiverId());

        userService.validateTransaction(payer, transactionDTO.value());

        if (!validateTransaction(payer, transactionDTO.value())){
            throw new Exception("Invalid Transaction");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmmount(transactionDTO.value());
        newTransaction.setPayer(payer);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimeStamp(LocalDateTime.now());

        payer.setBalance(payer.getBalance().subtract(transactionDTO.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.value()));

        repository.save(newTransaction);
        userService.SaveUser(payer);
        userService.SaveUser(receiver);

        notificationService.sendNotification(payer, "You pay this ammount of money:" + newTransaction.getAmmount());
        notificationService.sendNotification(receiver, "You receave this ammount of money:" + newTransaction.getAmmount());

        return newTransaction;
    }

    public boolean validateTransaction(User payer, BigDecimal value){
       /* ResponseEntity<Map> responseEntity = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK){
            return ((String) responseEntity.getBody().get("message")).equalsIgnoreCase("Autorizado");
        }else
            return false;*/

        return  true;
    }
}
