package org.education.hillel_springhomework.service;

import org.education.hillel_springhomework.dto.StatisticsCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticCollectionDAO extends JpaRepository<StatisticsCollection, Integer> {

}
