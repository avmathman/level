package com.isolation.level.infrastructure.repository;

import com.isolation.level.domain.history.HistoryEntity;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES;
import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.Replace.ANY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureEmbeddedDatabase(type = POSTGRES, replace = ANY, beanName = "dataSource")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HistoryRepositoryTest {

    HistoryEntity history;

    @Autowired
    private HistoryRepository historyRepository;

    @BeforeAll
    public void beforeAll() {
        insertHistory();
    }

    @Test
    public void getReferenceById_passesValidId_returnFoundHistory() {
        HistoryEntity current = historyRepository.getReferenceById(history.getId());

        assertNotNull(current);
        assertEquals("Title", current.getTitle());
        assertEquals(1, current.getLikes());
        assertEquals("RECEIVED", current.getStatus());
    }

    @Test
    public void findAll_queriesAllRows_shouldMatchCount() {
        List<HistoryEntity> histories = historyRepository.findAll();

        assertEquals(1, histories.size());
        assertEquals("Title", histories.get(0).getTitle());
        assertEquals(1, histories.get(0).getLikes());
        assertEquals("RECEIVED", histories.get(0).getStatus());
    }

    private void insertHistory() {
        history = new HistoryEntity();
        history.setId(1L);
        history.setTitle("Title");
        history.setLikes(1);
        history.setStatus("RECEIVED");

        historyRepository.save(history);
    }
}
