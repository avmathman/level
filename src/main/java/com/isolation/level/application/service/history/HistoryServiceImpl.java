package com.isolation.level.application.service.history;

import com.isolation.level.application.mapper.HistoryMapper;
import com.isolation.level.application.model.HistoryModel;
import com.isolation.level.domain.book.BookEntity;
import com.isolation.level.domain.history.HistoryEntity;
import com.isolation.level.infrastructure.repository.BookRepository;
import com.isolation.level.infrastructure.repository.HistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository repository;
    private final HistoryMapper mapper;
    private final BookRepository bookRepository;

    public HistoryServiceImpl(
            HistoryRepository repository,
            HistoryMapper mapper,
            BookRepository bookRepository
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public HistoryModel createHistoryReadCommitted(HistoryModel historyModel) {
        HistoryEntity historyEntity = mapper.modelToEntity(historyModel);
        HistoryEntity createdHistoryEntity = null;

        BookEntity book = bookRepository.findByTitle(historyModel.getTitle()).orElse(null);

        if (book != null) {
            historyEntity.setStatus("RECEIVED");
            createdHistoryEntity = repository.save(historyEntity);

            book.setLikes(book.getLikes() + historyModel.getLikes());
            bookRepository.save(book);

            log.info("{} likes added to {}", historyModel.getLikes(), book.getAuthor());
        } else {
            log.warn("Book with title {} not found", historyModel.getTitle());

            historyEntity.setStatus("ORPHANED");
            createdHistoryEntity = repository.save(historyEntity);
        }

        return mapper.entityToModel(repository.save(createdHistoryEntity));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public HistoryModel createHistoryRepeatableRead(HistoryModel historyModel) {
        HistoryEntity historyEntity = mapper.modelToEntity(historyModel);
        HistoryEntity createdHistoryEntity = null;

        BookEntity book = bookRepository.findByTitle(historyModel.getTitle()).orElse(null);

        if (book != null) {
            historyEntity.setStatus("RECEIVED");
            createdHistoryEntity = repository.save(historyEntity);

            book.setLikes(book.getLikes() + historyModel.getLikes());
            bookRepository.save(book);

            log.info("{} likes added to {}", historyModel.getLikes(), book.getAuthor());
        } else {
            log.warn("Book with title {} not found", historyModel.getTitle());

            historyEntity.setStatus("ORPHANED");
            createdHistoryEntity = repository.save(historyEntity);
        }

        return mapper.entityToModel(repository.save(createdHistoryEntity));
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Retryable(value = CannotAcquireLockException.class, maxAttempts = 5, backoff=@Backoff(delay = 1000))
    public HistoryModel createHistorySerializable(HistoryModel historyModel) {
        HistoryEntity historyEntity = mapper.modelToEntity(historyModel);
        HistoryEntity createdHistoryEntity = null;

        BookEntity book = bookRepository.findByTitle(historyModel.getTitle()).orElse(null);

        if (book != null) {
            historyEntity.setStatus("RECEIVED");
            createdHistoryEntity = repository.save(historyEntity);

            try {
                book.setLikes(book.getLikes() + historyModel.getLikes());
                bookRepository.save(book);
            } catch (Exception e) {
                log.error(e.getMessage());
            }

            log.info("{} likes added to {}", historyModel.getLikes(), book.getAuthor());
        } else {
            log.warn("Book with title {} not found", historyModel.getTitle());

            historyEntity.setStatus("ORPHANED");
            createdHistoryEntity = repository.save(historyEntity);
        }

        return mapper.entityToModel(repository.save(createdHistoryEntity));
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
