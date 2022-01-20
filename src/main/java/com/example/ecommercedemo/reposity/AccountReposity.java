package com.example.ecommercedemo.reposity;

import com.example.ecommercedemo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountReposity extends JpaRepository<Account,Long>, JpaSpecificationExecutor<Account> {
}
