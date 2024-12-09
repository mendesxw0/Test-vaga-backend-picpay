package com.example.com.picPaySImplificado.UserRepository;

import com.example.com.picPaySImplificado.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransationRepository extends JpaRepository<Transaction, Long> {

}
