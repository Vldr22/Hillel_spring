package org.education.hillel_springhomework.service;

import jakarta.transaction.Transactional;
import org.education.hillel_springhomework.dto.StatisticsCollection;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StatisticsService {

    private final StatisticCollectionDAO statisticCollectionDAO;

    public StatisticsService(StatisticCollectionDAO statisticCollectionDAO) {
        this.statisticCollectionDAO = statisticCollectionDAO;
    }

    public void addStatistics(StatisticsCollection statistics) {
        statisticCollectionDAO.save(statistics);
    }
}
