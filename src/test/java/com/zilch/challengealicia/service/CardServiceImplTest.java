package com.zilch.challengealicia.service;

import com.zilch.challengealicia.dao.CardRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CardServiceImplTest {
    @Autowired
    private CardRepo cardRepo;

    @Autowired
    private CardService cardService;

    @Test
    void testGenerateCard() {
        long initSize = cardRepo.count();
        cardService.generateCard("Name test");
        assertEquals(initSize + 1, cardRepo.count());
    }
}