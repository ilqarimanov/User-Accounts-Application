package az.dev.useraccountsapplication.repository;

import az.dev.useraccountsapplication.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserEntitiesByUsernameAndPassword(String username, String password);

    Optional<UserEntity> findByUsername(String username);

    UserEntity findUserEntityById(Long userId);


}
