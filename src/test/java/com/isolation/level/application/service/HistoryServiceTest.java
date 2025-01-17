package com.isolation.level.application.service;

import com.isolation.level.application.mapper.HistoryMapper;
import com.isolation.level.application.model.HistoryModel;
import com.isolation.level.application.service.history.HistoryService;
import com.isolation.level.application.service.history.HistoryServiceImpl;
import com.isolation.level.domain.history.HistoryEntity;
import com.isolation.level.infrastructure.repository.BookRepository;
import com.isolation.level.infrastructure.repository.HistoryRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES;
import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.RefreshMode.BEFORE_EACH_TEST_METHOD;
import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.Replace.ANY;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureEmbeddedDatabase(type = POSTGRES, replace = ANY, beanName = "dataSource", refresh = BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HistoryServiceTest {

    private HistoryService historyService;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private BookRepository bookRepository;

    private HistoryMapper mapper;

    @BeforeAll
    public void setUp() {
        mapper = Mappers.getMapper(HistoryMapper.class);
        historyService = new HistoryServiceImpl(historyRepository, mapper, bookRepository);
    }

    @Test
    public void getHistory_passHistoryId_shouldReturnHistory() {

        // Assign
        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setTitle("title");
        historyEntity.setStatus("Status");
        historyEntity.setLikes(1);

        HistoryModel createdModel = mapper.entityToModel(historyRepository.save(historyEntity));

        // Act
        HistoryModel retrievedModel = historyService.getHistory(createdModel.getId());

        // Assert
        assertNotNull(retrievedModel);
        assertEquals(createdModel.getTitle(), retrievedModel.getTitle());
        assertEquals(createdModel.getStatus(), retrievedModel.getStatus());
        assertEquals(createdModel.getLikes(), retrievedModel.getLikes());

    }

    @Test
    public void getAllHistory_executeRetrieval_shouldReturnAllHistory() {

        // Assign
        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setTitle("title");
        historyEntity.setStatus("Status");
        historyEntity.setLikes(1);

        HistoryModel createdModel = mapper.entityToModel(historyRepository.save(historyEntity));

        // Act
        List<HistoryModel> historyModels = historyService.getAllHistory();

        // Assert
        assertTrue(historyModels.size() == 1, "Size of books should be greater than 0");
        assertEquals(createdModel.getTitle(), historyModels.get(0).getTitle());
        assertEquals(createdModel.getStatus(), historyModels.get(0).getStatus());
        assertEquals(createdModel.getLikes(), historyModels.get(0).getLikes());

    }

    @Test
    public void deleteAllHistory_executesMethod_shouldDeleteAllHistory() {

        // Assign
        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setTitle("title");
        historyEntity.setStatus("Status");
        historyEntity.setLikes(1);

        historyRepository.save(historyEntity);

        // Act
        historyService.deleteAllHistory();
        List<HistoryModel> historyModels = historyService.getAllHistory();

        // Assert
        assertTrue(historyModels.isEmpty(), "Size of histories should be 0");

    }
}
