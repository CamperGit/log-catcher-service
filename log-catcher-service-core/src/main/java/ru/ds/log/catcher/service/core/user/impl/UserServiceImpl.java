package ru.ds.log.catcher.service.core.user.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.ds.log.catcher.service.core.user.UserService;
import ru.ds.log.catcher.service.dao.entity.user.UserEntity;
import ru.ds.log.catcher.service.dao.entity.user.UserRepository;
import ru.ds.log.catcher.service.dao.entity.user.UserSettings;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public UserEntity findByTelegramChatIdOrUsernameOrCreate(String telegramChatId, String telegramUsername) {
        if (StringUtils.isEmpty(telegramChatId) && StringUtils.isEmpty(telegramUsername)) {
            throw new IllegalStateException(String.format("Telegram chat id (%s) or telegram username (%s) is empty", telegramChatId, telegramUsername));
        }
        Optional<UserEntity> byTelegramChatId = userRepository.findByTelegramChatId(telegramChatId);
        Optional<UserEntity> byTelegramUsername = userRepository.findByTelegramUsername(telegramUsername);
        if (byTelegramChatId.isEmpty() && byTelegramUsername.isEmpty()) {
            UserEntity newUser = new UserEntity();
            newUser.setTelegramChatId(telegramChatId);
            newUser.setTelegramUsername(telegramUsername);
            newUser.setSettings(new UserSettings());
            return save(newUser);
        }
        return byTelegramChatId.orElseGet(() -> byTelegramUsername.get());
    }

    @Override
    public UserEntity findByTelegramChatId(String telegramChatId) {
        return userRepository.findByTelegramChatId(telegramChatId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Not found user for telegram chat id = %s", telegramChatId)
                ));
    }

    @Override
    public UserEntity findByTelegramUsername(String telegramUsername) {
        return userRepository.findByTelegramUsername(telegramUsername)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Not found user for telegram username = %s", telegramUsername)
                ));
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Not found user for id = %d", id)
                ));
    }
}
