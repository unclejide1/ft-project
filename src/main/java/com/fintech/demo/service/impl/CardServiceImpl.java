package com.fintech.demo.service.impl;

import com.fintech.demo.dao.AccountsDao;
import com.fintech.demo.dao.HitsDao;
import com.fintech.demo.dto.CardDetailsResponseJSON;
import com.fintech.demo.dto.HitCount;
import com.fintech.demo.model.Accounts;
import com.fintech.demo.model.Hits;
import com.fintech.demo.service.CardService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;

import javax.inject.Named;
import java.util.stream.Collectors;



@Slf4j
@Named
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private AccountsDao accountsDao;
    private HitsDao hitsDao;
    private Gson gson;

    @Override
//    @Cacheable("accounts")
    public CardDetailsResponseJSON getCardDetails(String cardNo) {

        System.out.println("here");
        Accounts accounts = accountsDao.getCardByCardNumber(cardNo);
        Hits hits = Hits.builder()
                .account(accounts)
                .build();
        hitsDao.saveRecord(hits);
        String bank;
        if(accounts.getBank() != null){
            bank = accounts.getBank();
        }else{
            bank ="N/A";
        }

        CardDetailsResponseJSON responseJSON = new CardDetailsResponseJSON();
        responseJSON.setCardSchemes(accounts.getCardSchemes().name());
        responseJSON.setCardType(accounts.getCardType().name());
        responseJSON.setBank(bank);

        System.out.println("result>>>>"+responseJSON.toString());
        return responseJSON;
    }

    @Override
    public  LinkedHashMap<String, Long>getMostSearchedCards(int start, int limit) {
        int newStart = start;
        if(start > 0){
            newStart = start-1;
        }
        LinkedHashMap<String, Long> result = new LinkedHashMap<>();
        List<HitCount> hitCounts= hitsDao.getMostSearchedCards(newStart, limit).stream().map(hitCount-> {
            Accounts account = accountsDao.getRecordById(hitCount.getAccountId());
            result.put(account.getAccountNo().substring(0,5), hitCount.getTotal());
            return hitCount;
        }).collect(Collectors.toList());

        return  result;

    }

    @Override
    public Long getTotalCount() {
        return hitsDao.countHits();
    }
}
