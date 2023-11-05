package ru.ds.log.catcher.service.dao.entity.system;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "system")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SystemEntity {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "system_id_seq", sequenceName = "system_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system_id_seq")
    Long id;

    @Column(name = "name")
    String name;
}
