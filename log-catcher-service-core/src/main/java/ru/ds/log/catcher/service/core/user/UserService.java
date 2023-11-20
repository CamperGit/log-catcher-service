package ru.ds.log.catcher.service.core.user;

import ru.ds.log.catcher.service.dao.entity.user.UserEntity;

public interface UserService {

    UserEntity findByTelegramChatIdOrUsernameOrCreate(String telegramChatId, String telegramUsername);

    UserEntity findByTelegramChatId(String telegramChatId);

    UserEntity findByTelegramUsername(String telegramUsername);

    UserEntity save(UserEntity user);

    UserEntity findById(Long id);

}
