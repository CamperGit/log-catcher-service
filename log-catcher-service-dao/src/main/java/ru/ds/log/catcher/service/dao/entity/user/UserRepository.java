package ru.ds.log.catcher.service.dao.entity.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByTelegramChatId(String telegramChatId);

    Optional<UserEntity> findByTelegramUsername(String telegramUsername);
}
