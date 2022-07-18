package az.dev.useraccountsapplication.repository;

import az.dev.useraccountsapplication.entity.AccountEntity;
import az.dev.useraccountsapplication.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    Optional<List<AccountEntity>> findAllByUserId(UserEntity userId);



}
