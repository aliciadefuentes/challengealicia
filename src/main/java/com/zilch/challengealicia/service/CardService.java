package com.zilch.challengealicia.service;

import com.zilch.challengealicia.model.Card;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

public interface CardService {

    List<Card> findAllCards();

    Optional<Card> findCard(Integer id);

    Card generateCard(String name) throws IllegalArgumentException, ValidationException;
}
