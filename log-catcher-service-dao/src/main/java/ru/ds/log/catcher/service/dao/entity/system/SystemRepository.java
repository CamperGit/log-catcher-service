package ru.ds.log.catcher.service.dao.entity.system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemRepository extends JpaRepository<SystemEntity, Long> {

    Optional<SystemEntity> findByName(String name);
}
