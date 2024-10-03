package com.isolation.level.infrastructure.repository;

import com.isolation.level.domain.history.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Data repository for history.
 */
@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {
}
