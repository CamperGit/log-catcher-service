package ru.ds.log.catcher.service.dao.entity.log;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.ds.log.catcher.service.dao.entity.system.SystemEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "log")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LogEntity {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "log_id_seq", sequenceName = "log_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_id_seq")
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    LogType type;

    @Column(name = "pid")
    Long pid;

    @Column(name = "clazz")
    String clazz;

    @Column(name = "thread")
    String thread;

    @Column(name = "message")
    String message;

    @Column(name = "time")
    LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "system_id")
    SystemEntity system;
}
