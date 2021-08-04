package ru.vlapin.demo.springsecuritydbdemo;

import org.aspectj.lang.annotation.Aspect;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import ru.vlapin.demo.springsecuritydbdemo.dao.AccountRepository;
import ru.vlapin.demo.springsecuritydbdemo.model.Account;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan(includeFilters = @ComponentScan.Filter(Aspect.class))
public class SpringSecurityDbDemoApplication extends WebSecurityConfigurerAdapter {

  @Bean
  PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Override
  protected void configure(@NotNull HttpSecurity httpSecurity) throws Exception {
    //ALLOW ACCESS TO H2 CONSOLE
    httpSecurity.authorizeRequests().antMatchers("/h2-console/**").permitAll();
    httpSecurity.headers().frameOptions().sameOrigin();
    httpSecurity.csrf().disable();

    //LOCK EVERYTHING ELSE (User must be Authorized with proper Roles & Authorities)
    httpSecurity.authorizeRequests().anyRequest().authenticated();

    //AUTHENTICATE USER WITH DEFAULT LOGIN FORM
    httpSecurity.formLogin();
  }

  @Bean
  CommandLineRunner accountLoader(AccountRepository repository) {
    return new CommandLineRunner() {
      @Override
      @Transactional
      public void run(String... __) {
        repository.save(
            Account.builder()
                .name("myUser")
                .password("myUserPassword")
//                .role("ROLE_USER").build());
                .role("ROLE_ADMIN").build());
      }
    };
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringSecurityDbDemoApplication.class, args);
  }
}
