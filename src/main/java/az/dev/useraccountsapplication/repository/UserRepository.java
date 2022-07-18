package az.dev.useraccountsapplication.repository;

import az.dev.useraccountsapplication.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long> {



    UserEntity findByUsername(String username);

    Optional<UserEntity> findUserEntityById(Long userId);



}
