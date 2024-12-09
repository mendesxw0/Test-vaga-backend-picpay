package com.example.com.picPaySImplificado.service;

import com.example.com.picPaySImplificado.UserRepository.TransationRepository;
import com.example.com.picPaySImplificado.domain.transaction.Transaction;
import com.example.com.picPaySImplificado.domain.user.User;
import com.example.com.picPaySImplificado.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransectionService {

    @Autowired
    private UserServise userServise;
    @Autowired
    private TransationRepository repository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userServise.findUserById(transaction.senderId());
        User receiver = this.userServise.findUserById(transaction.receiverId());

        userServise.validateTransection(sender, transaction.value());

        boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());
        if (!isAuthorized) {
            throw new Exception("Transação não autorizada. ");
        }


        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(newTransaction.getAmount());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestemp(LocalDateTime.now());


        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

          this.repository.save(newTransaction);
          this.userServise.saveUser(sender);
          this.userServise.saveUser(receiver);

          this.notificationService.SendNotification(sender, "Transação realizada com sucesso. ");
          this.notificationService.SendNotification(receiver, "Transação recebida  com sucesso. ");

          return newTransaction;

    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {
          ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);


          if (authorizationResponse.getStatusCode() == HttpStatus.OK) {

              String massage = String.valueOf(authorizationResponse.getBody().get("message"));
              return  "Autorizado".equalsIgnoreCase(massage);
          } else return false;
    }


}
