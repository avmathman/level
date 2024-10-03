package com.isolation.level.application.service.history;

import com.isolation.level.application.model.HistoryModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Override
    public HistoryModel createHistory(HistoryModel historyModel) {
        return null;
    }

    @Override
    public HistoryModel getHistory(Long historyId) {
        return null;
    }

    @Override
    public List<HistoryModel> getAllHistory() {
        return List.of();
    }
}
