package ru.vlapin.demo.springsecuritydbdemo.service;

import io.vavr.Function3;
import io.vavr.Tuple;
import io.vavr.Tuple3;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vlapin.demo.springsecuritydbdemo.dao.AccountRepository;
import ru.vlapin.demo.springsecuritydbdemo.model.Account;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

  private static final Function<Account, User> TO_USER =
      Function3.<String, String, Collection<? extends GrantedAuthority>, User>of(User::new)
          .tupled()
          .<Tuple3<String, String, ? extends GrantedAuthority>>compose(t -> t.map3(Collections::singleton))
          .<Tuple3<String, String, String>>compose(t -> t.map3(SimpleGrantedAuthority::new))
          .compose(account -> Tuple.of(account.getName(), account.getPassword(), account.getRole()));

  AccountRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return repository.findByName(username).map(TO_USER)
        .orElseThrow(() -> new UsernameNotFoundException(username));
  }
}
