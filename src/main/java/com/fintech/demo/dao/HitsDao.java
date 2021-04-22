package com.fintech.demo.dao;

import com.fintech.demo.dto.HitCount;
import com.fintech.demo.model.Hits;

import java.util.List;

public interface HitsDao extends CrudDao<Hits, Long> {
    long countHits();
    List<HitCount> getMostSearchedCards(int start, int limit);
}
