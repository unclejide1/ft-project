package com.fintech.demo.persistence.repository;


import com.fintech.demo.dto.HitCount;
import com.fintech.demo.model.Hits;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HitsRepository extends JpaRepository<Hits, Long> {

    @Query("SELECT account.id,COUNT(account.id) as coun FROM Hits GROUP BY account.id ORDER BY coun DESC")
    List<Object[]> countHitsByAccount();
}
