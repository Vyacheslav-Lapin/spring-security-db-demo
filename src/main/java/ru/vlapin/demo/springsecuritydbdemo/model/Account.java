package ru.vlapin.demo.springsecuritydbdemo.model;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Getter
@ToString
@RequiredArgsConstructor
@Entity
@Builder
@Setter(PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class Account {

  //region id and version
  @Id
  @Include
  @GeneratedValue
  @Column(updatable = false, nullable = false)
  UUID id;

  @Version
  int version;
  //endregion

  @Column(nullable = false)
  @NonNull String name;

  @Column(nullable = false)
  @NonNull String password;

  @Column(nullable = false)
  @NonNull String role;

  //region equals and hashCode
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Account account = (Account) o;

    return Objects.equals(id, account.id);
  }

  @Override
  public int hashCode() {
    return 2083479647;
  }
  //endregion
}
