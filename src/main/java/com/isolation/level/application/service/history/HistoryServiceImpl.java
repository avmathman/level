package com.isolation.level.application.service.history;

import com.isolation.level.application.mapper.HistoryMapper;
import com.isolation.level.application.model.HistoryModel;
import com.isolation.level.infrastructure.repository.HistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository repository;
    private final HistoryMapper mapper;

    public HistoryServiceImpl(
            HistoryRepository repository,
            HistoryMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public HistoryModel createHistoryReadCommitted(HistoryModel historyModel) {
        return mapper.entityToModel(repository.save(mapper.modelToEntity(historyModel)));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public HistoryModel createHistoryRepeatableRead(HistoryModel historyModel) {
        return mapper.entityToModel(repository.save(mapper.modelToEntity(historyModel)));
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public HistoryModel createHistorySerializable(HistoryModel historyModel) {
        return mapper.entityToModel(repository.save(mapper.modelToEntity(historyModel)));
    }

    @Override
    public HistoryModel getHistory(Long historyId) {
        return mapper.entityToModel(repository.getReferenceById(historyId));
    }

    @Override
    public List<HistoryModel> getAllHistory() {
        return mapper.entitiesToModels(repository.findAll());
    }

    @Override
    public void deleteAllHistory() {
        repository.deleteAll();
    }
}
