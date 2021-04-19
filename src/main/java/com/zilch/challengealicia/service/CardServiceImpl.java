package com.zilch.challengealicia.service;

import com.zilch.challengealicia.dao.CardRepo;
import com.zilch.challengealicia.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

@Service("CardService")
public class CardServiceImpl implements CardService {

    private final CardRepo cardRepo;

    @Autowired
    public CardServiceImpl(CardRepo cardRepo) {
        this.cardRepo = cardRepo;
    }

    public List<Card> findAllCards() {
        return cardRepo.findAll();
    }

    public Optional<Card> findCard(Integer id) {
        return cardRepo.findById(id);
    }

    public Card generateCard(String name) throws IllegalArgumentException, ValidationException  {
        Card card = new Card(name);
        return cardRepo.saveAndFlush(card);
    }

}
