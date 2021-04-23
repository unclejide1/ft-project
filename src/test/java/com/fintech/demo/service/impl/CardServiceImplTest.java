package com.fintech.demo.service.impl;

import com.fintech.demo.dao.AccountsDao;
import com.fintech.demo.dao.HitsDao;
import com.fintech.demo.dto.CardDetailsResponseJSON;
import com.fintech.demo.model.Accounts;
import com.fintech.demo.model.Hits;
import com.fintech.demo.model.enums.CardSchemes;
import com.fintech.demo.model.enums.CardType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

//import static org.junit.Assert.*;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CardServiceImplTest {

    private static CardDetailsResponseJSON cardDetailsResponseJSON;
    private static Accounts accounts;
    private static Accounts accounts1;
    private static Hits hits;

    @Mock
    private AccountsDao accountsDao;

    @Mock
    private HitsDao hitsDao;

    @InjectMocks
    private CardServiceImpl cardService;

    @BeforeEach
    void setUp() {
        cardDetailsResponseJSON = new CardDetailsResponseJSON();
        cardDetailsResponseJSON.setBank("ACCESS");
        cardDetailsResponseJSON.setCardSchemes("VERVE");
        cardDetailsResponseJSON.setCardType("CREDIT");
        accounts = new Accounts();
        accounts.setAccountNo("1234567890");
        accounts.setCardSchemes(CardSchemes.valueOf("VERVE"));
        accounts.setCardType(CardType.valueOf("CREDIT"));
        accounts.setBank("ACCESS");
        hits = new Hits();
        hits.setAccount(accounts);
        accounts1 = new Accounts();
        accounts1.setAccountNo("123456789");
        accounts1.setCardSchemes(CardSchemes.valueOf("VERVE"));
        accounts1.setCardType(CardType.valueOf("CREDIT"));
        accounts1.setBank("ACCE");
    }

    @BeforeEach
    void setMockOutput() {
        Mockito.when(accountsDao.getCardByCardNumber("1234567890")).thenReturn(accounts);
        Mockito.when(accountsDao.getCardByCardNumber("123456789")).thenReturn(accounts1);
        Mockito.when(hitsDao.saveRecord(hits)).thenReturn(hits);
//        Mockito.when(accountOpeningRequestEntityDao.saveRecord(accountOpeningRequestEntity)).thenReturn(accountOpeningRequestEntity);
    }



    @Test
    void getCardDetails() {
        assertEquals(cardService.getCardDetails("1234567890"), cardDetailsResponseJSON);
        assertThat(cardService.getCardDetails("1234567890"), is(cardDetailsResponseJSON));
    }

    @Test
    void getCardDetailsWrong() {
        assertNotEquals(cardService.getCardDetails("123456789"), cardDetailsResponseJSON);
//        assertThat(cardService.getCardDetails("123456789"), is(cardDetailsResponseJSON));
    }

    @Test
    void getMostSearchedCards() {
    }

    @Test
    void getTotalCount() {
    }
}