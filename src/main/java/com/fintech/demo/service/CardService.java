package com.fintech.demo.service;

import com.fintech.demo.dto.CardDetailsResponseJSON;
import com.fintech.demo.dto.HitCount;
import com.fintech.demo.model.Accounts;

import java.util.HashMap;
import java.util.List;

public interface CardService {
    CardDetailsResponseJSON getCardDetails(String cardNo);
    HashMap<String, Long> getMostSearchedCards(int start, int limit);
    Long getTotalCount();
}
