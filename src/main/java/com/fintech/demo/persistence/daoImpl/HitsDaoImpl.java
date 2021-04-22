package com.fintech.demo.persistence.daoImpl;


import com.fintech.demo.dao.HitsDao;
import com.fintech.demo.dto.HitCount;
import com.fintech.demo.exceptions.BadRequestException;
import com.fintech.demo.model.Hits;
import com.fintech.demo.persistence.repository.HitsRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.inject.Named;
import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class HitsDaoImpl extends CrudDaoImpl<Hits, Long> implements HitsDao {

    private HitsRepository repository;

    public HitsDaoImpl( HitsRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public long countHits() {
        return repository.count();
    }

    @Override
    public List<HitCount> getMostSearchedCards(int start, int limit) {
        List<HitCount> hitCounts = new ArrayList<>();
        int newLimit = limit;
        int newStart = start;

            List<Object[]> results = repository.countHitsByAccount();
            if( limit > results.size() || start >= results.size() ){
                newLimit = results.size();
                newStart = 0;
                System.out.println(newStart);
            }
            hitCounts = results.subList(newStart,newLimit).stream().map(hitCount-> {
                HitCount hitCount1 = new HitCount();
                hitCount1.setAccountId((Long) hitCount[0]);
                hitCount1.setTotal((Long) hitCount[1]);

            return hitCount1;
        }).collect(Collectors.toList());
        return hitCounts;
    }
}
