package ru.vlapin.demo.springsecuritydbdemo.dao;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlapin.demo.springsecuritydbdemo.model.Account;

public interface AccountRepository extends JpaRepository<Account, UUID> {

  Optional<Account> findByName(String name);
}
