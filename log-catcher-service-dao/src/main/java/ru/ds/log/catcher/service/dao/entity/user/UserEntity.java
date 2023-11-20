package ru.ds.log.catcher.service.dao.entity.user;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.ds.log.catcher.service.dao.converter.UserSettingsJsonConverter;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    Long id;

    @Column(name = "telegram_username")
    String telegramUsername;

    @Column(name = "telegram_chat_id")
    String telegramChatId;

    @Column(name = "settings", columnDefinition = "jsonb", nullable = false)
    @Convert(converter = UserSettingsJsonConverter.class)
    UserSettings settings;
}
